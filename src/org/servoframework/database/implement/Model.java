package org.servoframework.database.implement;

import org.servoframework.database.DBConnector;

/**
 * Created by unidev on 2017. 7. 24..
 */
public interface Model {
    public void init();
    public int count();
    public void close();
}
