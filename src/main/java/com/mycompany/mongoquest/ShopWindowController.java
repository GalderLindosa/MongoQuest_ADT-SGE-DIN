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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ImplementacionBD;
import model.Product;
import org.bson.Document;
/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class ShopWindowController implements Initializable {

    ImplementacionBD implementacion = ImplementacionBD.getInstance();
    public static Product pr;

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

        for (Product p : implementacion.obtenerProductos()) {
            contenedorProductos.getChildren().add(crearTarjetaProducto(p));
        }
    }

    private VBox crearTarjetaProducto(Product p) {
        // Imagen
        ImageView imagen = new ImageView(cargarImagen(p));
        imagen.setFitWidth(120);
        imagen.setFitHeight(120);
        imagen.setPreserveRatio(true);

        // Nombre y precio
        Label lblNombre = new Label(p.getName());
        lblNombre.setStyle("-fx-font-weight: bold;");
        lblNombre.setWrapText(true);
        lblNombre.setMaxWidth(140);

        Label lblPrecio = new Label(p.getPrice() + " €");

        // Contenedor de la tarjeta
        VBox tarjeta = new VBox(8, imagen, lblNombre, lblPrecio); //Se añaden los elementos del producto al VBox (la tarjetita del producto
        tarjeta.setAlignment(Pos.CENTER);
        tarjeta.setPadding(new Insets(10));
        tarjeta.setPrefWidth(160);
        /*tarjeta.setStyle(
            "-fx-border-color: #cccccc; -fx-border-radius: 8; " +
            "-fx-background-radius: 8; -fx-background-color: white; " +
            "-fx-cursor: hand;");*/

        // Toda la tarjeta clickable
        tarjeta.setOnMouseClicked(e -> product(p));
        /*tarjeta.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                product(p);
            }
        });*/
        
        return tarjeta;
    }
    
    private Image cargarImagen(Product p) {
        try {
            // Si guardas la ruta/URL como String en el producto:
            return null ;//new Image(p.getImage(), 120, 120, true, true, true);  <---- Para cuando tengamos imagenes de productos
        } catch (Exception ex) {
            return null ;//new Image(getClass().getResourceAsStream("sin-imagen.png"));
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
    private void cart() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CartWindow.fxml"));
        Parent root = fxmlLoader.load();
        
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
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
