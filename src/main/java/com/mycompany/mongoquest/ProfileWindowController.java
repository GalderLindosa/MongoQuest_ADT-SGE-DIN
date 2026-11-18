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
 * Controlador de la ventana de perfil. Muestra la información del usuario
 * autenticado y configura las opciones disponibles según su rol.
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
    @FXML
    private Button button5;

    private String username;
    private String role;

    /**
     * Inicializa la ventana cargando los datos del usuario y configurando los
     * botones según su tipo.
     *
     * @param url ubicación utilizada para resolver rutas relativas.
     * @param rb recursos de internacionalización.
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

    /**
     * Muestra u oculta la contraseña del usuario según el estado del CheckBox.
     */
    @FXML
    public void mostrarContraseña() {
        if (showPassword.isSelected()) {
            userPass.setText(LoginWindowController.usuario.getPassword());
        } else {
            userPass.setText("****");
        }
    }

    /**
     * Carga información adicional del usuario en la interfaz.
     *
     * @param person usuario del que se desea mostrar información.
     */
    public void cargaInfoUser(Person person) {

        if (person instanceof User | person instanceof Employee) {
            Label balancelbl = new Label();
            balancelbl.setTextFill(Color.web("#FF0000"));
            balancelbl.setFont(new Font("Arial", 10));
            balancelbl.setLayoutX(111);
            balancelbl.setLayoutY(254);
        }
    }

    /**
     * Configura los botones visibles según el tipo de usuario.
     *
     * @param person usuario autenticado.
     * @throws IOException si ocurre un error al cargar una ventana.
     */
    public void setButtons(Person person) throws IOException {

        if (person instanceof User) {
            setClassButton();
            setShopButton();
            setRoutineButton();
            button5.setVisible(false);
        } else if (person instanceof Employee) {
            setClassButton();
            setShopButton();
            setUsListButton();
            button5.setVisible(false);
        } else if (person instanceof Boss) {
            setClassButton();
            setEmListButton();
            setUsListButton();
            button5.setVisible(true);
            setLogButton();

        }
        button1.setDisable(true);

    }

    /**
     * Configura el botón de acceso a las clases.
     *
     * @throws IOException si ocurre un error al cargar la ventana.
     */
    public void setClassButton() throws IOException {

        button2.setText("Classes");

        button2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                try {
                    FXMLLoader loader = new FXMLLoader(
                            App.class.getResource("ScheduleWindow.fxml")
                    );

                    Scene scene = new Scene(loader.load(), 819, 400);

                    ScheduleWindowController controller
                            = loader.getController();

                    String username = LoginWindowController.usuario.getUserName();
                    String role = "";

                    if (LoginWindowController.usuario instanceof User) {
                        role = "member";
                    } else if (LoginWindowController.usuario instanceof Employee) {
                        role = "employee";
                    } else if (LoginWindowController.usuario instanceof Boss) {
                        role = "manager";
                    }

                    controller.setUser(username, role);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Classes");
                    stage.show();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    /**
     * Configura el botón de acceso a la tienda.
     *
     * @throws IOException si ocurre un error al cargar la ventana.
     */
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

    /**
     * Configura el botón de acceso a la ventana de entrenamientos del usuario.
     *
     * @throws IOException si ocurre un error al cargar la ventana.
     */
    public void setRoutineButton() throws IOException {

        button3.setText("Routine");
        button3.setOnAction(new EventHandler<ActionEvent>() {
            private Scene scene;
            Stage stage = new Stage();

            public void handle(ActionEvent e) {

                try {

                    scene = new Scene(loadFXML("WorkoutWindow"), 819, 400);

                } catch (IOException ex) {
                    System.getLogger(ProfileWindowController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
                stage.setScene(scene);

                stage.show();
                stage.setTitle("Routine");
            }
        });
    }

    /**
     * Configura el botón de acceso a la gestión y consulta de usuarios
     * registrados.
     *
     * @throws IOException si ocurre un error al cargar la ventana.
     */
    public void setUsListButton() throws IOException {

        button3.setText("Users list");
        button3.setOnAction(new EventHandler<ActionEvent>() {
            private Scene scene;
            Stage stage = new Stage();

            public void handle(ActionEvent e) {

                try {
                    scene = new Scene(loadFXML("UserListWindow"), 790, 500);
                } catch (IOException ex) {
                    System.getLogger(ProfileWindowController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
                stage.setScene(scene);

                stage.show();
                stage.setTitle("Users list");
            }
        });
    }

    /**
     * Configura el botón de acceso a la lista de empleados registrados en el
     * gimnasio.
     *
     * @throws IOException si ocurre un error al cargar la ventana.
     */
    public void setEmListButton() throws IOException {

        button4.setText("Employee List");
        button4.setOnAction(new EventHandler<ActionEvent>() {
            private Scene scene;
            Stage stage = new Stage();

            public void handle(ActionEvent e) {

                try {
                    scene = new Scene(loadFXML("EmployeeListWindow"), 790, 500);
                } catch (IOException ex) {
                    System.getLogger(ProfileWindowController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
                stage.setScene(scene);

                stage.show();
                stage.setTitle("Employee List");
            }
        });
    }

    /**
     * Carga un archivo FXML y devuelve su nodo raíz.
     *
     * @param fxml nombre del archivo FXML sin extensión.
     * @return nodo raíz cargado.
     * @throws IOException si ocurre un error durante la carga.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Cierra la sesión actual y vuelve a la ventana de inicio de sesión.
     *
     * @param event evento generado al pulsar el botón de cerrar sesión.
     * @throws IOException si ocurre un error al cargar la ventana.
     */
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

    /**
     * Configura el botón que permite visualizar el registro de eventos y
     * acciones del sistema.
     */
    public void setLogButton() {

        button5.setText("View Logs");

        button5.setOnAction(new EventHandler<ActionEvent>() {

            private Scene scene;
            Stage stage = new Stage();

            @Override
            public void handle(ActionEvent e) {

                try {

                    scene = new Scene(
                            loadFXML("LogWindow"),
                            800,
                            500
                    );

                    stage.setScene(scene);
                    stage.setTitle("System Logs");
                    stage.show();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
