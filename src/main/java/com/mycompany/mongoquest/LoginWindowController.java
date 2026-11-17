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
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class LoginWindowController implements Initializable {

    /**
     * Instancia de la base de datos utilizada para las consultas.
     */
    private ImplementacionBD bd = ImplementacionBD.getInstance();
    /**
     * Escena principal de *a aplicación.
     */
    private static Scene scene;

    public static Person usuario;

    private boolean errorMessage = false;
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
    @FXML
    private CheckBox showPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = MongoConnection.getConnection();

    }

    /**
     * Comprueba las credenciales introduc*das por el usuario. Si son corr*ctas
     * abre la ventana de perfil y c*erra la ventana actual. En caso*contrario
     * muestra un mensaje de*error.
     *
     * @param event evento g*nerado al pulsar el botón de login
     *
     * @throws IOException si ocurre un error al cargar la ventana.
     */
    @FXML
    private void loggedIn(ActionEvent event) throws IOException {

        usuario = bd.buscar(fieldUser.getText(), fieldPass.getText());

        if (usuario == null) {
            Label userNull = new Label("USER OR PASSWORD IS INCORRECT");
            userNull.setTextFill(Color.web("#FF0000"));
            userNull.setFont(new Font("Arial", 10));
            if (!errorMessage) {
                errorMessage = true;
                vBoxLogin.getChildren().add(userNull);
            }

        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ProfileWindow.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Perfil");
            stage.setScene(new Scene(root));
            stage.show();

            Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ventanaActual.close();
            System.out.println(usuario.toString());
        }

    }

    @FXML
    private void passwordShown(ActionEvent event) {

        if (showPassword.isSelected()) {
            var alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Su contraseña es");
            alert.setContentText("Su contraseña es:" + fieldPass.getText());
            alert.show();
        }

    }

}
