package org.servoframework.annotation;

import java.lang.annotation.*;

/**
 * Created by unidev on 2017. 7. 4..
 */

@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Route {
    enum RouteMethod {ALL, GET, POST, ETC}          // define METHOD

    String route();                                 // where is ROUTED PATH
    RouteMethod method() default RouteMethod.ALL;   // METHOD
}
