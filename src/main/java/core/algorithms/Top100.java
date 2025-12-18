package core.algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Luxoft task
 */
public class Top100 {

    private static final int LIMIT = 100;
    // Минимальная куча: самый маленький элемент всегда на вершине
    private final PriorityQueue<Integer> minHeap = new PriorityQueue<>(LIMIT);
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    /**
     * Вызывается при поступлении нового элемента.
     * Сложность: O(log K), где K = 100.
     */
    public void onEvent(Integer number) {
        if (number == null) {
            return;
        }

        rwLock.writeLock().lock();
        try {
            if (minHeap.size() < LIMIT) {
                minHeap.add(number);
            }
            else if (number.compareTo(minHeap.peek()) > 0) {
                // Если новый элемент больше самого маленького из топ-100
                minHeap.poll(); // удаляем старый минимум
                minHeap.add(number);
            }
        }
        finally {
            rwLock.writeLock().unlock();
        }
    }

    /**
     * Возвращает 100 самых больших элементов, отсортированных по убыванию.
     * Сложность: O(K log K) из-за сортировки результата.
     */
    public Collection<Integer> getTop100() {
        rwLock.readLock().lock();
        try {
            List<Integer> result = new ArrayList<>(minHeap);
            result.sort(Collections.reverseOrder());
            return result;
        }
        finally {
            rwLock.readLock().unlock();
        }
    }
}


