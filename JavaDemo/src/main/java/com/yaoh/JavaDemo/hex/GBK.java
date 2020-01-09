package com.yaoh.JavaDemo.hex;

import java.io.UnsupportedEncodingException;

/**
 * Created by yaoh on 2019/6/17
 */
public class GBK {

    /**
     * @param code为汉字GBK编码
     * @return 返回偏移量
     * @author 小黄人软件
     * @see 非标准   xx7f 这个他用？来代替，不用减了。
     */
    public static int getGBKIndex2(int code) {
        //分为三块来减
        //1. 减0x8140前的所有
        //2. 减xx00-xx3F  0xxxff  共0x41个字节
        //3. xx7f 这个他用？来代替，不用减了。
        int temp = code - 0x8140 - (code / 256 - 0x81) * 0x41;
//		 if(code%256>0x7f)
//			 temp=temp-1;
        return temp;
    }

    /**
     * @param code为汉字GBK编码
     * @return 返回偏移量
     */
    public static int getGBKIndex(int code) {

        //分为三块来减
        //1. 减0x8140前的所有
        //2. 减xx00-xx3F xx7f  xxff  共0x42个
        //3. 低字节做判断  大于00x7f 减本次的1个xx7f
        int temp = code - 0x8140 - (code / 256 - 0x81) * 0x42;
        if (code % 256 > 0x7f)
            temp = temp - 1;
        return temp;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String str = "非";
        try {
            byte[] data = str.getBytes("GBK");
            int code = ((data[0] & 0xff) << 8) + (data[1] & 0xff);
            System.out.println("code: " + code + "," + String.format("%02X", code));

            int index = getGBKIndex(code);
            System.out.println("index: " + index);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


}
