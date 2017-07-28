package org.servoframework.request;

import org.servoframework.annotation.Route;
import org.servoframework.util.URLDecode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by unidev on 2017. 7. 4..
 */
public class Request {
    private String path;
    private String version;

    private Route.RouteMethod method;
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

    public void setMethod(Route.RouteMethod method) {
        this.method = method;
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

    public void setQuery(Map<String, String> parameters) {
        for(String key :parameters.keySet()) {
            queryParameters.put(key, parameters.get(key));
        }
    }

    public void setParameters(Map<String, String> parameters) {
        for(String key :parameters.keySet()) {
            requestParameters.put(key, parameters.get(key));
        }
    }

    public void setCookieParameters(Map<String, String> cookieParameters) {
        for(String key :cookieParameters.keySet()) {
            this.cookieParameters.put(key, cookieParameters.get(key));
        }
    }

    public String getQuery(String key) {
        if(queryParameters.containsKey(key)) {
            return URLDecode.getResult(queryParameters.get(key));
        } else {
            return null;
        }
    }

    public String getParameter(String key) {
        if(method == Route.RouteMethod.GET) {
            return getQuery(key);
        }

        if(requestParameters.containsKey(key)) {
            return requestParameters.get(key);
        } else {
            return null;
        }
    }

    public String cookie(String key) {
        return cookieParameters.get(key);
    }

    public String getHeader(String key) {
        if(headerOptions.containsKey(key)) {
            return headerOptions.get(key);
        } else {
            return null;
        }
    }

    public String getPath() {
        return path;
    }

    public String getVersion() {
        return version;
    }
}
