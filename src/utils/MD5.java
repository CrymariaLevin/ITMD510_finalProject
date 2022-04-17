/**
 * Final Project for ITMD510.
 * Program to create a account book app to store and operate data with different users's privilege through database, display with JavaFX.
 * @author: Li Mingyi Student, File Name: MD5.java
 * This file is to transfer a String password to a MD5 type String as an encryption
 */

package utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5 {
    /**
     * Get encrypted str in MD5 encryption
     *
     * @param str the String needed to be encrypted
     * @return String the encrypted str
     */
    public static String getMD5String(String str) {
        try {
            // create MessageDigest object
            MessageDigest md = MessageDigest.getInstance("MD5");
            // calculate the md5 function
            md.update(str.getBytes());
            // digest() return md5's  hash value (8 bit)
            // BigInteger turn 8 bit str into 16 bit hex's hash value
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    public static void main(String[] args) {
//        String pwd = getMD5String("580225");
//        System.out.println(pwd);
//        System.out.println(pwd.length());
//    }
}
