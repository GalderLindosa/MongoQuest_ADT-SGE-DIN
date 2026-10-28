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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class EmployeeListWindowController implements Initializable {


    @FXML
    private ListView<?> employeeList;
    @FXML
    private Button addEmployee;
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
    private TextField userField;
    @FXML
    private TextField passField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surField;
    @FXML
    private TextField dniFIeld;
    @FXML
    private TextField dateField;
    @FXML
    private Button deleteButton;
    @FXML
    private Button modifyButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
