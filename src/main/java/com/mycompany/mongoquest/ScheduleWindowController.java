package com.mycompany.mongoquest;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import model.GymClass;

import model.HorarioTabla;
import dao.ImplementacionBD;
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
@FXML
private void createClass() {

    try {

        TextInputDialog idDialog = new TextInputDialog();
        idDialog.setTitle("Create Class");
        idDialog.setHeaderText("Enter class ID");

        Optional<String> idResult = idDialog.showAndWait();

        if (!idResult.isPresent()) {
            return;
        }

        int id = Integer.parseInt(idResult.get());
        if (bd.classExists(id)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("A class with that ID already exists.");

            alert.showAndWait();
            return;
        }
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setTitle("Create Class");
        nameDialog.setHeaderText("Enter class name");

        Optional<String> nameResult = nameDialog.showAndWait();

        if (!nameResult.isPresent()) {
            return;
        }

        String className = nameResult.get();
        if (bd.classNameExists(className)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("A class with that name already exists.");

            alert.showAndWait();
            return;
        }
        TextInputDialog trainerDialog = new TextInputDialog();
        trainerDialog.setTitle("Create Class");
        trainerDialog.setHeaderText("Enter trainer username");

        Optional<String> trainerResult = trainerDialog.showAndWait();

        if (!trainerResult.isPresent()) {
            return;
        }

        String trainer = trainerResult.get();
        if (!bd.employeeExists(trainer)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("The employee does not exist.");

            alert.showAndWait();
            return;
        }
        TextInputDialog gymDialog = new TextInputDialog();
        gymDialog.setTitle("Create Class");
        gymDialog.setHeaderText("Enter gym ID");

        Optional<String> gymResult = gymDialog.showAndWait();

        if (!gymResult.isPresent()) {
            return;
        }

        int gymId = Integer.parseInt(gymResult.get());
        if (!bd.gymExists(gymId)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("The gym does not exist.");

            alert.showAndWait();
            return;
        }
        TextInputDialog dayDialog = new TextInputDialog();
        dayDialog.setTitle("Create Class");
        dayDialog.setHeaderText("Enter day");

        Optional<String> dayResult = dayDialog.showAndWait();

        if (!dayResult.isPresent()) {
            return;
        }

        String day = dayResult.get();
        if (!day.equals("Monday")
        && !day.equals("Tuesday")
        && !day.equals("Wednesday")
        && !day.equals("Thursday")
        && !day.equals("Friday")
        && !day.equals("Saturday")
        && !day.equals("Sunday")) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid day.");

            alert.showAndWait();
            return;
        }

        TextInputDialog hourDialog = new TextInputDialog();
        hourDialog.setTitle("Create Class");
        hourDialog.setHeaderText("Enter hour");

        Optional<String> hourResult = hourDialog.showAndWait();

        if (!hourResult.isPresent()) {
            return;
        }

        String hour = hourResult.get();
        if (!hour.matches("\\d{2}:\\d{2}")) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Time must be in HH:MM format.");

            alert.showAndWait();
            return;
        }
        ArrayList<Schedule> schedules = new ArrayList<>();
        schedules.add(new Schedule(day, hour));

        GymClass gymClass = new GymClass(
                id,
                className,
                schedules,
                trainer,
                new ArrayList<>(),
                gymId
        );

        bd.createClass(gymClass);

        cargarHorario();

    } catch (Exception e) {
        e.printStackTrace();
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

            String textoClase =
                    gymClass.getId() + " - "
                    + gymClass.getClassName();

            switch (s.getDay()) {

                case "Monday":
                    fila.setMonday(textoClase);
                    break;

                case "Tuesday":
                    fila.setTuesday(textoClase);
                    break;

                case "Wednesday":
                    fila.setWednesday(textoClase);
                    break;

                case "Thursday":
                    fila.setThursday(textoClase);
                    break;

                case "Friday":
                    fila.setFriday(textoClase);
                    break;

                case "Saturday":
                    fila.setSaturday(textoClase);
                    break;

                case "Sunday":
                    fila.setSunday(textoClase);
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
        
       if (!bd.classExists(classId)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Class ID does not exist.");

            alert.showAndWait();
            return;
        }
        TextInputDialog trainerDialog = new TextInputDialog();

        trainerDialog.setTitle("Edit Trainer");
        trainerDialog.setHeaderText("Enter the new trainer username");
        trainerDialog.setContentText("Username:");

        Optional<String> trainerResult = trainerDialog.showAndWait();

        if (trainerResult.isPresent()) {

            String trainerUsername = trainerResult.get();

            if (!bd.employeeExists(trainerUsername)) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("The employee does not exist.");

                alert.showAndWait();
                return;
            }

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