package core.exercise.transactionmanager.hashmapimpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class DataCommand {

    private final Map<String, Integer> elements = new HashMap<>();
    private final Stack<Map<String, Integer>> transactionStack = new Stack<>();
    private final Map<Integer, Integer> counter = new HashMap<>();
    private final Stack<Map<Integer, Integer>> counterCacheStack = new Stack<>();

    public void set(String name, int value) {
        Integer oldValue = get(name);
        if (transactionStack.isEmpty()) {
            elements.put(name, value);
        }
        else {
            transactionStack.peek().put(name, value);
        }
        if (oldValue != null) {
            decrementCounter(oldValue);
        }
        incrementCounter(value);
    }

    public Integer get(String name) {
        for (int i = transactionStack.size() - 1; i >= 0; i--) {
            Map<String, Integer> transaction = transactionStack.get(i);
            if (transaction.containsKey(name)) {
                return transaction.get(name);
            }
        }
        return elements.getOrDefault(name, null);
    }

    public void delete(String name) {
        Integer oldValue = get(name);
        if (oldValue != null) {
            if (transactionStack.isEmpty()) {
                elements.remove(name);
            }
            else {
                transactionStack.peek().put(name, null);
            }
            decrementCounter(oldValue);
        }
    }

    public void count(int value) {
        System.out.println(counter.getOrDefault(value, 0));
    }

    public void begin() {
        transactionStack.push(new HashMap<>());
        counterCacheStack.push(new HashMap<>(counter));
    }

    public void rollback() {
        if (!transactionStack.isEmpty()) {
            transactionStack.pop();
            counter.clear();
            counter.putAll(counterCacheStack.pop());
        }
        else {
            System.out.println("NO TRANSACTION");
        }
    }

    public void commit() {
        if (transactionStack.isEmpty()) {
            System.out.println("NO TRANSACTION");
            return;
        }

        for (Map<String, Integer> currentTransaction : transactionStack) {
            for (Map.Entry<String, Integer> entry : currentTransaction.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                if (value == null) {
                    elements.remove(key);
                }
                else {
                    elements.put(key, value);
                }
            }
        }
        transactionStack.clear();
        counterCacheStack.clear();
    }

    private void incrementCounter(int value) {
        counter.put(value, counter.getOrDefault(value, 0) + 1);
    }

    private void decrementCounter(int value) {
        counter.put(value, counter.getOrDefault(value, 0) - 1);
        if (counter.get(value) <= 0) {
            counter.remove(value);
        }
    }

}
