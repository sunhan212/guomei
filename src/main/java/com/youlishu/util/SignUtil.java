package com.youlishu.util;

import java.security.MessageDigest;
import java.util.Date;

/**
 * 签名工具类
 */
public class SignUtil {
    private static final String[] hex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    public static void main(String[] args) {

        //UUCS
        String appKey = "EnGfvA5wus";
        String appSecret = "ifVpv2Zde0NN9oiGk3ONWbJxNySRfWMY";
//        物联网
//        String appKey = "5ZopvhPjIn";
//        String appSecret = "x4KsEFSiO0MtjjfUmos7ZBbARyPGpjez";
        Date date = new Date();
        long time = date.getTime() /1000;
        StringBuffer sb = new StringBuffer(appKey).append(" ").append(appSecret).append(" ").append(time);
        System.out.println(sb.toString());
        System.out.print("md5: ");
        System.out.println(encode(sb.toString()));
    }
    public static String encode(String password) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] byteArray = md5.digest(password.getBytes("utf-8"));
            String passwordMD5 = byteArrayToHexString(byteArray);
            return passwordMD5;
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return password;
    }
    private static String byteArrayToHexString(byte[] byteArray) {
        StringBuffer sb = new StringBuffer();
        for (byte b : byteArray) {
            sb.append(byteToHexChar(b));
        }
        return sb.toString();
    }
    private static Object byteToHexChar(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hex[d1] + hex[d2];
    }
}
