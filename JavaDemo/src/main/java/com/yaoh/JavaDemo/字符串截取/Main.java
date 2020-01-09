package com.yaoh.JavaDemo.字符串截取;

import org.apache.http.util.TextUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yaoh
 * @date Create in 2019-11-26 09:20
 * @description TODO
 */
public class Main {

    public static void main(String[] args) {
        String str = "11哈哈哈楼传感器22号";
        str = str.trim();
        String str2 = "";

        if (!TextUtils.isEmpty(str)) {

            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                    str2 += str.charAt(i);
                }
            }
        }
        System.out.println(str2);


        System.out.println("getDeviceNumber------> " + getDeviceNumber(str));
    }


    //截取数字
    public static int getDeviceNumber(String content) {
        if (TextUtils.isEmpty(content)) {
            return -1;
        }

        if (content.lastIndexOf("号") != content.length() - 1) {
            return -1;
        }

        LinkedList<String> nums = new LinkedList<>();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            nums.add(matcher.group());
        }

        if (nums.isEmpty()) {
            return -1;
        }

        int deviceNumber = Integer.parseInt(nums.getLast());
        System.out.println("deviceNumber-------->" + deviceNumber);
        return deviceNumber;
    }

}
