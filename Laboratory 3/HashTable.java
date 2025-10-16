import java.util.LinkedList;

public class HashTable<K, V> {

    private static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    private static final int DEFAULT_CAPACITY = 10;

    private LinkedList<Entry<K, V>>[] table;

    private int size = 0;

    @SuppressWarnings("unchecked")
    public HashTable() {
        table = new LinkedList[DEFAULT_CAPACITY];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % table.length);
    }

    public void put(K key, V value) {
        int index = hash(key);

        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        for (Entry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }

        table[index].add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = hash(key);

        if (table[index] != null) {
            for (Entry<K, V> entry : table[index]) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }

        return null;
    }

    public void remove(K key) {
        int index = hash(key);

        if (table[index] != null) {
            for (Entry<K, V> entry : table[index]) {
                if (entry.getKey().equals(key)) {
                    table[index].remove(entry);
                    size--;
                    return;
                }
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (LinkedList<Entry<K, V>> bucket : table) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    sb.append(entry.getKey()).append(" = ").append(entry.getValue()).append("\n");
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        HashTable<String, Integer> table = new HashTable<>();

        table.put("apple", 5);
        table.put("banana", 3);
        table.put("orange", 7);
        table.put("banana", 10);

        System.out.println("banana ключ: " + table.get("banana"));
        System.out.println("size = " + table.size());
        System.out.println("isEmpty = " + table.isEmpty());

        table.remove("orange");

        System.out.println("После удаления orange:");
        System.out.println(table);
    }
}
