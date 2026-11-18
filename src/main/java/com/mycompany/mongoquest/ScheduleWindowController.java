package com.mycompany.mongoquest;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import model.GymClass;

import model.HorarioTabla;
import model.ImplementacionBD;
import model.Schedule;

public class ScheduleWindowController implements Initializable {

    @FXML
    private TableView<HorarioTabla> ScheduleTable;

    @FXML
    private TableColumn<HorarioTabla, String> hourColumn;

    @FXML
    private TableColumn<HorarioTabla, String> mondayColumn;

    @FXML
    private TableColumn<HorarioTabla, String> tuesdayColumn;

    @FXML
    private TableColumn<HorarioTabla, String> wednesdayColumn;

    @FXML
    private TableColumn<HorarioTabla, String> thursdayColumn;

    @FXML
    private TableColumn<HorarioTabla, String> fridayColumn;

    @FXML
    private TableColumn<HorarioTabla, String> saturdayColumn;

    @FXML
    private TableColumn<HorarioTabla, String> sundayColumn;

    @FXML
    private Button btnCreateClass;

    @FXML
    private Button btnEditClass;

    @FXML
    private Button btnDeleteClass;

    @FXML
    private Button btnUnsubscribe;

    private ImplementacionBD bd = ImplementacionBD.getInstance();

    private String username;
    private String role;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        hourColumn.setCellValueFactory(new PropertyValueFactory<>("hour"));
        mondayColumn.setCellValueFactory(new PropertyValueFactory<>("monday"));
        tuesdayColumn.setCellValueFactory(new PropertyValueFactory<>("tuesday"));
        wednesdayColumn.setCellValueFactory(new PropertyValueFactory<>("wednesday"));
        thursdayColumn.setCellValueFactory(new PropertyValueFactory<>("thursday"));
        fridayColumn.setCellValueFactory(new PropertyValueFactory<>("friday"));
        saturdayColumn.setCellValueFactory(new PropertyValueFactory<>("saturday"));
        sundayColumn.setCellValueFactory(new PropertyValueFactory<>("sunday"));
    }

    public void setUser(String username, String role) {
    System.out.println("Username: " + username);
    System.out.println("Role: " + role);
        this.username = username;
        this.role = role;

        configurarPermisos(); 
        cargarHorario();
    }

    private void configurarPermisos() {

        if ("boss".equals(role)) {

            btnCreateClass.setVisible(true);
            btnEditClass.setVisible(true);
            btnDeleteClass.setVisible(true);
            btnUnsubscribe.setVisible(false);

        } else if ("employee".equals(role)){

            btnCreateClass.setVisible(false);
            btnEditClass.setVisible(false);
            btnDeleteClass.setVisible(false);
            btnUnsubscribe.setVisible(false);

        } else if ("member".equals(role)) {

            btnCreateClass.setVisible(false);
            btnEditClass.setVisible(false);
            btnDeleteClass.setVisible(false);
            btnUnsubscribe.setVisible(true);
        }
    }

    private void cargarHorario() {

    ArrayList<GymClass> clases = bd.getGymClasses();

    ScheduleTable.getItems().clear();

    HashMap<String, HorarioTabla> filas = new HashMap<>();

    for (GymClass gymClass : clases) {

        for (Schedule s : gymClass.getSchedule()) {

            HorarioTabla fila = filas.get(s.getHour());

            if (fila == null) {
                fila = new HorarioTabla();
                fila.setHour(s.getHour());
                filas.put(s.getHour(), fila);
            }

            switch (s.getDay()) {

                case "Monday":
                    fila.setMonday(gymClass.getClassName());
                    break;

                case "Tuesday":
                    fila.setTuesday(gymClass.getClassName());
                    break;

                case "Wednesday":
                    fila.setWednesday(gymClass.getClassName());
                    break;

                case "Thursday":
                    fila.setThursday(gymClass.getClassName());
                    break;

                case "Friday":
                    fila.setFriday(gymClass.getClassName());
                    break;

                case "Saturday":
                    fila.setSaturday(gymClass.getClassName());
                    break;

                case "Sunday":
                    fila.setSunday(gymClass.getClassName());
                    break;
            }
        }
    }

        ScheduleTable.getItems().addAll(filas.values());
    }
@FXML
private void editClass() {

    TextInputDialog idDialog = new TextInputDialog();

    idDialog.setTitle("Edit Class");
    idDialog.setHeaderText("Enter the class ID");
    idDialog.setContentText("Class ID:");

    Optional<String> idResult = idDialog.showAndWait();

    if (idResult.isPresent()) {

        int classId = Integer.parseInt(idResult.get());

        TextInputDialog trainerDialog = new TextInputDialog();

        trainerDialog.setTitle("Edit Trainer");
        trainerDialog.setHeaderText("Enter the new trainer username");
        trainerDialog.setContentText("Username:");

        Optional<String> trainerResult = trainerDialog.showAndWait();

        if (trainerResult.isPresent()) {

            bd.updateClassTrainer(
                    classId,
                    trainerResult.get()
            );

            cargarHorario();

            System.out.println("Class updated successfully");
        }
    }
}

@FXML
private void deleteClass() {

    TextInputDialog dialog = new TextInputDialog();

    dialog.setTitle("Delete Class");
    dialog.setHeaderText("Enter the class ID");
    dialog.setContentText("Class ID:");

    Optional<String> result = dialog.showAndWait();

    if (result.isPresent()) {

        int id = Integer.parseInt(result.get());

        bd.deleteClass(id);

        cargarHorario();

        System.out.println("Class deleted successfully");
    }
}

@FXML
private void unsubscribe() {

    // JavaFX dialog used to ask the user for a value

    TextInputDialog dialog = new TextInputDialog();

    dialog.setTitle("Unsubscribe from Class");
    dialog.setHeaderText("Enter the class ID");
    dialog.setContentText("Class ID:");

    Optional<String> result = dialog.showAndWait();

    if (result.isPresent()) {

        int classId = Integer.parseInt(result.get());

        bd.removeMemberFromClass(
                classId,
                username
        );

        cargarHorario();

        System.out.println("Successfully unsubscribed from the class");
    }
}

}