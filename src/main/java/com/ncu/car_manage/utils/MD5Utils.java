package com.ncu.car_manage.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    public static String getMD5Str(String pwd, String salt) {
        String newstr = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            pwd += Base64.
                    encodeBase64String(md5.digest(salt.getBytes()));
            newstr = Base64.
                    encodeBase64String(md5.digest(pwd.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return newstr;
    }
}
