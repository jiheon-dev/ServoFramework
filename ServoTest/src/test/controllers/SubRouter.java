package test.controllers;

import org.bson.Document;
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
    private static User user = new User();
    @Route(route="/sub", method = Route.RouteMethod.GET)
    public static void index(Request req, Response res) {
        res.setHeader("Content-Type", "text/html");
        user.init();

        System.out.println(req.getQuery("name"));
        String result = user.findUser(req.getQuery("name"));

        System.out.println(result);

        res.write(result);
        user.close();
        res.end();
    }

    @Route(route="/update", method = Route.RouteMethod.GET)
    public static void update(Request req, Response res) {
        res.setHeader("Content-Type", "text/html");
        user.init();

        user.updateUser("name", "","");
        res.write("success: true");
        user.close();
        res.end();
    }
}
