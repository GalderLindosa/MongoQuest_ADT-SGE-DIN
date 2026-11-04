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

    // Método para conectar
    public static MongoDatabase getConnection() {
        if (client == null) {
            client = MongoClients.create(CONNECTION_STRING);
            database = client.getDatabase(DATABASE_NAME);
            System.out.println("Conectado a MongoDB: " + DATABASE_NAME);
        }
        return database;
    }

    public ArrayList<Product> obtenerProductos(){
        MongoCollection<Document> productos = MongoConnection.getConnection().getCollection("Productos");
        ArrayList<Product> lista = new ArrayList<>();
        
        for (Document doc : productos.find()) {
            Product p = new Product(((Number) doc.get("_id")).intValue(),
                                    doc.getString("name"),
                                    ((Number) doc.get("price")).floatValue(),
                                    ((Number) doc.get("stock")).intValue());
            lista.add(p);
        }
        
        return lista;
    }
    
    // Método para cerrar la conexión (opcional)
    public static void close() {
        if (client != null) {
            client.close();
            System.out.println("Conexión cerrada.");
        }
    }
}
