package org.servoframework.library;

import org.servoframework.library.ServoLibrary;

import java.util.Map;

/**
 * Created by unidev on 2017. 7. 4..
 *
 * This class will help you writing rendering class.
 */

public interface Renderer extends ServoLibrary {

    /**
     *
     * @param path File path Before Rendering
     * @return Rendered Content
     */

    String render(String path, Map<String, ?> params);
}
