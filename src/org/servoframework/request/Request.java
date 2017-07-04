package org.servoframework.request;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by unidev on 2017. 7. 4..
 */
public class Request {
    private String path;
    private String version;

    private Map<String, String> headerOptions;
    private Map<String, String> requestParameters;
    private Map<String, String> queryParameters;
    private Map<String, String> cookieParameters;

    /**
     *  Constuctor
     */

    public Request() {
        headerOptions = new HashMap<String, String>();
        requestParameters = new HashMap<>();
        queryParameters = new HashMap<>();
        cookieParameters = new HashMap<>();
    }

    /**
     *  Set Path
     *  @param method HTTP method
     */

    public void setMethod(String method) {

    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void addHeader(String key, String value) {
        headerOptions.put(key, value);
    }
    
    public void addCookie(String key, String value) {
        cookieParameters.put(key, value);
    }


}
