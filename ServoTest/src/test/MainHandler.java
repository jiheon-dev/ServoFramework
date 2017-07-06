package test;

import org.servoframework.Servo;
import test.controllers.MainRouter;

/**
 * Created by unidev on 2017. 7. 4..
 */

public class MainHandler {
    public static void main(String [] args) {
        Servo servo = new Servo();
        servo.addController(MainRouter.class);
        servo.startServer(8080);
    }
}
