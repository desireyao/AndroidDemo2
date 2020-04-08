package com.yaoh.JavaDemo.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author yaoh
 * @date 2020-03-27 13:09
 * @description TODO
 */
public class Main {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Optional<List<String>> data = Optional.ofNullable(list);
//        Optional<String> data = new ArrayList<>();
        System.out.println("isPresent =====> " + data.get());
    }

}
