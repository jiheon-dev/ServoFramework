package test.controllers;

import org.servoframework.annotation.Route;
import org.servoframework.request.Request;
import org.servoframework.response.Response;
import org.servoframework.util.Log;

/**
 * Created by unidev on 2017. 7. 4..
 */

public class MainRouter {
    private static final String TAG = "ServoFramework";

    @Route(route="/", method = Route.RouteMethod.GET)
    public static void index(Request req, Response res) {
        res.setHeader("Content-Type", "text/html");
        res.write("Hello World");
        res.end();
    }


    @Route(route="/home", method = Route.RouteMethod.POST)
    public static void home(Request req, Response res) {
        res.setHeader("Content-Type", "application/x-www-form-urlencoded");
        res.cookie("key", "home");
        res.write("Hello Home");
        Log.i(TAG, req.getQuery("name"));
        res.end();
    }
}
