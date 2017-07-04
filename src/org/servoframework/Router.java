package org.servoframework;

import org.servoframework.annotation.Route;

import java.lang.reflect.Method;

/**
 * Created by unidev on 2017. 7. 4..
 */

public class Router {
    public Method method;
    public String path;
    public Route.RouteMethod routeMethod;

    public Router() {}
    public Router(Method method, String path, Route.RouteMethod routeMethod) {
        this.method = method;
        this.path = path;
        this.routeMethod = routeMethod;
    }
}
