/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mongoquest;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class HorarioWindowController implements Initializable {


    @FXML
    private TableView<?> ScheduleTable;
    @FXML
    private TableColumn<?, ?> mondayColumn;
    @FXML
    private TableColumn<?, ?> tuesdayColumn;
    @FXML
    private TableColumn<?, ?> wednesdayColumn;
    @FXML
    private TableColumn<?, ?> thursdayColumn;
    @FXML
    private TableColumn<?, ?> fridayColumn;
    @FXML
    private TableColumn<?, ?> saturdayRow;
    @FXML
    private TableColumn<?, ?> sundayRow;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
