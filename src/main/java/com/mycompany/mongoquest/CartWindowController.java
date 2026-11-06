/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mongoquest;

import java.net.URL;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Product;
import model.User;

/**
 * FXML Controller class
 *
 * @author Unai.Ibarguren
 */
public class CartWindowController implements Initializable {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button btnBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    /*
    
    CAMBIAR ESTO, HAY QUE RECORRER EL MAPA DEL CARRITO CON .entrySet() Y LUEGO PILLAR LA CLAVE CON .getKey()
    PARA LUEGO ASIGNAR A CADA ELEMENTO LO QUE PROCEDA
    
    private VBox crearTarjetaProducto() {
        // Imagen
        ImageView imagen = new ImageView(cargarImagen(((User) LoginWindowController.usuario).getCart().getKey()));
        imagen.setFitWidth(120);
        imagen.setFitHeight(120);
        imagen.setPreserveRatio(true);

        // Nombre y precio
        Label lblNombre = new Label(((User) LoginWindowController.usuario).getCart().getKey().getName());
        lblNombre.setStyle("-fx-font-weight: bold;");
        lblNombre.setWrapText(true);
        lblNombre.setMaxWidth(140);

        Label lblPrecio = new Label(p.getPrice() + " €");

        // Contenedor de la tarjeta
        VBox tarjeta = new VBox(8, imagen, lblNombre, lblPrecio);
        tarjeta.setAlignment(Pos.CENTER);
        tarjeta.setPadding(new Insets(10));
        tarjeta.setPrefWidth(160);
        tarjeta.setStyle(
            "-fx-border-color: #cccccc; -fx-border-radius: 8; " +
            "-fx-background-radius: 8; -fx-background-color: white; " +
            "-fx-cursor: hand;");
        
        return tarjeta;
    }*/
    
    private Image cargarImagen(Product p) {
        try {
            // Si guardas la ruta/URL como String en el producto:
            return null ;//new Image(p.getImage(), 120, 120, true, true, true);  <---- Para cuando tengamos imagenes de productos
        } catch (Exception ex) {
            return null ;//new Image(getClass().getResourceAsStream("sin-imagen.png"));
        }
    }
    
    @FXML
    private void goBack(ActionEvent event) throws java.io.IOException {        
        Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ventanaActual.close();
    }
    
}
