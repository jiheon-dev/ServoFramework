package org.servoframework.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by unidev on 2017. 7. 24..
 */
public class URLDecode {
    public static String getResult(String decodingString) {
        String result = null;
        try {
            result = URLDecoder.decode(decodingString, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
