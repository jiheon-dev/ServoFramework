package test.models;

import org.bson.Document;
import org.servoframework.database.DBConnector;

/**
 * Created by unidev on 2017. 7. 24..
 */
public class User {
    public static void init() {
        DBConnector connector = new DBConnector("test_db");
        connector.connect();
        connector.insertQuery("user", new Document("name", "Smaker"));
    }
}
