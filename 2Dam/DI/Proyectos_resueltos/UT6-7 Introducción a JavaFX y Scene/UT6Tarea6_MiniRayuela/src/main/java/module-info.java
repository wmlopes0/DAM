module com.mycompany.ut6tarea6_minirayuela {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.ut6tarea6_minirayuela to javafx.fxml;
    exports com.mycompany.ut6tarea6_minirayuela;
}
