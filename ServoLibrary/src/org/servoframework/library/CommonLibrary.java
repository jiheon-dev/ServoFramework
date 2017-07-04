package org.servoframework.library;

/**
 * Created by unidev on 2017. 7. 4..
 *
 * Define Library's executing method.
 */

public interface CommonLibrary extends ServoLibrary {

    /**
     * This method will be execute Library's main function.
     *
     * @param objects parameters that Library requires
     */

    Object execute(Object ... objects);
}
