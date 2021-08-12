package utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SHA256 {

    private static final String algorithm = "SHA-256";

    public static String getSHA(String input){

        String encryptedPassword = null;

        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);

            BigInteger number = new BigInteger(1, md.digest(input.getBytes(StandardCharsets.UTF_8)));
            StringBuilder hexString = new StringBuilder(number.toString(16));

            while (hexString.length() < 32) {
                hexString.insert(0, '0');
            }

            encryptedPassword = hexString.toString();

        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        return encryptedPassword;
    }

}


