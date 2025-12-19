package core.algorithms;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Task from Moss
 * <p>
 * Exchange rates change once a minute<br>
 * The method will be called frequently - multiple times a second<br>
 * The method will be called by multiple threads
 */
public class CurrencyRate {

    public enum Currency {USD, EUR, BYN}

    private record CurrencyPair(Currency from, Currency to) {
    }

    private record RateKeeper(Instant time, BigDecimal rate) {
    }

    private static final long CACHE_TTL_SECONDS = 60;
    private final Map<CurrencyPair, RateKeeper> rateMap = new ConcurrentHashMap<>();

    public BigDecimal getRate(Currency fromCurrency, Currency toCurrency) {
        CurrencyPair pair = new CurrencyPair(fromCurrency, toCurrency);

        // Пытаемся получить значение из кэша (быстрый путь без блокировок)
        RateKeeper existing = rateMap.get(pair);

        if (existing != null && !isExpired(existing)) {
            return existing.rate();
        }

        // Если данных нет или они устарели, входим в compute.
        // compute блокирует только конкретный бакет мапы и только на время выполнения лямбды.
        return rateMap.compute(pair, (key, current) -> {
            // Двойная проверка (Double-checked locking style) внутри compute:
            // пока мы ждали блокировки, другой поток мог уже обновить курс.
            if (current == null || isExpired(current)) {
                // затычка вместо реального вызова rateApi.fetchRateFromProvider(fromCurrency, toCurrency)
                BigDecimal newRate = BigDecimal.valueOf(1.0 + (Math.random() * 0.5));
                return new RateKeeper(Instant.now(), newRate);
            }
            return current;
        }).rate();
    }

    private boolean isExpired(RateKeeper keeper) {
        return keeper.time().plusSeconds(CACHE_TTL_SECONDS).isBefore(Instant.now());
    }
}
