module com.mycompany.mongoquest {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.mongoquest to javafx.fxml;
    exports com.mycompany.mongoquest;
}
