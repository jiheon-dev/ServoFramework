package test.controllers;

import org.servoframework.annotation.Route;
import org.servoframework.request.Request;
import org.servoframework.response.Response;

/**
 * Created by unidev on 2017. 7. 4..
 */

public class MainRouter {
    @Route(route="/", method = Route.RouteMethod.GET)
    public static void index(Request req, Response res) {
        res.setHeader("Content-Type", "text/html");
        res.write("Hello World");
        res.end();
    }
}
