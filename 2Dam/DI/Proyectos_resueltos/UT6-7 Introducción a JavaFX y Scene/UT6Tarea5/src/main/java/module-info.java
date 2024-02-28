module com.clase.ut6tarea5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.clase.ut6tarea5 to javafx.fxml;
    exports com.clase.ut6tarea5;
}
