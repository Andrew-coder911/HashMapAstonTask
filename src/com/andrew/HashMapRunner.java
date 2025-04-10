package com.andrew;

public class HashMapRunner {
    public static void main(String[] args) {
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<String, Integer>();
// Все попадут в buckets[0]
        stringIntegerHashMap.put("Aa", 10);
        stringIntegerHashMap.put("BB", 11);
        stringIntegerHashMap.put("Cc", 12);
// Все попадут в buckets[1]
        stringIntegerHashMap.put("Ad", 13);
        stringIntegerHashMap.put("BE", 14);
// Все попадут в buckets[2]
        stringIntegerHashMap.put("AF", 15);
        stringIntegerHashMap.put("BG", 16);

        stringIntegerHashMap.remove("Cc");

    }
}
