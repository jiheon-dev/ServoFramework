package org.servoframework.database;

import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by unidev on 2017. 7. 24..
 */
public class DBConnector {
    private int port = 27017;
    private String host = "localhost";
    private String dbname = "test";
    MongoClient mongoClient;
    MongoDatabase database;

    public DBConnector() {}

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

    public void connect() {
        mongoClient = new MongoClient(host, port);
        database = mongoClient.getDatabase(dbname);
    }

    public String findQuery(String collection, Document docs) {
        MongoCollection<Document> coll = database.getCollection(collection);
        Document result = coll.find(docs).first();
        return result.toJson();
    }

    public String findAllQuery(String collection) {
        MongoCollection<Document> coll = database.getCollection(collection);

        MongoCursor<Document> cursor = coll.find().iterator();
        String result = "";
        try {
            while (cursor.hasNext()) {
                result += cursor.next().toJson();
                result += ", ";
            }
        } finally {
            cursor.close();
        }

        return result;
    }

    public void insertQuery(String collection, Document docs) {
        try {
            MongoCollection<Document> coll = database.getCollection(collection);
            coll.insertOne(docs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteQuery(String collection, Document docs) {
        try {
            MongoCollection<Document> coll = database.getCollection(collection);
            coll.deleteOne(docs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateQuery(String collection, String fieldName, Object value, Object newValue) {
        try {
            MongoCollection<Document> coll = database.getCollection(collection);
            coll.updateOne(eq(fieldName, value), new Document("$set", new Document(fieldName, newValue)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateQuery(String collection, String fieldName, Object value, String newFieldName, Object newValue) {
        try {
            MongoCollection<Document> coll = database.getCollection(collection);
            coll.updateOne(eq(fieldName, value), new Document("$set", new Document(newFieldName, newValue)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int countQuery(String collection) {
        long count = -1;
        int result = -1;

        try {
            MongoCollection<Document> coll = database.getCollection(collection);
            count = coll.count();
            result = Integer.parseInt(String.valueOf(count));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int countQuery(String collection, Document docs) {
        long count = -1;
        int result = -1;

        try {
            MongoCollection<Document> coll = database.getCollection(collection);
            count = coll.count(docs);
            result = Integer.parseInt(String.valueOf(count));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void close() {
        mongoClient.close();
    }


}
