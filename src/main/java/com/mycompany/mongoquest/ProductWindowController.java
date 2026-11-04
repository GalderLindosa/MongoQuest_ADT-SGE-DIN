/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mongoquest;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    private Label lblIdProduct;
    @FXML
    private Label lblProductPrize;
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
        // TODO
    }
    
    @FXML
    private void goBack() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ShopWindow.fxml"));
        Parent root = fxmlLoader.load();
        
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
}
