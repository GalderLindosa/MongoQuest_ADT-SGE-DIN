/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mongoquest;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Boss;
import model.Employee;
import model.Person;
import model.User;
import javafx.stage.Stage;
import javafx.scene.Scene;

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
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            userName.setText(LoginWindowController.usuario.getName());
            userSur.setText(LoginWindowController.usuario.getLastName());
            user.setText(LoginWindowController.usuario.getUserName());
            userDNI.setText(LoginWindowController.usuario.getDni());
            balancelbl.setText(Float.toString(LoginWindowController.usuario.getBalance()) + "€");
            button1.setText("Profile");
            mostrarContraseña();
            cargaInfoUser(LoginWindowController.usuario);
            setButtons(LoginWindowController.usuario);
        } catch (IOException ex) {
            System.getLogger(ProfileWindowController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @FXML
    public void mostrarContraseña() {
        if (showPassword.isSelected()) {
            userPass.setText(LoginWindowController.usuario.getPassword());
        } else {
            userPass.setText("****");
        }
    }

    public void cargaInfoUser(Person person) {

        if (person instanceof User | person instanceof Employee) {
            Label balancelbl = new Label();
            balancelbl.setTextFill(Color.web("#FF0000"));
            balancelbl.setFont(new Font("Arial", 10));
            balancelbl.setLayoutX(111);
            balancelbl.setLayoutY(254);
        }
    }

    public void setButtons(Person person) throws IOException {

        if (person instanceof User) {
            setClassButton();
            setShopButton();
            setRoutineButton();
        } else if (person instanceof Employee) {
            setClassButton();
            setShopButton();
            setUsListButton();
        } else if (person instanceof Boss) {
            setClassButton();
            setEmListButton();
            setUsListButton();

        }
        button1.setDisable(true);

    }

    public void setClassButton() throws IOException {

        button2.setText("Classes");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            private Scene scene;
            Stage stage = new Stage();

            public void handle(ActionEvent e) {

                try {
                    scene = new Scene(loadFXML("HorarioWindow"), 819, 400);
                } catch (IOException ex) {
                    System.getLogger(ProfileWindowController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
                stage.setScene(scene);

                stage.show();
                stage.setTitle("Classes");
            }
        });
    }

    public void setShopButton() throws IOException {

        button4.setText("Shop");
        button4.setOnAction(new EventHandler<ActionEvent>() {
            private Scene scene;
            Stage stage = new Stage();

            public void handle(ActionEvent e) {

                try {
                    scene = new Scene(loadFXML("ShopWindow"), 819, 400);
                } catch (IOException ex) {
                    System.getLogger(ProfileWindowController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
                stage.setScene(scene);

                stage.show();
                stage.setTitle("Shop");
            }
        });
    }

    public void setRoutineButton() throws IOException {

        button3.setText("Routine");
        button3.setOnAction(new EventHandler<ActionEvent>() {
            private Scene scene;
            Stage stage = new Stage();

            public void handle(ActionEvent e) {

                try {
                    scene = new Scene(loadFXML("HorarioWindow"), 819, 400);
                } catch (IOException ex) {
                    System.getLogger(ProfileWindowController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
                stage.setScene(scene);

                stage.show();
                stage.setTitle("Routine");
            }
        });
    }

    public void setUsListButton() throws IOException {

        button3.setText("Users list");
        button3.setOnAction(new EventHandler<ActionEvent>() {
            private Scene scene;
            Stage stage = new Stage();

            public void handle(ActionEvent e) {

                try {
                    scene = new Scene(loadFXML("UserListWindow"), 819, 400);
                } catch (IOException ex) {
                    System.getLogger(ProfileWindowController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
                stage.setScene(scene);

                stage.show();
                stage.setTitle("Users list");
            }
        });
    }

    public void setEmListButton() throws IOException {

        button4.setText("Employee List");
        button4.setOnAction(new EventHandler<ActionEvent>() {
            private Scene scene;
            Stage stage = new Stage();

            public void handle(ActionEvent e) {

                try {
                    scene = new Scene(loadFXML("EmployeeListWindow"), 819, 400);
                } catch (IOException ex) {
                    System.getLogger(ProfileWindowController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
                stage.setScene(scene);

                stage.show();
                stage.setTitle("Employee List");
            }
        });
    }



    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    @FXML
    public void goBackToLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("LoginWindow.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.show();

        Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ventanaActual.close();
    }

}
