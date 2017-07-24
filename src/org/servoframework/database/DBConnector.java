package org.servoframework.database;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

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

    public FindIterable<Document> findQuery(String collection, Document docs) {
        MongoCollection<Document> coll = database.getCollection(collection);
        return coll.find(docs);
    }

    public void insertQuery(String collection, Document docs) {
        MongoCollection<Document> coll = database.getCollection(collection);
        coll.insertOne(docs);
    }

    public void deleteQuery(String collection, Document docs) {
        MongoCollection<Document> coll = database.getCollection(collection);
        coll.deleteOne(docs);
    }

    public void updateQuery(String collection, Document docs, Document changeDocs) {
        MongoCollection<Document> coll = database.getCollection(collection);
        coll.updateOne(docs, changeDocs);
    }

}
