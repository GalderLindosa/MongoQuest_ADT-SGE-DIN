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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void switchToSecondary() throws java.io.IOException {
        App.setRoot("ProfileWindow");
    }

}
