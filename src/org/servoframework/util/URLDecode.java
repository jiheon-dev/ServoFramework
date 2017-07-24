package org.servoframework.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by unidev on 2017. 7. 24..
 */
public class URLDecode {
    private String decodingString;
    private String charsetName = "UTF-8";
    private String result;

    public URLDecode(String decodingString) {
        try {
            String result = URLDecoder.decode(decodingString, charsetName);
            result = this.result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getResult() {
        return this.result;
    }
}
