package test.models;

import org.bson.Document;
import org.servoframework.database.DBConnector;
import org.servoframework.database.implement.Model;

/**
 * Created by unidev on 2017. 7. 24..
 */
public class User implements Model {
    private static DBConnector connector;
    private static String collection = "user";

    @Override
    public void init() {
        connector = new DBConnector("test_db");
        connector.connect();
    }

    public void insertUser(String name) {
        connector.insertQuery(collection, new Document("name", name));
    }

    public String findUser(String name) {
        return connector.findQuery(collection, new Document("name", name));
    }

    public void updateUser(String fieldName, Object value, Object newValue) {
        connector.updateQuery(collection, fieldName, value, newValue);
    }

    @Override
    public int count() {
        return connector.countQuery(collection);
    }

    @Override
    public void close() {
        connector.close();
    }
}
