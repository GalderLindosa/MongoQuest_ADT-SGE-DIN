/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;*/

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;


import java.util.ArrayList;
import java.util.ResourceBundle;

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
  
    
    public static ImplementacionBD getInstance(){
        if(instance == null){
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
    
   
}
