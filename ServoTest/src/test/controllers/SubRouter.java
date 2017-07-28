package test.controllers;

import org.servoframework.annotation.Route;
import org.servoframework.request.Request;
import org.servoframework.response.Response;
import test.models.User;

import java.util.HashMap;

/**
 * Created by unidev on 2017. 7. 4..
 */

public class SubRouter {
    private static User user = new User();
    @Route(route="/sub", method = Route.RouteMethod.GET)
    public static void index(Request req, Response res) {
        res.setHeader("Content-Type", "text/html; charset=utf-8");
        user.init();

        System.out.println(req.getParameter("name"));
        String result = user.findUser(req.getParameter("name"));

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

    @Route(route="/count", method = Route.RouteMethod.GET)
    public static void count(Request req, Response res) {
        res.setHeader("Content-Type", "text/html");
        user.init();

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        map.put("result", user.count());
        res.json(map);

        user.close();
        res.end();
    }
}
