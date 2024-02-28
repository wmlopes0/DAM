module com.clase.proyecto1javafx {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.clase.proyecto1javafx to javafx.fxml;
    exports com.clase.proyecto1javafx;
}
