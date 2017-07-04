package org.servoframework;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by unidev on 2017. 7. 4..
 * @author unidev
 *
 * Just call getInstance and call what you want.
 */

public class Preferences {
    private static Preferences preferences;
    private Map<String, Object> values;

    private Preferences() {
        values = new HashMap<>();
    }

    /**
     * I realize this class to single ton.
     * @return preferences
     */
    public static Preferences getInstance() {
        if(preferences == null) {
            preferences = new Preferences();
            return preferences;
        } else {
            return preferences;
        }
    }

    /**
     * Get value
     * @param key value's key
     * @return value
     */
    public Object get(String key) {
        return values.get(key);
    }

    /**
     * put datum
     * @param key datum's key
     * @param object datum
     */
    public void put(String key, Object object) {
        values.put(key, object);
    }
}
