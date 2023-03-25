
package src;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.mindrot.jbcrypt.BCrypt;

public class Authentication {
    private static final String DB_NAME = "remote_printing";
    private static final String COLLECTION_NAME = "users";
    private MongoCollection<Document> usersCollection;

    public Authentication() {
        MongoClient mongoClient = MongoClients.create(); // Connects to default MongoDB server at localhost:27017
        MongoDatabase database = mongoClient.getDatabase(DB_NAME);
        usersCollection = database.getCollection(COLLECTION_NAME);
    }

    public boolean register(String username, String password) {
        if (usernameExists(username)) {
            return false;
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        Document userDocument = new Document("username", username)
                .append("password", hashedPassword);
        usersCollection.insertOne(userDocument);
        return true;
    }

    public boolean authenticate(String username, String password) {
        Document userDocument = findUserByUsername(username);
        if (userDocument != null) {
            String storedHashedPassword = userDocument.getString("password");
            return BCrypt.checkpw(password, storedHashedPassword);
        }
        return false;
    }

    private boolean usernameExists(String username) {
        return findUserByUsername(username) != null;
    }

    private Document findUserByUsername(String username) {
        return usersCollection.find(new Document("username", username)).first();
    }
}
