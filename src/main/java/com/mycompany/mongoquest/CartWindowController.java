/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mongoquest;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Person;
import model.Product;
import model.WithCart;

/**
 * FXML Controller class
 *
 * @author Unai.Ibarguren
 */
public class CartWindowController implements Initializable {
    
    private Map<Product, Integer> cart;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button btnBack;
    @FXML
    private FlowPane contenedorProductos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Person p = LoginWindowController.usuario;

        if (!(p instanceof WithCart)) {
            return;   // Jefe u otro tipo sin carrito
        }

        cart = ((WithCart) p).getCart();

        if (cart == null || cart.isEmpty()) {
            contenedorProductos.getChildren().add(new Label("El carrito está vacío"));
            return;
        }

        for (VBox tarjeta : crearTarjetaProducto(cart)) {
            contenedorProductos.getChildren().add(tarjeta);
        }
    }
    
    private List<VBox> crearTarjetaProducto(Map<Product, Integer> cart) {
        
        ArrayList<VBox> lista = new ArrayList<>();
    
        for (Map.Entry<Product, Integer> e : cart.entrySet()) {
            // Imagen
            ImageView imagen = new ImageView(cargarImagen(e.getKey()));
            imagen.setFitWidth(120);
            imagen.setFitHeight(120);
            imagen.setPreserveRatio(true);

            // Nombre, cantidad y precio
            Label lblNombre = new Label(e.getKey().getName());
            lblNombre.setStyle("-fx-font-weight: bold;");
            lblNombre.setWrapText(true);
            lblNombre.setMaxWidth(140);
            
            Label lblCantidad = new Label(Integer.toString(e.getValue()));

            Label lblPrecio = new Label(e.getValue() * e.getKey().getPrice() + " €");

            // Contenedor de la tarjeta
            VBox tarjeta = new VBox(8, imagen, lblNombre, lblCantidad, lblPrecio);
            tarjeta.setAlignment(Pos.CENTER);
            tarjeta.setPadding(new Insets(10));
            tarjeta.setPrefWidth(160);
            /*tarjeta.setStyle(
                "-fx-border-color: #cccccc; -fx-border-radius: 8; " +
                "-fx-background-radius: 8; -fx-background-color: white; " +
                "-fx-cursor: hand;");*/
            
            lista.add(tarjeta);
        }
        
        return lista;
    }
    
    private Image cargarImagen(Product p) {
        try {
            // Si guardas la ruta/URL como String en el producto:
            return null ;//new Image(p.getImage(), 120, 120, true, true, true);  <---- Para cuando tengamos imagenes de productos
        } catch (Exception ex) {
            return null ;//new Image(getClass().getResourceAsStream("sin-imagen.png"));
        }
    }
    
    private void goBack(ActionEvent event) throws java.io.IOException {        
        Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ventanaActual.close();
    }
    
}
