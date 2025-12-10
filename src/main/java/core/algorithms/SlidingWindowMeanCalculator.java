package core.algorithms;

import java.time.Instant;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Создать структуру данных, которая может принимать (accept) поток числовых значений (number) с временными метками и в любой момент
 * времени возвращать среднее арифметическое (mean) только тех значений, которые поступили в течение последних 300 секунд (5 минут).
 */
public class SlidingWindowMeanCalculator {

    private static final long TIME_PERIOD_IN_SECONDS = 300;
    private final Deque<TimestampedValue> queue = new LinkedList<>();
    private long sum = 0;

    public void accept(int number) {
        Instant now = Instant.now();
        queue.addLast(new TimestampedValue(now, number));
        sum += number;
    }

    public double mean() {
        Instant now = Instant.now();
        while (!queue.isEmpty() && queue.peekFirst().instant.plusSeconds(TIME_PERIOD_IN_SECONDS).isBefore(now)) {
            sum -= queue.pollFirst().number;
        }
        return queue.isEmpty() ? 0 : (double) sum / queue.size();
    }

    private record TimestampedValue(Instant instant, int number) {
    }

}
