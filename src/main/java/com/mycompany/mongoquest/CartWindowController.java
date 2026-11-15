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
 * Inicializa la ventana del carrito cargando los productos
 * almacenados en el carrito del usuario actual.
 *
 * @param url ubicación utilizada para resolver rutas relativas.
 * @param rb recursos utilizados para la internacionalización.
 */
@Override
public void initialize(URL url, ResourceBundle rb) {
        Person p = LoginWindowController.usuario;

        if (!(p instanceof WithCart)) {
            return;   
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
/**
 * Crea las tarjetas visuales de los productos contenidos
 * en el carrito de compra.
 *
 * @param cart mapa con los productos y sus cantidades.
 * @return lista de tarjetas de productos.
 */
private List<VBox> crearTarjetaProducto(Map<Product, Integer> cart) {
        
        ArrayList<VBox> lista = new ArrayList<>();
    
        for (Map.Entry<Product, Integer> e : cart.entrySet()) {
            
            ImageView imagen = new ImageView(cargarImagen(e.getKey()));
            imagen.setFitWidth(120);
            imagen.setFitHeight(120);
            imagen.setPreserveRatio(true);


            Label lblNombre = new Label(e.getKey().getName());
            lblNombre.setStyle("-fx-font-weight: bold;");
            lblNombre.setWrapText(true);
            lblNombre.setMaxWidth(140);
            
            Label lblCantidad = new Label(Integer.toString(e.getValue()));

            Label lblPrecio = new Label(e.getValue() * e.getKey().getPrice() + " €");

            VBox tarjeta = new VBox(8, imagen, lblNombre, lblCantidad, lblPrecio);
            tarjeta.setAlignment(Pos.CENTER);
            tarjeta.setPadding(new Insets(10));
            tarjeta.setPrefWidth(160);
            
            lista.add(tarjeta);
        }
        
        return lista;
    }
/**
 * Carga la imagen asociada a un producto.
 *
 * @param p producto del que se desea obtener la imagen.
 * @return imagen del producto.
 */
private Image cargarImagen(Product p){
        try {
                return null ;
        } catch (Exception ex) {
            return null ;
        }
    }
    
  /**
 * Cierra la ventana actual y vuelve a la pantalla anterior.
 *
 * @param event evento generado al pulsar el botón volver.
 * @throws java.io.IOException si ocurre un error durante el proceso.
 */
private void goBack(ActionEvent event) throws java.io.IOException {        
        Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ventanaActual.close();
    }
    
}
