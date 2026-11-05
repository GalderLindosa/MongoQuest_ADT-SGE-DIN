/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mongoquest;

import java.io.IOException;
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
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
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
    private Label lblProductPrice;
    @FXML
    private Label lblProductAmount;
    @FXML
    private Spinner<?> amountToBuy;
    @FXML
    private Button addToCart;
    @FXML
    private Button btnBack;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //productimg.setImage(ShopWindowController.pr.getImage()); <---- Para cuando tengamos imagenes
        lblProductnName.setText(ShopWindowController.pr.getName());
        lblProductPrice.setText(Float.toString(ShopWindowController.pr.getPrice()) + "€");
        lblProductAmount.setText(Integer.toString(ShopWindowController.pr.getStock()));
    }
    
    @FXML
    private void addToCart() {
        
    }
    
    @FXML
    private void goBack(ActionEvent event) throws IOException{        
        Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ventanaActual.close();
    }
    
}
