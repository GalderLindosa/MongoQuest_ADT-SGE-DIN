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
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class ProductWindowController implements Initializable {


    @FXML
    private ImageView productimg;
    @FXML
    private Label lblProductnName;
    @FXML
    private Label lblIdProduct;
    @FXML
    private Label lblProductPrize;
    @FXML
    private Label lblProductAmount;
    @FXML
    private Spinner<?> amountToBuy;
    @FXML
    private Button addToCart;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
