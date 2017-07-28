package test.models;

import org.bson.Document;
import org.servoframework.database.DBConnector;
import org.servoframework.database.implement.Model;
import test.config.Config;

/**
 * Created by unidev on 2017. 7. 24..
 */
public class User implements Model {
    private static DBConnector connector = new DBConnector(Config.DB_NAME);;
    private static String collection = Config.collection[0];

    @Override
    public void init() {
        connector.connect();
    }

    @Override
    public void insert(String name) {
        connector.insertQuery(collection, new Document("name", name));
    }

    @Override
    public String find(String name) {
        return connector.findQuery(collection, new Document("name", name));
    }

    @Override
    public void update(String fieldName, Object value, Object newValue) {
        connector.updateQuery(collection, fieldName, value, newValue);
    }

    @Override
    public void delete(String name) {

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
