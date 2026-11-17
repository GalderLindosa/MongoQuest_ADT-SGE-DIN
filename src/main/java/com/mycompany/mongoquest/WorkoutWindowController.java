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
import javafx.event.ActionEvent;
import org.bson.Document;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class WorkoutWindowController implements Initializable {

    @FXML
    private AnchorPane workoutsAnchor;
    @FXML
    private ListView<String> workoutLists;
    @FXML
    private Label exerName;
    @FXML
    private Label amountReps;
    @FXML
    private Label exerWeight;
    @FXML
    private Label amountSets;

    private List<Training> works = new ArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        exerName.setVisible(false);
        amountReps.setVisible(false);
        amountSets.setVisible(false);
        exerWeight.setVisible(false);
        works = ((User) LoginWindowController.usuario).getTrainings();

        for (int i = 0; i < works.size(); i++) {
            workoutLists.getItems().add(works.get(i).getTrainingName());
        }

    }

    @FXML
    public void infoWorkouts() {

        String name = workoutLists.getSelectionModel().getSelectedItem();
        System.out.println(name);
        for (int i = 0; i < works.size(); i++) {
            if (works.get(i).getTrainingName().equalsIgnoreCase(name)) {
                exerName.setText(works.get(i).getTrainingName());
                exerWeight.setText(Integer.toString(works.get(i).getWeight()));
                amountReps.setText(Integer.toString(works.get(i).getReps()));
                amountSets.setText(Integer.toString(works.get(i).getSet()));
                exerName.setVisible(true);
                amountReps.setVisible(true);
                amountSets.setVisible(true);
                exerWeight.setVisible(true);
            } else {
                System.out.println("AAAAAAAAAAAA");
            }

        }
    }

}
