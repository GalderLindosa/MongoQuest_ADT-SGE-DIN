/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mongoquest;

import dao.ImplementacionBD;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Employee;
import model.Person;
import model.Product;
import model.User;
import model.WithCart;
/**
 * Controlador de la ventana de visualización de productos.
 * Permite consultar la información de un producto y añadirlo
 * al carrito de compra del usuario.
 *
 * @author Usuario
 */
public class ProductWindowController implements Initializable {
    
   /**
   * Producto seleccionado en la tienda.
   */
    Product product = ShopWindowController.pr;
    
    ImplementacionBD implementacion = ImplementacionBD.getInstance();
    
    @FXML
    private ImageView productimg;
    @FXML
    private Label lblProductnName;
    @FXML
    private Label lblProductPrice;
    @FXML
    private Label lblProductAmount;
    /**
     * Selector de cantidad a comprar.
     */
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
        lblProductnName.setText(product.getName());
        lblProductPrice.setText(Float.toString(product.getPrice()) + "€");
        lblProductAmount.setText("Stock: " + Integer.toString(product.getStock()));
        amountToBuy.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, product.getStock(), 0));
    }
    
    /**
    * Añade la cantidad seleccionada del producto al carrito
    * del usuario y actualiza el stock disponible.
    *
    * @param event evento generado al pulsar el botón.
    * @throws IOException si ocurre un error durante el proceso.
    */
    @FXML
    private void addToCart(ActionEvent event) throws IOException {

        int cantidad = amountToBuy.getValue();

        if (cantidad <= 0) {
            showAlert("Error", "You must select an amount.");
            return;
        }

        if (!(LoginWindowController.usuario instanceof WithCart)) {
            showAlert("Error", "This user does not have a cart.");
            return;
        }

        // Primero comprobamos y actualizamos el stock en MongoDB.
        if (!implementacion.decreaseProductStock(product.getProductId(), cantidad)) {
            showAlert("Error", "Insuficient stock");
            return;
        }

        // Actualizamos el objeto del producto.
        product.setStock(product.getStock() - cantidad);

        // Obtenemos el carrito.
        Map<Product, Integer> cart = ((WithCart) LoginWindowController.usuario).getCart();

        boolean encontrado = false;
        if (cart != null) {
            for (Product p : cart.keySet()) {
                if (p.getProductId() == product.getProductId()) {
                    cart.put(p, cart.get(p) + cantidad);
                    encontrado = true;
                    break;
                }
            }
        }

        // Si no estaba en el carrito, lo añadimos.
        if (!encontrado) {
            ((WithCart) LoginWindowController.usuario).setCart(product, cantidad);
        }

        Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ventanaActual.close();
    }

   /**
    * Cierra la ventana actual y vuelve a la anterior.
    *
    * @param event evento generado al pulsar el botón volver.
    * @throws IOException si ocurre un error durante el proceso.
    */
   @FXML
   private void goBack(ActionEvent event) throws IOException {        
        Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ventanaActual.close();
    }
   
   private void showAlert(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
}
