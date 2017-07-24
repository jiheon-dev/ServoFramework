package test.models;

import org.bson.Document;
import org.servoframework.database.DBConnector;

/**
 * Created by unidev on 2017. 7. 24..
 */
public class User implements Model {
    private static DBConnector connector;

    @Override
    public void init() {
        connector = new DBConnector("test_db");
        connector.connect();
    }

    public void insertUser(String name) {
        connector.insertQuery("user", new Document("name", name));
    }

    public String findUser(String name) {
        String result = connector.findQuery("user", new Document("name", name));
        return result;
    }

    public void updateUser(String fieldName, Object value, Object newValue) {
        connector.updateQuery("user", fieldName, value, newValue);
    }

    @Override
    public void close() {
        connector.close();
    }
}
