/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;*/
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.and;

import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Filters.or;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import controller.MongoConnection;

import java.util.ArrayList;
import java.util.ResourceBundle;
import org.bson.conversions.Bson;

/**
 *
 * @author ire22
 */
public class ImplementacionBD implements GymDAO {

    // Atributos
    // private Connection con;
    // private PreparedStatement stmt;
    private ResourceBundle configFile;
    private String urlBD;
    private String userBD;
    private String passwordBD;
    // private ResultSet rs;
    private MongoClient mongoClient;
    private MongoDatabase database;

    // Singleton
    private static ImplementacionBD instance;
    private final MongoCollection<Document> PROFILES;

    private ImplementacionBD() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("G2_Life");
        PROFILES = database.getCollection("People");

    }

    public static ImplementacionBD getInstance() {
        if (instance == null) {
            instance = new ImplementacionBD();

        }
        return instance;
    }


    public Person buscar(String usuario, String contraseña) {
        Person persona = null;

        {
            try {
                Document doc = PROFILES.find(and(eq("username", usuario), eq("password", contraseña))).first();

                if (doc == null || !contraseña.equals(doc.getString("password"))) {
                    return null;
                } else {
                    String type = doc.getString("role");

                    if ("member".equals(type)) {
                        return new User(doc.getString("username"), doc.getString("password"), doc.getString("first_name"), doc.getString("last_name"), doc.getString("national_id"),doc.getInteger("balance"));
                    } else if ("manager".equals(type)) {
                        return new Boss(doc.getInteger("gym_id"), doc.getString("username"), doc.getString("password"), doc.getString("first_name"), doc.getString("last_name"), doc.getString("national_id"),doc.getInteger("balance"));
                    } else if ("employee".equals(type)) {
                        return new Employee(doc.getString("discount_code"), doc.getInteger("gym_id"), doc.getString("username"), doc.getString("password"), doc.getString("first_name"), doc.getString("last_name"), doc.getString("national_id"),doc.getInteger("balance"));
                    }
                }

            } catch (MongoException ex) {

            }
        }
        return persona;
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

}
