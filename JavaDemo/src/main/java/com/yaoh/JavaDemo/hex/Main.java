package com.yaoh.JavaDemo.hex;

import java.io.UnsupportedEncodingException;

import static java.lang.String.format;

/**
 * Created by yaoh on 2019/1/14.
 */

public class Main {

    public static void main(String[] args) {

//        byte i = -1;
//        System.out.print("data: " + String.format("%02x",i));
//
//        int x = -124;
//        byte hex_x = (byte) (x & 0xff);
//        System.out.println(String.format("%02x",hex_x));
//
//        byte[] recvData = new byte[]{(byte) 0x84, (byte) 0x84};
//        System.out.println("parserTypeInt: " + parserTypeInt(recvData));
//
//        int deviceType = (recvData[0]) | ((recvData[1] & 0xff) << 8);
//        System.out.println("deviceType: " +deviceType);

//        int n = 0xffff;
//        int h = (byte) ((n >> 8) & 0xff);
//
//        byte s = (byte) 0xdd;
////        byte l = (byte) (s & 0xff);
////        int l = s << 8 & 0xffff;
//        int l = (s & 0xff) << 8;
//        System.out.println("h--->" + h + " l---> " + l);
//
//        int x = 500;
//        System.out.println(Integer.toHexString(x));


//        byte a = (byte) (-3 & 0xff);
        byte a = (byte) (0xfd);
        System.out.println(String.format("%02X", a));
        System.out.println(String.format("%02d", a));

//        String text = "你";
//        System.out.println(encode(text));
//        System.out.println(Integer.toHexString(text.charAt(0)));
//
//        try {
////            byte[] data = text.getBytes("GBK");
//            byte[] data = text.getBytes("unicode");
//            System.out.println("data.length:" + data.length);
//            for (int i = 0; i < data.length; i++) {
//                System.out.println(format("%02x", data[i]));
//            }
//
////            System.out.println(String.format("%02x",data[2]));
////            System.out.println(String.format("%02x",data[3]));
////            System.out.println(String.format("%02x",data[4]));
////            System.out.println(String.format("%02x",data[5]));
////            System.out.println(String.format("%02x",data[6]));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

    }

    public static String encode(String str) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            String code = format(Integer.toHexString(c));
            unicode.append(code);
        }
        return unicode.toString();
    }


    private static int parserTypeInt(byte[] recvData) {
        int result = 0;
        for (int i = 0; i < recvData.length; i++) {
            result |= ((recvData[i] & 0xff) << (8 * i));
        }
        return result;
    }


    private void hexString(String s) {
        s.getBytes();
    }

}
