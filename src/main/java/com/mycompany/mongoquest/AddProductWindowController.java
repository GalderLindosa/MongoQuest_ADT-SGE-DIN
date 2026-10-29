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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class AddProductWindowController implements Initializable {

    @FXML
    private Label newNameLabel;
    @FXML
    private Label newPriceLabel;
    @FXML
    private Label newStockLabel;
    @FXML
    private TextField newNameField;
    @FXML
    private TextField NewPriceField;
    @FXML
    private TextField newStockField;
    @FXML
    private Button createProduct;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
