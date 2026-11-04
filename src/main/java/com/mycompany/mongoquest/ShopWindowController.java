/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mongoquest;

import controller.MongoConnection;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import model.Product;
import org.bson.Document;
/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class ShopWindowController implements Initializable {

    MongoConnection conexion = new MongoConnection();

    @FXML
    private Button btnCarrito;
    @FXML
    private Button btnHistorial;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private FlowPane contenedorProductos;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contenedorProductos.getChildren().clear();

        for (Product p : conexion.obtenerProductos()) {
            Button boton = new Button(p.getName());
            boton.setPrefSize(150, 80);
            boton.setOnAction(e -> product());

            contenedorProductos.getChildren().add(boton);
        }
    }    
    
    @FXML
    private void history(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("PurchaseHistory.fxml"));
        Parent root = fxmlLoader.load();
        
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        
        Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ventanaActual.close();
    }
    
    @FXML
    private void cart(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CartWindow.fxml"));
        Parent root = fxmlLoader.load();
        
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        
        Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ventanaActual.close();
    }
    
    private void product() {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ProductWindow.fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            System.getLogger(ShopWindowController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
