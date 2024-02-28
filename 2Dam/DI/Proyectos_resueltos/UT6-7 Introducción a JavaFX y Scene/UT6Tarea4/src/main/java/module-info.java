module com.mycompany.ut6tarea4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.ut6tarea4 to javafx.fxml;
    exports com.mycompany.ut6tarea4;
}
