/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mongoquest;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import controller.*;
import model.*;
import controller.MongoConnection;
import com.mongodb.client.MongoDatabase;
import java.io.File;
/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class LoginWindowController implements Initializable {

    @FXML
    private Label Titulo;
    @FXML
    private Label lblUser;
    @FXML
    private Label lblPass;
    @FXML
    private Button btnLogin;
    @FXML
    private TextField fieldUser;
    @FXML
    private PasswordField fieldPass;
    
    private MongoDatabase db;

    ImplementacionBD instanceBD = ImplementacionBD.getInstance();
    GymControlador controlador = new GymControlador();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      db = MongoConnection.getConnection();
       System.out.println("Controlador conectado a MongoDB");
    }

    @FXML
    private void switchToSecondary() throws java.io.IOException {
        App.setRoot("ProfileWindow");
    }
//a los metodos los llamamos como controlador.metodo 
    //el metodo de implementacion sea mirar si existe y devolver PERSONA 
    //aqui hacer un instance of y mirar que tipo de persona es y abrir una ventana dependiendo de el tipo que sea 
}
