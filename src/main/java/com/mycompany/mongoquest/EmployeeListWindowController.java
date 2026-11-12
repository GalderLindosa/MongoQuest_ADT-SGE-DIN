/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mongoquest;

import controller.GymControlador;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.Person;
import model.Employee;
import excepciones.*;
import java.time.DateTimeException;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class EmployeeListWindowController implements Initializable {
    @FXML
    private ListView<Employee> employeeList;
    @FXML
    private Button addUserButton;
    @FXML
    private Label userLabel;
    @FXML
    private Label passLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label surLabel;
    @FXML
    private Label dniLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label mensaje;
    
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surField;
    @FXML
    private TextField dniField;
    @FXML
    private TextField dateField;
  
    @FXML
    private Button modifyUser;
    
    @FXML
    private RadioButton cancelRegistration;
    
    private GymControlador cont;
    private Employee anadirEmployee;
    
    @FXML
    private Label passLabel1;
    
    @FXML
    private PasswordField passField1;
    @FXML
    private Label passLabel11;
    @FXML
    private CheckBox verContra1;
    @FXML
    private CheckBox verContra2;
    
    @FXML
    private TextField passVisibleField;
    @FXML
    private TextField passVisibleField1;
    /**
     * Initializes the controller class.
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        cont = new GymControlador();
        
        anadirEmployee = new Employee();
        anadirEmployee .setUserName("+Añadir");
        
        for (Employee e : cont.getEmployee()) {
            employeeList.getItems().add(e);
        }
       employeeList.getItems().add(anadirEmployee );
       
       addUserButton.setVisible(false);
       modifyUser.setVisible(false);
       
       passVisibleField.setVisible(false);
       passVisibleField1.setVisible(false);
    }
    
    private void mostrarInfo(String msg) {
        mensaje.setStyle("-fx-text-fill: green;");
        mensaje.setText(msg);
    }
    
    private void mostrarError(String msg) {
        mensaje.setStyle("-fx-text-fill: red;");
        mensaje.setText(msg);
    }
     
   //Metodo para rellenar los text con la informacion
    @FXML 
    private void seleccionarUsuario() {
        ocultarContrasenas();
        Employee user = employeeList.getSelectionModel().getSelectedItem();
        if (user == null) {
            return;
        }
        if (user.getUserName().equals("+Añadir")) { 
            userField.clear();
            userField.setEditable(true);
            passField.clear();
            passField1.clear();
            nameField.clear();
            surField.clear();
            dniField.clear();
            dateField.clear();
            dateField.setText(LocalDate.now().toString());
            addUserButton.setVisible(true);
            modifyUser.setVisible(false);
            cancelRegistration.setVisible(false);
            
        }else{
            addUserButton.setVisible(false);
            modifyUser.setVisible(true);
            userField.setText(user.getUserName());
            userField.setEditable(false); // para que no se pueda moficar 
            passField.setText(user.getPassword());
            passField1.setText(user.getPassword());
            nameField.setText(user.getName());
            surField.setText(user.getLastName());
            dniField.setText(user.getDni());
            dateField.setText(user.getStartDate().toString());
            cancelRegistration.setVisible(true);
            cancelRegistration.setSelected(false);
        }
        dateField.setEditable(false);
    }
    
    //Metodo para mirar si sigue todo igual; lo que hace es coger el user y mirar is es igual a los text 
    private boolean igual(Employee user) {
        return user.getPassword().equals(passField.getText())
            && user.getPassword().equals(passField1.getText())
            && user.getName().equals(nameField.getText())
            && user.getLastName().equals(surField.getText())
            && user.getDni().equals(dniField.getText());
    }
    
    @FXML
    private void modificarEmployee() {
        Employee seleccionado = employeeList.getSelectionModel().getSelectedItem();
        boolean dniCorrecto = true, passwordCorrecto=true;

        if (userField.getText().isBlank() || seleccionado == null ) {
            mostrarError("You must select a user.");
        } else {
            if (cancelRegistration.isSelected()) {
                System.out.println("Entrando a eliminar");
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmation");
                alerta.setHeaderText("Cancel someone's registration");
                alerta.setContentText("Are you sure you want to delete the user?");

                Optional<ButtonType> resultado = alerta.showAndWait();

                if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                   System.out.println("Usuario seleccionado: "
                + seleccionado.getUserName());
                   
                    boolean eliminado = cont.eliminarPerson(seleccionado);

                    System.out.println("Resultado eliminación: " + eliminado);
                    if (eliminado) {
                        employeeList.getItems().remove(seleccionado);
                        employeeList.getSelectionModel().clearSelection();
                        mostrarInfo("User deleted from the database");
                        cancelRegistration.setSelected(false);
                        
                    } else {
                        mostrarError("Error deleting user");
                    }
                }
            } else {
                if (igual(seleccionado)) {
                    mostrarError("You need to modify some information.");
                } else {
                    try {
                        seleccionado.setDni(dniField.getText());
                    } catch (DniException e) {
                        mostrarError("Incorrect ID format");
                        dniCorrecto = false;
                    }
                    if (dniCorrecto) {
                        try {
                            seleccionado.setPassword(passField.getText());
                        } catch (PasswordException e) {
                            mostrarError("The password must be at least 8 characters include an uppercase letter, a lowercase letter, "
                                    + "\na number, and a special character.");
                            passwordCorrecto=false;
                        }
                        if (!contrasIguales(passField.getText(),passField1.getText())){
                            passwordCorrecto = false;
                            mostrarError("The passwords must be the same.");
                        }
                        
                        if (passwordCorrecto){
                            seleccionado.setName(nameField.getText());
                            seleccionado.setLastName(surField.getText());

                            if (cont.modificarPerson(seleccionado)) {
                                mostrarInfo("User updated");
                            } else {
                                mostrarError("Error updating user");
                            }
                        }
                    }
                }
            }
        }
    }
    
    private boolean comprobacion() {
        if (userField.getText().isBlank()
                || passField.getText().isBlank()
                || passField1.getText().isBlank()
                || nameField.getText().isBlank()
                || surField.getText().isBlank()
                || dniField.getText().isBlank()
                || dateField.getText().isBlank()) {
            mostrarError("All fields are required");
            return false;
        }
        return true;
    }
    
    @FXML
    //comprobar que todo los datos estan rellenados (en metodo mejor)
    private void anadirPerson() {
        boolean dniCorrecto=true,passwordCorrecto=true;
        if (comprobacion()) {
            String nomUser = userField.getText();

            if (userNameRepe(nomUser)) {
                mostrarError("That username already exists.");
                
            }else{
                Employee nuevo = new Employee();

                nuevo.setUserName(userField.getText());
                try {
                    nuevo.setPassword(passField.getText());
                } catch (PasswordException e) {
                    mostrarError("The password must be at least 8 characters include an uppercase letter, a lowercase letter,"
                            + "\n a number, and a special character.");
                    passwordCorrecto = false;
                }
               
                if (!contrasIguales(passField.getText(),passField1.getText())){
                    passwordCorrecto = false;
                    mostrarError("The passwords must be the same.");
                }
                
                if (passwordCorrecto){
                nuevo.setName(nameField.getText());
                nuevo.setLastName(surField.getText());

                try {
                    nuevo.setDni(dniField.getText());
                } catch (DniException e) {
                    mostrarError("Incorrect ID format");
                    dniCorrecto = false;
                }
                if (dniCorrecto){
                    try{
                        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate fecha = LocalDate.parse(dateField.getText(), formato);

                        nuevo.setStartDate(fecha);
                    }catch(DateTimeException e){
                        mostrarError("Error en la fecha");
                        System.out.print(e);
                    }
                    if (cont.insertarPerson(nuevo)) {
                        employeeList.getItems().remove(anadirEmployee);
                        employeeList.getItems().add(nuevo);
                        employeeList.getSelectionModel().selectFirst();
                        employeeList.getItems().add(anadirEmployee);

                        mostrarInfo("New user registered");

                    } else {
                        mostrarError("Error registering the user");
                    }
                }
                }
            }
       }
    }

   private boolean userNameRepe(String userName) {
        for (Employee u : cont.getEmployee()) {
             if (u.getUserName().equalsIgnoreCase(userName)) {
                 return true;
             }
         }
         return false;
   }
   
   private boolean contrasIguales(String cont1, String cont2){
       if (cont1.equals(cont2)){
           return true;
       }else{
           return false;
       }
   }
   
   @FXML
    private void verContrasena() {
        if (verContra1.isSelected()) {
            passVisibleField.setText(passField.getText());
            passVisibleField.setVisible(true);
            passVisibleField.setManaged(true);
            passField.setVisible(false);
            passField.setManaged(false);
        } else {
            passField.setText(passVisibleField.getText());
            passField.setVisible(true);
            passField.setManaged(true);
            passVisibleField.setVisible(false);
            passVisibleField.setManaged(false);
        }
    }
    
    @FXML
    private void verContrasena1() {
        if (verContra2.isSelected()) {
            passVisibleField1.setText(passField1.getText());
            passVisibleField1.setVisible(true);
            passVisibleField1.setManaged(true);
            passField1.setVisible(false);
            passField1.setManaged(false);

        } else {
            passField1.setText(passVisibleField1.getText());
            passField1.setVisible(true);
            passField1.setManaged(true);
            passVisibleField1.setVisible(false);
            passVisibleField1.setManaged(false);
        }
    }
    
    private void ocultarContrasenas() {
        verContra1.setSelected(false);
        verContra2.setSelected(false);

        passField.setVisible(true);
        passField.setManaged(true);

        passVisibleField.setVisible(false);
        passVisibleField.setManaged(false);

        passField1.setVisible(true);
        passField1.setManaged(true);

        passVisibleField1.setVisible(false);
        passVisibleField1.setManaged(false);
    }
}
