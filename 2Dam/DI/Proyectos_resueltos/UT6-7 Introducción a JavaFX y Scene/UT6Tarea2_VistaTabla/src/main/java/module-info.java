module com.mycompany.ut6tarea2_vistatabla {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.ut6tarea2_vistatabla to javafx.fxml;
    exports com.mycompany.ut6tarea2_vistatabla;
}
