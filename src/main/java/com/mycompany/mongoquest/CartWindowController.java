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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import dao.ImplementacionBD;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
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
    private float totalPrice;
    private TextField textFieldDiscount;
    
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button btnBack;
    @FXML
    private FlowPane contenedorProductos;
    @FXML
    private Button btnBuy;
    @FXML
    private ChoiceBox<Gym> choiceBoxWhere;
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

        lblBalance.setText(Float.toString(p.getBalance()));

        ObservableList<Gym> gyms = FXCollections.observableArrayList(implementacion.obtenerGimnasios());
        choiceBoxWhere.setItems(gyms);

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

        if (p instanceof Employee) {

            Label lblDiscount = new Label("Código de descuento:");
            lblDiscount.setLayoutX(300);
            lblDiscount.setLayoutY(281);

            textFieldDiscount = new TextField();
            textFieldDiscount.setLayoutX(430);
            textFieldDiscount.setLayoutY(277);
            textFieldDiscount.setPrefWidth(120);

            Button btnDiscount = new Button("Aplicar");
            btnDiscount.setLayoutX(560);
            btnDiscount.setLayoutY(277);

            btnDiscount.setOnAction(e -> applyDiscount());

            anchorPane.getChildren().addAll(
                    lblDiscount,
                    textFieldDiscount,
                    btnDiscount
            );
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
            Product prod = e.getKey();
            int cantidad = e.getValue();

            Label lblNombre = new Label(prod.getName());
            lblNombre.setStyle("-fx-font-weight: bold;");

            Label lblTotal = new Label(String.format("%.2f €", prod.getPrice() * cantidad));

            Region espacio = new Region();
            HBox.setHgrow(espacio, Priority.ALWAYS);

            VBox tarjeta = new VBox(5, new HBox(lblNombre, espacio, lblTotal));

            tarjeta.getChildren().add(new Label("Cantidad: " + cantidad));
            tarjeta.getChildren().add(new Label(String.format("Precio unitario: %.2f €", prod.getPrice())));

            tarjeta.setPadding(new Insets(10));
            tarjeta.setStyle(
                    "-fx-border-color: #cccccc; -fx-border-radius: 8; " +
                    "-fx-background-radius: 8; -fx-background-color: white;");

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
                // Si guardas la ruta/URL como String en el producto:
                return null ;//new Image(p.getImage(), 120, 120, true, true, true);  <---- Para cuando tengamos imagenes de productos
            } catch (Exception ex) {
                return null ;//new Image(getClass().getResourceAsStream("sin-imagen.png"));
            }
        }

      /**
     * Cierra la ventana actual y vuelve a la pantalla anterior.
     *
     * @param event evento generado al pulsar el botón volver.
     * @throws java.io.IOException si ocurre un error durante el proceso.
     */
    @FXML
    private void goBack(ActionEvent event) throws java.io.IOException {        
        Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ventanaActual.close();
    }
    
    @FXML
    private void buy(ActionEvent event) throws java.io.IOException {
        if (!cart.isEmpty()) {
            if (choiceBoxWhere.getValue() == null) {
                showAlert("Error de compra", "Debes seleccionar un gimnasio donde recoger el pedido.");
            } else if (p.getBalance() > totalPrice) {
                implementacion.comprar(
                        cart,
                        choiceBoxWhere.getValue().getGymId(),
                        p.getUserName(),
                        totalPrice
                );
                p.setBalance(p.getBalance() - totalPrice);
                cart.clear();

                Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
                ventanaActual.close();
            } else {
                showAlert("Error de compra", "No se puede realizar la compra por falta de fondos.");
            }
        } else {
            showAlert("Error de compra", "No se puede realizar una compra con el carrito vacio.");
        }
    }
    
    private void showAlert(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);   // quita la cabecera para que quede más limpio
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    private void applyDiscount() {
        Employee empleado = (Employee) p;

        if (textFieldDiscount.getText().equals(empleado.getDiscountCode())) {
            totalPrice = Math.round(totalPrice * 0.80f * 100) / 100.0f;
            lblTotalPrice.setText(totalPrice + "€");
            showAlert("Descuento aplicado", "Se ha aplicado correctamente el descuento del 20%.");
        } else {
            showAlert("Código incorrecto", "El código de descuento introducido no es válido.");
        }
    }
    
}
