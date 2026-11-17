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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Employee;
import model.Gym;
import model.ImplementacionBD;
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
    Person p = LoginWindowController.usuario;
    ImplementacionBD implementacion = ImplementacionBD.getInstance();
    
    float totalPrice;
    ArrayList<String> gymNames = new ArrayList<>();
    
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button btnBack;
    @FXML
    private FlowPane contenedorProductos;
    @FXML
    private Button btnBuy;
    @FXML
    private ChoiceBox<String> choiceBoxWhere;
    @FXML
    private Label lblTotalPrice;
    @FXML
    private Label lblBalance;

    /**
     * Inicializa la ventana del carrito cargando los productos
     * almacenados en el carrito del usuario actual.
     *
     * @param url ubicación utilizada para resolver rutas relativas.
     * @param rb recursos utilizados para la internacionalización.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

            if (!(p instanceof WithCart)) {
                return;   
            }

            cart = ((WithCart) p).getCart();

            if (cart == null || cart.isEmpty()) {
                contenedorProductos.getChildren().add(new Label("El carrito está vacío"));
                lblTotalPrice.setText("0€");
                return;
            }

            for (VBox tarjeta : crearTarjetaProducto(cart)) {
                contenedorProductos.getChildren().add(tarjeta);
            }

            totalPrice = (float) cart.entrySet().stream()
                    .mapToDouble(e -> e.getKey().getPrice() * e.getValue())
                    .sum();
            lblTotalPrice.setText(totalPrice+"€");

            lblBalance.setText(Float.toString(p.getBalance()));

            for (Gym g : implementacion.obtenerGimnasios()) {
                gymNames.add(g.getDir());
            }
            ObservableList<String> gyms = FXCollections.observableArrayList(gymNames);
            choiceBoxWhere.setItems(gyms);


            if (p instanceof Employee) {
                Label lblDiscount = new Label();
                lblDiscount.setLayoutX(340);
                lblDiscount.setLayoutY(281);
                TextField textFieldDiscount = new PasswordField();
                textFieldDiscount.setLayoutX(430);
                textFieldDiscount.setLayoutY(277);
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
                tarjeta.setStyle(
                    "-fx-border-color: #cccccc; -fx-border-radius: 8; " +
                    "-fx-background-radius: 8; -fx-background-color: white; " +
                    "-fx-cursor: hand;");

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
    
    @FXML
    private void buy(ActionEvent event) throws java.io.IOException {
        if (!lblTotalPrice.getText().equals("0€")) {
            //Añadir el carrito entero (cart) al historial de compras (base de datos, crear una compra
            //en la coleccion de compras y añadir el id de la misma a la lista del usuario que hace la compra)

            p.setBalance(p.getBalance()-totalPrice);
            cart.clear();
            Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ventanaActual.close();
        }
    }
    
}
