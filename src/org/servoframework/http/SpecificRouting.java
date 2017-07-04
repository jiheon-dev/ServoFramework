package org.servoframework.http;

import org.servoframework.Servo;
import org.servoframework.annotation.Route;
import org.servoframework.request.Request;
import org.servoframework.response.Response;

/**
 * Created by unidev on 2017. 7. 4..
 */

public class SpecificRouting {
    @Route(route = "/404")
    public static void notFound(Request req, Response res) {
        res.setHeader("Content-Type", "text/html");

        res.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>404 Not Found</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <center>\n" +
                "        <h1>\n" +
                "            <strong>\n" +
                "                404 Not Found\n" +
                "            </strong>\n" +
                "        </h1>\n" +
                "        <hr>\n" +
                "        <p>\n" +
                "            Servo/" + Servo.VersionName + "\n" +
                "        </p>\n" +
                "    </center>\n" +
                "</body>\n" +
                "</html>");
        res.end();
    }

    @Route(route="/500")
    public static void internalServerError(Request req, Response res) {
        res.setHeader("Content-Type", "text/html");
        res.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>500 Internal Server Error</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <center>\n" +
                "        <h1>\n" +
                "            <strong>\n" +
                "                500 Internal Server Error\n" +
                "            </strong>\n" +
                "        </h1>\n" +
                "        <hr>\n" +
                "        <p>\n" +
                "            The Server encountered an internal error or misconfiguration and was unable to complete your request.\n" +
                "        </p>\n" +
                "        <p>\n" +
                "            Please contact the server administrator, and inform them of the time the error occurred, and anything you might have done that may have caused the error.\n" +
                "        </p>\n" +
                "        <p>\n" +
                "            More information about this error may be available in the server error log\n" +
                "        </p>\n" +
                "        <hr>\n" +
                "        <p>\n" +
                "            Servo/" + Servo.VersionName + "\n" +
                "        </p>\n" +
                "    </center>\n" +
                "</body>\n" +
                "</html>");
        res.end();
    }
}
