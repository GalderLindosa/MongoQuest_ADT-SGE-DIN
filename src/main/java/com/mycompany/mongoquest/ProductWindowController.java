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
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Employee;
import model.User;
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
    private Spinner<Integer> amountToBuy;
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
        lblProductAmount.setText("Stock: " + Integer.toString(ShopWindowController.pr.getStock()));
        amountToBuy.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, ShopWindowController.pr.getStock(), 0));
    }
    
    @FXML
    private void addToCart(ActionEvent event) throws IOException {
        ShopWindowController.pr.setStock(ShopWindowController.pr.getStock() - amountToBuy.getValue());
        
        if (LoginWindowController.usuario instanceof User) {
            ((User)LoginWindowController.usuario).getCart();
        } else if (LoginWindowController.usuario instanceof Employee) {
            ((Employee)LoginWindowController.usuario).getCart();
        }
        
        Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ventanaActual.close();
    }
    
    @FXML
    private void goBack(ActionEvent event) throws IOException {        
        Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ventanaActual.close();
    }
    
}
