package com.yaoh.JavaDemo.json;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by yaoh on 2019/7/16
 */
public class Main {

    private static Map<String, String> mMap = new HashMap<>();

    public static void main(String[] args) {
        JsonObject jo = new JsonObject();
        for (int i = 0; i < 1500; i++) {
            JsonObject jo_data = new JsonObject();
            jo_data.addProperty("networkId", "10006F");
            jo_data.addProperty("groupId", 57);
            jo_data.addProperty("locationInsVal", "45540501a300d700");
            jo_data.addProperty("locateOnOff", 1);
            String mac = randomHexString(12);
            jo.add(mac, jo_data);
            mMap.put(mac,mac);
        }
        System.out.println(jo.toString() + " size: " + mMap.size());

    }


    public static String randomHexString(int len) {
//        try {
//            StringBuffer result = new StringBuffer();
//            for (int i = 0; i < len; i++) {
//                result.append(Integer.toHexString(new Random().nextInt(16)));
//            }
//            return result.toString().toUpperCase();
//
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//
//        }
//        return null;

//        String s = "";
//        for (int i = 0; i < 5; i++) {
//            s = UUID.randomUUID().toString();
//            s = s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24); 
//            System.out.println(s.substring(0, 12));
//        }

        String s = UUID.randomUUID().toString();
        s = s.substring(0, 8)
                + s.substring(9, 13)
                + s.substring(14, 18)
                + s.substring(19, 23)
                + s.substring(24);
        s = s.substring(0, 12).toUpperCase();
        return s;

    }
}
