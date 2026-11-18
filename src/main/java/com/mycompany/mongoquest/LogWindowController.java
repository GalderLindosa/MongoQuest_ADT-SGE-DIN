package com.mycompany.mongoquest;

import dao.ImplementacionBD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class LogWindowController implements Initializable {

    @FXML
    private TextArea txtLog;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        txtLog.setText(
                ImplementacionBD
                        .getInstance()
                        .leerLog()
        );
    }
}