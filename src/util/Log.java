package util;

/**
 * Created by unidev on 2017. 7. 4..
 *
 * This Class helps logging.
 * List : Debug, Info, Release, Error
 */

public class Log {

    public static void d(String tag, String msg) {
        logging("Debug/[" + tag + "]", msg);
    }

    public static void i(String tag, String msg) {
        logging("Info/[" + tag + "]", msg);
    }

    public static void r(String tag, String msg) {
        logging("Release/[" + tag + "]", msg);
    }

    public static void e(String tag, String msg) {
        logging("Error/[" + tag + "]", msg);
    }

    private static void logging(String tag, String msg) {
        System.out.println(tag + " : " + msg);
    }
}