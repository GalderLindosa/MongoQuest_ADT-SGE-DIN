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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import dao.ImplementacionBD;
import model.Product;
import org.bson.Document;
/**
 * Controlador de la ventana de la tienda.
 * Permite visualizar los productos disponibles,
 * acceder al carrito, consultar el historial de compras
 * y seleccionar productos para añadirlos al carrito.
 *
 * @author unaiIbarguren
 */
public class ShopWindowController implements Initializable  {

    ImplementacionBD implementacion = ImplementacionBD.getInstance();
    public static Product pr;

    @FXML
    private Button btnCarrito;
    @FXML
    private Button btnHistorial;
    /**
    * Panel desplazable que muestra los productos.
    */
   @FXML
   private ScrollPane scrollPane;
   /**
    * Contenedor donde se añaden dinámicamente los productos.
    */
   @FXML
   private FlowPane contenedorProductos;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contenedorProductos.getChildren().clear();

        for (Product p : implementacion.obtenerProductos()) {
            contenedorProductos.getChildren().add(crearTarjetaProducto(p));
        }
    }


    /**
     * Crea la tarjeta gráfica asociada a un producto.
     *
     * @param p producto a mostrar.
     * @return tarjeta visual del producto.
     */
    private VBox crearTarjetaProducto(Product p) {
        // Imagen
        ImageView imagen = new ImageView(cargarImagen(p));
        imagen.setFitWidth(120);
        imagen.setFitHeight(120);
        imagen.setPreserveRatio(true);

        Label lblNombre = new Label(p.getName());
        lblNombre.setStyle("-fx-font-weight: bold;");
        lblNombre.setWrapText(true);
        lblNombre.setMaxWidth(140);

        Label lblPrecio = new Label(p.getPrice() + " €");

        VBox tarjeta = new VBox(8, imagen, lblNombre, lblPrecio); //Se añaden los elementos del producto al VBox (la tarjetita del producto
        tarjeta.setAlignment(Pos.CENTER);
        tarjeta.setPadding(new Insets(10));
        tarjeta.setPrefWidth(160);
        tarjeta.setStyle(
            "-fx-border-color: #cccccc; -fx-border-radius: 8; " +
            "-fx-background-radius: 8; -fx-background-color: white; " +
            "-fx-cursor: hand;");

        // Toda la tarjeta clickable
        tarjeta.setOnMouseClicked(e -> product(p));
        
        
        return tarjeta;
    }
    /**
     * Carga la imagen asociada a un producto.
     *
     * @param p producto cuya imagen se desea cargar.
     * @return imagen del producto.
     */
    private Image cargarImagen(Product p) {
        try {
               return null ;
        } catch (Exception ex) {
            return null ;
        }
    }
    
    /**
     * Abre la ventana del historial de compras.
     *
     * @param event evento generado al pulsar el botón.
     * @throws IOException si ocurre un error al cargar la ventana.
     */
    @FXML
    private void history(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("PurchaseHistory.fxml"));
        Parent root = fxmlLoader.load();
        
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    /**
     * Abre la ventana del carrito de compra.
     *
     * @throws IOException si ocurre un error al cargar la ventana.
     */
    @FXML
    private void cart() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CartWindow.fxml"));
        Parent root = fxmlLoader.load();
        
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    /**
     * Abre la ventana de información del producto seleccionado.
     *
     * @param p producto seleccionado.
     */
    private void product(Product p) {
        pr = p;
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
