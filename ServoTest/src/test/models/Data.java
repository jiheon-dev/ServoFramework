package test.models;

import com.mongodb.client.MongoCollection;
import org.servoframework.database.DBConnector;

/**
 * Created by unidev on 2017. 7. 24..
 */
public class Data implements Model {
    DBConnector connector = new DBConnector("test_db");

    @Override
    public void init() {
        connector.connect();
    }

    @Override
    public void close() {
        connector.close();
    }
}
