package com.grup15.gtuticaret;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class Sha256hash {
    private static final Random RANDOM = new SecureRandom();
    public static String generate(String input) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA256");
        md.reset();
        byte[] buffer = input.getBytes("UTF-8");
        md.update(buffer);
        byte[] digest = md.digest();

        String hexStr = "";
        for (int i = 0; i < digest.length; i++) {
            hexStr +=  Integer.toString( ( digest[i] & 0xff ) + 0x100, 16).substring( 1 );
        }
        return hexStr;
    }
    public static String getNextSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return salt.toString();
    }
}
