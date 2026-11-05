/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mongoquest;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Person;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class ProfileWindowController implements Initializable {

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
    private Label userName;
    @FXML
    private Label userSur;
    @FXML
    private Label user;
    @FXML
    private Label userPass;
    @FXML
    private Label userDNI;
    @FXML
    private Pane profilePane;
    @FXML
    private Label balancelbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        userName.setText(LoginWindowController.usuario.getName());
        userSur.setText(LoginWindowController.usuario.getLastName());
        user.setText(LoginWindowController.usuario.getUserName());
        userDNI.setText(LoginWindowController.usuario.getDni());
        balancelbl.setText(Float.toString(LoginWindowController.usuario.getBalance()));
        mostrarContraseña();

    }
    

    @FXML
    public void mostrarContraseña() {
        if (showPassword.isSelected()) {
            userPass.setText(LoginWindowController.usuario.getPassword());
        } else {
            userPass.setText("****");
        }
    }

}
