package core.algorithms;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.tuple.Pair;

public class CurrencyExchanger {

    private final Map<Pair<String, String>, TreeMap<LocalDateTime, Double>> exchangeRates = new HashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public CurrencyExchanger() {
        startPeriodicRateUpdate();
    }

    private void addExchangeRate() {
        // имитация внешнего API
        addExchangeRate("USD", "BYN", 3.45 + Math.random() * 0.02, LocalDateTime.now());
        addExchangeRate("EUR", "BYN", 3.62 + Math.random() * 0.02, LocalDateTime.now());
        addExchangeRate("EUR", "USD", 1.18 + Math.random() * 0.02, LocalDateTime.now());
    }

    private void addExchangeRate(String fromCurrency, String toCurrency, double rate, LocalDateTime timestamp) {
        Pair<String, String> currencyPair = Pair.of(fromCurrency, toCurrency);
        exchangeRates
            .computeIfAbsent(currencyPair, k -> new TreeMap<>())
            .put(timestamp, rate);
    }

    public double getExchangeRate(String fromCurrency, String toCurrency, LocalDateTime timestamp) {
        Pair<String, String> currencyPair = Pair.of(fromCurrency, toCurrency);
        TreeMap<LocalDateTime, Double> rates = exchangeRates.get(currencyPair);

        if (rates == null || rates.isEmpty()) {
            throw new IllegalArgumentException("No exchange rates available for this currency pair.");
        }

        // Найти курс, актуальный для заданной временной метки
        Map.Entry<LocalDateTime, Double> entry = rates.floorEntry(timestamp);

        if (entry == null) {
            throw new IllegalStateException("No valid exchange rate found for the given time.");
        }

        return entry.getValue();
    }

    public double convert(String fromCurrency, String toCurrency, double amount, LocalDateTime timestamp) {
        double rate = getExchangeRate(fromCurrency, toCurrency, timestamp);
        return amount * rate;
    }

    private void startPeriodicRateUpdate() {
        scheduler.scheduleAtFixedRate(
            this::addExchangeRate,
            0,
            2,
            TimeUnit.SECONDS
        );
    }

    public void stopService() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            scheduler.shutdownNow();
        }
    }

    public static void case1() throws InterruptedException {
        CurrencyExchanger exchanger = new CurrencyExchanger();
        exchanger.addExchangeRate("USD", "BYN", 3.45, LocalDateTime.of(2025, 1, 25, 13, 0));
        exchanger.addExchangeRate("USD", "BYN", 3.47, LocalDateTime.of(2025, 1, 25, 17, 30));
        exchanger.addExchangeRate("USD", "BYN", 3.41, LocalDateTime.of(2025, 1, 26, 10, 15));
        exchanger.addExchangeRate("BYN", "USD", 0.29, LocalDateTime.of(2025, 1, 25, 13, 0));
        exchanger.addExchangeRate("EUR", "BYN", 3.62, LocalDateTime.of(2025, 1, 25, 13, 0));
        exchanger.addExchangeRate("BYN", "EUR", 0.27, LocalDateTime.of(2025, 1, 25, 13, 0));
        exchanger.addExchangeRate("USD", "EUR", 0.85, LocalDateTime.of(2025, 1, 25, 13, 0));
        exchanger.addExchangeRate("EUR", "USD", 1.18, LocalDateTime.of(2025, 1, 25, 13, 0));

        Thread.sleep(10000);

        exchanger.stopService();

        double convertedAmount = exchanger.convert("USD", "BYN", 100, LocalDateTime.of(2025, 1, 25, 18, 0));
        System.out.println(convertedAmount);
    }

}
