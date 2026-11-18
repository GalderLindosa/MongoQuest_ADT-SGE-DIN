package com.mycompany.mongoquest;

import dao.ImplementacionBD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

/**
 * Controlador de la ventana de visualización de logs del sistema.
 *
 * Permite consultar las acciones registradas por la aplicación almacenadas en
 * el fichero de registro.
 *
 * @author ire
 */
public class LogWindowController implements Initializable {

    /**
     * Área de texto donde se muestra el contenido completo del registro del
     * sistema.
     */
    @FXML
    private TextArea txtLog;

    /**
     * Inicializa la ventana cargando el contenido del fichero de log generado
     * por la aplicación y mostrándolo en el área de texto.
     *
     * @param url ubicación utilizada para resolver rutas.
     * @param rb recursos de internacionalización.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        txtLog.setText(
                ImplementacionBD
                        .getInstance()
                        .leerLog()
        );
    }
}
