package com.yaoh.JavaDemo.bytebuffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author yaoh
 * @date Create in 2019-12-23 10:16
 * @description TODO
 */
public class Main {

    public static void main(String[] args) {
        int data = 1000;
        byte[] tmp = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(data).array();
        System.out.println("tmp-------> " + ConvertUtils.bytes2HexString(tmp));

        byte xData = -1 | 0x10;
        System.out.println("xData-------> " + xData);
        System.out.println("111-------> " + String.format("%02X",-1));
        System.out.println("222-------> " + String.format("%02X",1));

    }


}
