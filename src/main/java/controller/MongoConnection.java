package controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import model.Product;
import org.bson.Document; 

public class MongoConnection {

    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "G2_Life";

    private static MongoClient client;
    private static MongoDatabase database;


/**
 * Obtiene una conexión a la base de datos MongoDB.
 * Si no existe una conexión activa, la crea y la devuelve.
 *
 * @return instancia de la base de datos MongoDB.
 */
public static MongoDatabase getConnection() {
    if (client == null) {
        client = MongoClients.create(CONNECTION_STRING);
        database = client.getDatabase(DATABASE_NAME);
        System.out.println("Conectado a MongoDB: " + DATABASE_NAME);
    }
    return database;
}
    
/**
 * Cierra la conexión activa con MongoDB.
 */
public static void close() {
    if (client != null) {
        client.close();
        System.out.println("Conexión cerrada.");
    }
}
}
