package test.models;

import org.bson.Document;
import org.servoframework.database.DBConnector;
import org.servoframework.database.implement.Model;
import test.config.Config;

/**
 * Created by unidev on 2017. 7. 24..
 */
public class Data implements Model {
    private static DBConnector connector = new DBConnector(Config.DB_NAME);
    private static String collection = Config.collection[1];


    @Override
    public void init() {
        connector.connect();
    }

    @Override
    public String find(String name) {
        return null;
    }

    @Override
    public void insert(String name) {

    }

    @Override
    public void update(String fieldName, Object value, Object newValue) {

    }

    @Override
    public void delete(String name) {

    }

    @Override
    public int count() { return connector.countQuery(collection); }

    @Override
    public void close() {
        connector.close();
    }
}
