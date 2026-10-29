/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;*/
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.lt;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;

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

    private ImplementacionBD() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("G2_Life");
    }

    public static ImplementacionBD getInstance() {
        if (instance == null) {
            instance = new ImplementacionBD();
        }
        return instance;
    }

    /*private void openConnection() {
        try {
            //con = DriverManager.getConnection(urlBD, userBD, passwordBD);
        } catch (SQLException e) {
            System.out.println("Error opening the database");
            e.printStackTrace();
        }
    }*/
    public Person buscar(String usuario, String contraseña) {
        // Replace the uri string with your MongoDB deployment's connection string
        Person persona;
        String uri = "<connection string uri>";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("G2_Life");
            MongoCollection<Document> collection = database.getCollection("People");
            // Proyecta los atributos de la clase User, excluyendo los atributos de la coleccion que no son necesarios
            Bson projectionFields = Projections.fields(
                    Projections.include("username", "password", "first_name", "last_name", "national_id", "workouts", "classes", "purchases"),
                    Projections.exclude("_id", "role"));
            
            // Se crea y usa un filtro el cual filtra los resultados por el usuario y contraseña introducidos por el usuario
            Bson filter = Filters.and(Filters.eq("username", usuario), Filters.eq("password", contraseña));
            FindIterable<Document> docs = collection.find(filter)
                    .projection(projectionFields);

            // Prints the titles of the queried documents
            System.out.println("10 movies under 15 minutes: ");
            docs.forEach(doc -> System.out.println("- " + doc.get("title")));
            System.out.println();
            // Retrieves the document with the best imdb rating that is less
            // than 15 minutes long, applying the projection
            Document doc = collection.find(lt("runtime", 15))
                    .projection(projectionFields)
                    .sort(Sorts.ascending("imdb.rating"))
                    .first();
            // Prints title of the queried document
            if (doc == null) {
                System.out.println("No results found.");
            } else {
                // System.out.println("The highest rated movie under 15 minutes: " + doc.toJson().get("title"));
            }
        }
        return null;
    }
}
