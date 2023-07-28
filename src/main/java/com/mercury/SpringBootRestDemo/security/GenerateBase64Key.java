package com.mercury.SpringBootRestDemo.security;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class GenerateBase64Key {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA512");
        SecretKey secretKey = keyGenerator.generateKey();
        String base64EncodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        System.out.println("Base64 Encoded Secret Key: " + base64EncodedSecretKey);
    }
}
