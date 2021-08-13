package utils;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;


public class SHA256 {

    public static String getSHA(String originalString){

        return Hashing.sha256()
                .hashString(originalString, StandardCharsets.UTF_8)
                .toString();

    }

}


