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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import dao.ImplementacionBD;
import model.Person;
import model.Product;
import model.ShoppingHistory;
/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class PurchaseHistoryController implements Initializable {

    Person pr = LoginWindowController.usuario;
    ImplementacionBD implementacion = ImplementacionBD.getInstance();

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox contenedorCompras;
    @FXML
    private Button btnBack;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<ShoppingHistory> compras = implementacion.findPurchasesByUser(pr.getUserName());

        if (compras.isEmpty()) {
            contenedorCompras.getChildren().add(new Label("Todavía no has hecho ninguna compra"));
            return;
        }

        Map<Integer, Product> products = implementacion.loadProducts();

        for (VBox tarjeta : crearTarjetasCompra(compras, products)) {
            contenedorCompras.getChildren().add(tarjeta);
        }
    }

    private List<VBox> crearTarjetasCompra(List<ShoppingHistory> compras, Map<Integer, Product> products) {
        ArrayList<VBox> lista = new ArrayList<>();

        for (ShoppingHistory compra : compras) {
            Label lblTitulo = new Label("Compra #" + compra.getShoppingHistoryId()
                    + " · " + compra.getDate());
            lblTitulo.setStyle("-fx-font-weight: bold;");

            Label lblTotal = new Label(String.format("%.2f €", compra.getTotalPrice()));

            Region espacio = new Region();
            HBox.setHgrow(espacio, Priority.ALWAYS);

            VBox tarjeta = new VBox(5, new HBox(lblTitulo, espacio, lblTotal));

            for (Map.Entry<Integer, Integer> linea : compra.getProducts().entrySet()) {
                Product prod = products.get(linea.getKey());
                int cantidad = linea.getValue();
                String texto = (prod != null)
                        ? String.format("%s x%d · %.2f €", prod.getName(), cantidad, prod.getPrice() * cantidad)
                        : "Producto " + linea.getKey() + " x" + cantidad;
                Label lblLinea = new Label(texto);
                lblLinea.setWrapText(true);
                tarjeta.getChildren().add(lblLinea);
            }

            tarjeta.setPadding(new Insets(10));
            tarjeta.setPrefWidth(270);
            tarjeta.setStyle(
                    "-fx-border-color: #cccccc; -fx-border-radius: 8; " +
                    "-fx-background-radius: 8; -fx-background-color: white;");

            lista.add(tarjeta);
        }
        return lista;
    }

    @FXML
    private void goBack(ActionEvent event) {
        Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ventanaActual.close();
    }
}
