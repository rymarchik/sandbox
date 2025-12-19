package core.algorithms;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Продолжение {@link CurrencyRate}
 * Если запросы от внешнего API доходят непозволительно долго, то лучше создать пул потоков, который будет работать в фоне и
 * получать актуальный курс. Клиент в это время не ждет и получает кэшированное значение.
 * <p>
 * Использовать Scheduler в качестве решения здесь не очень хорошая идея, т.к. придется подтягивать курсы всех пар раз в минуту.
 * Если валют 100, то это 100*99 пар. Это может быть очень затратно и по времени и по деньгам.
 */
public class CurrencyRateEnhanced {

    public enum Currency {USD, EUR, BYN}

    private record CurrencyPair(Currency from, Currency to) {
    }

    private record RateKeeper(Instant time, BigDecimal rate) {
    }

    private final Map<CurrencyPair, RateKeeper> rateMap = new ConcurrentHashMap<>();
    // Пул потоков для фонового обновления, чтобы не занимать потоки обработчиков запросов
    private final ExecutorService executor = Executors.newFixedThreadPool(10);
    // Множество для предотвращения дублирующих запросов к API по одной и той же паре
    private final Set<CurrencyPair> updatingPairs = Collections.newSetFromMap(new ConcurrentHashMap<>());

    public BigDecimal getRate(Currency fromCurrency, Currency toCurrency) {
        CurrencyPair pair = new CurrencyPair(fromCurrency, toCurrency);
        RateKeeper keeper = rateMap.get(pair);

        // 1. Если в кэше вообще ничего нет - ждем синхронно (холодный старт)
        if (keeper == null) {
            return updateSync(pair);
        }

        // 2. Если данные устарели, запускаем фоновое обновление
        if (isExpired(keeper)) {
            startBackgroundUpdate(pair);
        }

        // 3. ВСЕГДА возвращаем данные из кэша мгновенно (даже если они чуть устарели)
        return keeper.rate();
    }

    private void startBackgroundUpdate(CurrencyPair pair) {
        // Если пара уже в процессе обновления - пропускаем, чтобы не спамить API
        if (updatingPairs.add(pair)) {
            CompletableFuture.runAsync(() -> {
                try {
                    updateSync(pair);
                }
                finally {
                    updatingPairs.remove(pair);
                }
            }, executor);
        }
    }

    private BigDecimal updateSync(CurrencyPair pair) {
        // затычка вместо реального вызова rateApi.fetchRateFromProvider(pair.from(), pair.to())
        BigDecimal rate = BigDecimal.valueOf(1.0 + (Math.random() * 0.5));
        rateMap.put(pair, new RateKeeper(Instant.now(), rate));
        return rate;
    }

    private boolean isExpired(RateKeeper keeper) {
        return keeper.time().plusSeconds(60).isBefore(Instant.now());
    }
}
