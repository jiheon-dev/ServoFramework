package org.servoframework.database.implement;

import org.servoframework.database.DBConnector;

/**
 * Created by unidev on 2017. 7. 24..
 */
public interface Model {
    public void init();
    public String find(String name);
    public void insert(String name);
    public void update(String fieldName, Object value, Object newValue);
    public void delete(String name);
    public int count();
    public void close();
}
