package com.andrew;

public class HashMap<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    private int size = 0;
    private int capacity;
    private Node<K,V>[] buckets;

    public HashMap() {
        this.buckets = new Node[INITIAL_CAPACITY];
        capacity = INITIAL_CAPACITY;
    }

    public HashMap(int capacity) {
        this.buckets = new Node[capacity];
        this.capacity = capacity;
    }

    public void put(K key, V value) {
        int hash = key.hashCode();
        int bucketIndex = Math.abs(hash % buckets.length);
        int limit = (int) (capacity * LOAD_FACTOR);
        if (size >= limit) {
            resize();
        }
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
        size++;
    }

    private void resize() {
        Node<K, V>[] newBuckets = new Node[capacity * 2];
        for (int i = 0; i < buckets.length; i++) {
            Node<K, V> current = buckets[i];
            while (current != null) {
                Node<K, V> next = current.getNext();
                int newBucketIndex = Math.abs(current.getHash() % newBuckets.length);

                current.setNext(newBuckets[newBucketIndex]);
                newBuckets[newBucketIndex] = current;
                current = next;
            }
        }
        capacity = newBuckets.length;
        buckets = newBuckets;
    }

    public V get(K key) {
        int hash = key.hashCode();
        int bucketIndex = Math.abs(hash % buckets.length);

        Node<K, V> current = buckets[bucketIndex];
        while (current != null) {
            if (current.getHash() == hash && current.getKey().equals(key)) {
                return current.getValue();
            }
            current = current.getNext();
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
                } else {
                    buckets[bucketIndex] = current.getNext();
                    return;
                }
                size--;
                return;
            }
            prev =  current;
            current = current.getNext();
        }
    }
}
