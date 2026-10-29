/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 *
 * @author ire22
 */
public class ImplementacionBD implements GymDAO {
        // Atributos
    private Connection con;
    private PreparedStatement stmt;
    private ResourceBundle configFile;
    private String urlBD;
    private String userBD;
    private String passwordBD;
    private ResultSet rs;

    // Sentencias SQL
    final String SQL_CustomerId = "SELECT * FROM CUSTOMER_S WHERE ID = ?";
    final String SQL_InsertProduct = "INSERT INTO PRODUCT (ID, NAME_P, PRICE, CATEGORY, STOCK, ROOT) VALUES (?, ?, ?, ?, ?, ?)";
    final String SQL_InsertClient = "INSERT INTO CLIENT_S (ID, NAME_C, EMAIL, PHONE, ADDRESS) VALUES (?, ?, ?, ?, ?)";
    final String SQL_VerProductos = "SELECT * FROM PRODUCT WHERE STOCK > 0";
    final String SQL_OrdersByCustomer = "SELECT orderId, orderDate, total FROM ORDERS WHERE customerId = ?";
    final String SQL_UpdateStock = "UPDATE PRODUCT SET STOCK=? WHERE ID=?";
    final String SQL_ProductExists = "SELECT ID FROM PRODUCT WHERE ID=?";
    final String SQL_GetProduct = "SELECT * FROM product WHERE id = ?";

    // Singleton
    private static ImplementacionBD instance;

    private ImplementacionBD() {
        this.configFile = ResourceBundle.getBundle("configClase");
        this.urlBD = this.configFile.getString("Conn");
        this.userBD = this.configFile.getString("DBUser");
        this.passwordBD = this.configFile.getString("DBPass");
    }
    
    public static ImplementacionBD getInstance(){
        if(instance == null){
             instance = new ImplementacionBD();
        }
        return instance;
    }

    private void openConnection() {
        try {
            con = DriverManager.getConnection(urlBD, userBD, passwordBD);
        } catch (SQLException e) {
            System.out.println("Error opening the database");
            e.printStackTrace();
        }
    }
    
    
    @Override
    public Person login(int id) {

        Person  person = null;
        openConnection();

        try {
            stmt = con.prepareStatement(SQL_CustomerId);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                person = new Person();
                
                person.setId(rs.getInt("ID"));
                person.setName(rs.getString("NAME"));
                person.setEmail(rs.getString("EMAIL"));
                person.setPhoneNumber(rs.getString("PHONE"));
                person.setAddress(rs.getString("ADDRESS"));
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error getting customer: " + e.getMessage());
        }

        return person;
    }

}
