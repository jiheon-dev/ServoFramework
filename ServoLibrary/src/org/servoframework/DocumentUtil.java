package org.servoframework;

/**
 * Created by unidev on 2017. 7. 4..
 * @author unidev
 *
 * This class will help you set/get document setting.
 * More functions will be appneded in future.
 */

import java.io.File;

public class DocumentUtil {

    /**
     * Get File Path
     *
     * @param path path in views path
     * @return file path. If this method returns null, the file doesn't exist.
     */

    public static String getFilePath(String path) {
        Preferences p = Preferences.getInstance();
        String viewsPath = (String) p.get("view");
        File f;
        if (viewsPath == null) {
            f = new File(path);
        } else {
            if (viewsPath.indexOf(viewsPath.length() - 1) != '/') {
                viewsPath += '/';
            }
            f = new File(viewsPath + path);
            if (!f.exists())
                f = new File(path);
        }

        if (!f.exists()) {
            return null;
        }

        return f.getPath();
    }
}