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
import com.mongodb.client.model.Filters;
import java.util.Date;

import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.DeleteResult;
import excepciones.DniException;
import excepciones.PasswordException;
import java.time.LocalDate;
import java.time.ZoneId;


import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

/**
 *
 * @author ire22
 */
public class ImplementacionBD {
    
    // Atributos
    private ResourceBundle configFile;
    private String urlBD;
    private String userBD;
    private String passwordBD;
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
    
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();

        MongoCollection<Document> collection =  database.getCollection("people"); //Consigo la collecion de "Person" = "db.Persons.find()"

        // Document porue MongoDB devuelve BSON/JSON y en java estos documentos se representan con la clase Document
        for (Document doc : collection.find(Filters.eq("role", "member"))) {
            System.out.println(doc);
            User user = new User();

            user.setUserName(doc.getString("username"));
             try {
                user.setPassword(doc.getString("password"));
            } catch (PasswordException e) {
                System.out.println("Incorrect password: " + e.getMessage());
                continue; // pasa al siguiente usuario
            }
            
            user.setName(doc.getString("first_name"));
            user.setLastName(doc.getString("last_name"));

            try {
                user.setDni(doc.getString("national_id"));
            } catch (DniException e) {
                System.out.println("Incorrect DNI: " + e.getMessage());
                continue; // pasa al siguiente usuario
            }

            Date fechaMongo = doc.getDate("start_date");
            LocalDate fecha = fechaMongo.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            user.setStartDate(fecha);

            users.add(user);
         }
            return users;
    }
    public ArrayList<Employee> getEmployee() {
        ArrayList<Employee> aEmployee = new ArrayList<>();

        MongoCollection<Document> collection =  database.getCollection("people"); //Consigo la collecion de "Person" = "db.Persons.find()"

        // Document porue MongoDB devuelve BSON/JSON y en java estos documentos se representan con la clase Document
        for (Document doc : collection.find(Filters.eq("role", "employee"))) {
            System.out.println(doc);
            Employee employee = new Employee();

            employee.setUserName(doc.getString("username"));
             try {
                employee.setPassword(doc.getString("password"));
            } catch (PasswordException e) {
                System.out.println("Incorrect password: " + e.getMessage());
                continue; // pasa al siguiente usuario
            }
            
            employee.setName(doc.getString("first_name"));
            employee.setLastName(doc.getString("last_name"));

            try {
                employee.setDni(doc.getString("national_id"));
            } catch (DniException e) {
                System.out.println("Incorrect DNI: " + e.getMessage());
                continue; // pasa al siguiente usuario
            }

            Date fechaMongo = doc.getDate("start_date");
            LocalDate fecha = fechaMongo.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            employee.setStartDate(fecha);

            aEmployee.add(employee);
         }
            return aEmployee;
    }
    
        public boolean insertarPerson (Person p) {
        try {
            MongoCollection<Document> collection = database.getCollection("people");
            Document doc = new Document();
            doc.append("username", p.getUserName());
            doc.append("password", p.getPassword());
            doc.append("first_name", p.getName());
            doc.append("last_name", p.getLastName());
            doc.append("national_id", p.getDni());
            doc.append("start_date", p.getStartDate());
            
            if (p instanceof Employee) {
                doc.append("role", "employee");
            } else if (p instanceof User) {
                doc.append("role", "member");
            }
            
            collection.insertOne(doc);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean modificarPerson(Person p) {
        try {
            MongoCollection<Document> collection =database.getCollection("people");
            Document cambios = new Document();
            cambios.append("password", p.getPassword());
            cambios.append("first_name", p.getName());
            cambios.append("last_name", p.getLastName());
            cambios.append("national_id", p.getDni());
            collection.updateOne(
                    Filters.eq("username", p.getUserName()), //busca el que tenga ese userName 
                    new Document("$set", cambios) //y con el $set actualiza los campo que le digo pero que deje lo demas igual 
            );
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
   
    public boolean eliminarPerson(Person p) {
        try {
            MongoCollection<Document> collection = database.getCollection("people");
            DeleteResult resultado = collection.deleteOne(
                    Filters.eq("username", p.getUserName())
            );
            return resultado.getDeletedCount() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
