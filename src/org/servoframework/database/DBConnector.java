package org.servoframework.database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * Created by unidev on 2017. 7. 24..
 */
public class DBConnector {
    private int port = 27017;
    private String host = "localhost";
    private String dbname = "test";

    public DBConnector(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public DBConnector(String host, int port, String dbname) {
        this.host = host;
        this.port = port;
        this.dbname = dbname;
    }

    public DBConnector(String dbname) {
        this.dbname = dbname;
    }

    MongoClient mongoClient = new MongoClient(host, port);
    MongoDatabase database = mongoClient.getDatabase(dbname);
}
