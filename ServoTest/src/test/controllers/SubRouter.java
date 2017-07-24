package test.controllers;

import org.servoframework.annotation.Route;
import org.servoframework.database.DBConnector;
import org.servoframework.http.SpecificRouting;
import org.servoframework.request.Request;
import org.servoframework.response.Response;
import org.servoframework.util.URLDecode;
import test.models.User;

/**
 * Created by unidev on 2017. 7. 4..
 */

public class SubRouter {
    @Route(route="/sub", method = Route.RouteMethod.GET)
    public static void index(Request req, Response res) {
        res.setHeader("Content-Type", "text/html");
        User user = new User();
        user.init();

        System.out.println(req.getQuery("name"));
        String result = user.findUser(req.getQuery("name"));

        System.out.println(result);

        res.write(result);
        User.close();
        res.end();
    }
}
