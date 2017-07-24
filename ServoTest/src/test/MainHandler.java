package test;

import org.servoframework.Servo;
import test.controllers.MainRouter;
import test.controllers.SubRouter;
import test.models.User;

/**
 * Created by unidev on 2017. 7. 4..
 */

public class MainHandler {
    public static void main(String [] args) throws InterruptedException {
        Servo servo = new Servo();

        servo.addController(MainRouter.class);
        servo.addController(SubRouter.class);
        User.init();
        servo.startServer(7000);
    }
}
