package com.andrew;

public class HashMap<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private Node<K,V>[] buckets;

    public HashMap() {
        this.buckets = new Node[INITIAL_CAPACITY];
    }

    public HashMap(int capacity) {
        this.buckets = new Node[capacity];
    }

    public void put(K key, V value) {
        int hash = key.hashCode();
        int bucketIndex = Math.abs(hash % buckets.length);

        Node<K, V> current = buckets[bucketIndex];
        while (current != null) {
            if (current.getHash() == hash && current.getKey().equals(key)) {
                current.setValue(value);
                return;
            }
            current = current.getNext();
        }
        Node<K, V> newNode = new Node<>(key, value, hash, buckets[bucketIndex]);
        buckets[bucketIndex] = newNode;
    }

    public V get(K key) {
        int hash = key.hashCode();
        int bucketIndex = Math.abs(hash % buckets.length);

        Node<K, V> current = buckets[bucketIndex];
        while (current != null) {
            if (current.getHash() == hash && current.getKey().equals(key)) {
                return current.getValue();
            }
        }
        return null;
    }

    public void remove(K key) {
        int hash = key.hashCode();
        int bucketIndex = Math.abs(hash % buckets.length);

        Node<K, V> prev = null;
        Node<K, V> current = buckets[bucketIndex];
        while (current != null) {
            if (current.getHash() == hash && current.getKey().equals(key)) {
                if (prev != null) {
                    prev.setNext(current.getNext());
                    return;
                } else {
                    buckets[bucketIndex] = current.getNext();
                    return;
                }
            }
            prev =  current;
            current = current.getNext();
        }
    }
}
