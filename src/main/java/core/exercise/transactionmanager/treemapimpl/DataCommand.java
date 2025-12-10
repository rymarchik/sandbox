package core.exercise.transactionmanager.treemapimpl;

import java.util.Map;
import java.util.TreeMap;

public class DataCommand {

    private final TreeMap<String, Integer> elements = new TreeMap<>();
    private Transaction currentTransaction = null;

    public void set(String name, int value) {
        if (currentTransaction == null) {
            elements.put(name, value);
        } else {
            currentTransaction.set(name, value);
        }
    }

    public Integer get(String name) {
        if (currentTransaction == null) {
            return elements.getOrDefault(name, null);
        } else {
            return currentTransaction.get(name, elements);
        }
    }

    public void delete(String name) {
        if (currentTransaction == null) {
            elements.remove(name);
        } else {
            currentTransaction.set(name, null);
        }
    }

    public void count(int value) {
        if (currentTransaction == null) {
            long count = elements.values().stream().filter(v -> v == value).count();
            System.out.println(count);
        } else {
            System.out.println(currentTransaction.count(value, elements));
        }
    }

    public void begin() {
        currentTransaction = new Transaction(currentTransaction);
    }

    public void rollback() {
        if (currentTransaction == null) {
            System.out.println("NO TRANSACTION");
        } else {
            currentTransaction = currentTransaction.parent;
        }
    }

    public void commit() {
        if (currentTransaction == null) {
            System.out.println("NO TRANSACTION");
            return;
        }

        currentTransaction.commit(elements);
        currentTransaction = null;
    }

    private static class Transaction {
        private final Transaction parent;
        private final TreeMap<String, Integer> changes = new TreeMap<>();

        public Transaction(Transaction parent) {
            this.parent = parent;
        }

        public void set(String name, Integer value) {
            changes.put(name, value);
        }

        public Integer get(String name, TreeMap<String, Integer> base) {
            if (changes.containsKey(name)) {
                return changes.get(name);
            }
            return parent != null ? parent.get(name, base) : base.get(name);
        }

        public long count(int value, TreeMap<String, Integer> base) {
            // работает некорректно, нужно исключить дублирование проверки значений на уровне транзакций и в базовой структуре
            long count = changes.values().stream().filter(v -> v != null && v == value).count();
            if (parent != null) {
                count += parent.count(value, base);
            }
            count += base.values().stream().filter(v -> v == value).count();
            return count;
        }

        public void commit(TreeMap<String, Integer> base) {
            if (parent != null) {
                parent.commit(base);
            }
            for (Map.Entry<String, Integer> entry : changes.entrySet()) {
                if (entry.getValue() == null) {
                    base.remove(entry.getKey());
                } else {
                    base.put(entry.getKey(), entry.getValue());
                }
            }
        }
    }
}
