/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mongoquest;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import model.Training;
import model.User;
import com.mycompany.mongoquest.LoginWindowController;
import java.util.List;
/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class WorkoutWindowController implements Initializable {

    @FXML
    private AnchorPane workoutsAnchor;
    @FXML
    private ListView<Object> workoutLists;
    @FXML
    private Label exerName;
    @FXML
    private Label amountReps;
    @FXML
    private Label exerWeight;
    @FXML
    private Label amountSets;
    
    private List <Object> works = new ArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        works = ((User)LoginWindowController.usuario).getTrainings();
        
       for(int i=0; i<works.size();i++){
           Object base = works.get(i);
           Training entrenamiento = (Training)base;
           
           
           workoutLists.getItems().add(entrenamiento.getTrainingName());
       }
        
    }    
    
    
    
    
    
    
    
}
