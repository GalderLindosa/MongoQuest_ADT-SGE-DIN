/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mongoquest;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class ProfileWindowController implements Initializable {

    @FXML
    private Tab tabUser;
    @FXML
    private Label lblPfp;
    @FXML
    private Label lblName;
    @FXML
    private Label lblSur;
    @FXML
    private Label lblUser;
    @FXML
    private Label lblPass;
    @FXML
    private Label lblDni;
    @FXML
    private CheckBox showPassword;
    @FXML
    private Button btnLogOut;
    @FXML
    private TextField fieldName;
    @FXML
    private TextField fieldSur;
    @FXML
    private TextField fieldUser;
    @FXML
    private TextField fieldDni;
    @FXML
    private PasswordField fieldPass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        tabUser.setText("Perfil");
    }    
 

    
    
}
