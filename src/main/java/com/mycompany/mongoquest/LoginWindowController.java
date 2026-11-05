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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import controller.*;
import model.*;
import controller.MongoConnection;
import com.mongodb.client.MongoDatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ImplementacionBD;
import java.io.File;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class LoginWindowController implements Initializable {

    private ImplementacionBD bd = ImplementacionBD.getInstance();

    private static Scene scene;


    public static Person usuario;

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
    @FXML
    private VBox vBoxLogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = MongoConnection.getConnection();

    }

    @FXML
    private void sesionIniciada(ActionEvent event) throws IOException {

        usuario = bd.buscar(fieldUser.getText(), fieldPass.getText());

        if (usuario==null) {
            Label userNull = new Label("USER OR PASSWORD IS INCORRECT");
            userNull.setTextFill(Color.web("#FF0000"));
            userNull.setFont(new Font("Arial", 10));

            vBoxLogin.getChildren().add(userNull);

        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ProfileWindow.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Perfil");
            stage.setScene(new Scene(root));
            stage.show();

            Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ventanaActual.close();
        }

    }

    //a los metodos los llamamos como controlador.metodo 
    //el metodo de implementacion sea mirar si existe y devolver PERSONA 
    //aqui hacer un instance of y mirar que tipo de persona es y abrir una ventana dependiendo de el tipo que sea 
}
