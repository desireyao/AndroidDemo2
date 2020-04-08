package com.yaoh.JavaDemo.maptest;

import java.util.LinkedHashMap;

/**
 * @author yaoh
 * @date 2020/4/6 9:39 AM
 * @description TODO
 */
public class Main {

    public static void main(String[] args) {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>(8, 0.75f, true);
//        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);

        int value =  map.get(2);
        System.out.println("value: " + value + " map: " + map);
    }
}
