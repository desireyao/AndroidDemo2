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
    }


}
