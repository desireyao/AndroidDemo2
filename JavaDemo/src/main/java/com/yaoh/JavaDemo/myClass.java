package com.yaoh.JavaDemo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class myClass {

    private static final char hexDigits[] =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static void main(String[] args) {
//        int _major = (-1 & 0XFF << 8) + 0xFC;
//        System.out.print("_major: " + _major);

//        Thread mthread = new Thread(mTask);
//        mthread.start();
//        long curTime = System.currentTimeMillis();
//        while (System.currentTimeMillis() - curTime < 1000) {
//
//        }
//        mthread.interrupt();

//        System.out.println("Exception---------> isInterrupted: " + mthread.isInterrupted());
//
//        long curTime2 = System.currentTimeMillis();
//        while (System.currentTimeMillis() - curTime2 < 2500) {
//
//        }
//        System.out.println("Exception---------> isInterrupted: " + mthread.isInterrupted());

//         List array = new ArrayList();
//         array.add(1);
//         System.out.print(array.contains(1));


//         String host = "tcp://pg556p9.mqtt.iot.gz.baidubce.com:1883";
//         String host = "pg556p9.mqtt.iot.gz.baidubce.com";
//         System.out.print(GetInetAddress(host));

//        byte[] hexData = hexString2Bytes("192");
//        String data = bytes2HexString(hexData);
//        System.out.println(data);

        byte[] data = convertIP2Bytes("12.18.0");
        System.out.println(data[0]);
        System.out.println(data[1]);
        System.out.println(data[2]);
        System.out.println(data[3]);

    }

    /**
     * @param param
     * @return
     */
    public static byte[] convertIP2Bytes(String param) {
        String[] strings = param.split("\\.");
        if (strings == null || strings.length != 4) {
            return null;
        }

        byte[] byteDatas = new byte[4];
        for (int i = 0; i < 4; i++) {
            String string = strings[i];
            byte data = (byte) (Integer.parseInt(string) & 0xff);
            byteDatas[i] = data;
        }

//        LogTool.LogE_DEBUG(TAG, LogTool.LogBytes2Hex(byteDatas, "convertIP2Bytes--->"));
        return byteDatas;
    }

    public static byte[] hexString2Bytes(String hexString) {
        int len = hexString.length();
        if (len % 2 != 0) {
            hexString = "0" + hexString;
            len = len + 1;
        }
        char[] hexBytes = hexString.toUpperCase().toCharArray();
        byte[] ret = new byte[len >> 1];
        for (int i = 0; i < len; i += 2) {
            ret[i >> 1] = (byte) (hex2Int(hexBytes[i]) << 4 | hex2Int(hexBytes[i + 1]));
        }
        return ret;
    }

    private static int hex2Int(final char hexChar) {
        if (hexChar >= '0' && hexChar <= '9') {
            return hexChar - '0';
        } else if (hexChar >= 'A' && hexChar <= 'F') {
            return hexChar - 'A' + 10;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static String bytes2HexString(final byte[] bytes) {
        if (bytes == null) return null;
        int len = bytes.length;
        if (len <= 0) return null;
        char[] ret = new char[len << 1];
        for (int i = 0, j = 0; i < len; i++) {
            ret[j++] = hexDigits[bytes[i] >>> 4 & 0x0f];
            ret[j++] = hexDigits[bytes[i] & 0x0f];
        }
        return new String(ret);
    }


    public static String GetInetAddress(String host) {
        String IPAddress = "";
        InetAddress ReturnStr1 = null;
        try {
            ReturnStr1 = java.net.InetAddress.getByName(host);
            IPAddress = ReturnStr1.getHostAddress();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return IPAddress;
        }
        return IPAddress;
    }


    private static Runnable mTask = new Runnable() {
        @Override
        public void run() {
            while (true) {
                System.out.println("mTask---------> start"
                        + " isInterrupted: " + Thread.currentThread().isInterrupted()
                        + " Thread.currentThread: " + Thread.currentThread());
//                try {
//                    Thread.sleep(1000);
//                    System.out.println("mTask---------> "
//                            + " isInterrupted: " + Thread.currentThread().isInterrupted()
//                            + " Thread.currentThread: " + Thread.currentThread());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }


                long curTime = System.currentTimeMillis();
                while (System.currentTimeMillis() - curTime < 5000) {

                }

                System.out.println("mTask---------> 222"
                        + " isInterrupted: " + Thread.currentThread().isInterrupted()
                        + " Thread.currentThread: " + Thread.currentThread());
            }
//            System.out.println("mTask---------> start"
//                    + " isInterrupted: " + Thread.currentThread().isInterrupted()
//                    + " Thread.currentThread: " + Thread.currentThread());
        }
    };

}
