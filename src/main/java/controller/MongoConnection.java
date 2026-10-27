package controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {

    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "G2_Life";

    private static MongoClient client;
    private static MongoDatabase database;

    // Método para conectar
    public static MongoDatabase getConnection() {
        if (client == null) {
            client = MongoClients.create(CONNECTION_STRING);
            database = client.getDatabase(DATABASE_NAME);
            System.out.println("Conectado a MongoDB: " + DATABASE_NAME);
        }
        return database;
    }

    // Método para cerrar la conexión (opcional)
    public static void close() {
        if (client != null) {
            client.close();
            System.out.println("Conexión cerrada.");
        }
    }
}
