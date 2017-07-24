package test.controllers;

import org.servoframework.annotation.Route;
import org.servoframework.database.DBConnector;
import org.servoframework.request.Request;
import org.servoframework.response.Response;
import test.models.User;

/**
 * Created by unidev on 2017. 7. 4..
 */

public class SubRouter {
    @Route(route="/sub", method = Route.RouteMethod.GET)
    public static void index(Request req, Response res) {
        res.setHeader("Content-Type", "text/html");
        String result = User.findUser(req.getQuery("name"));
        res.write(result);
        res.end();
    }
}
