module pruebaventanasfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens pruebaventanasfx to javafx.fxml;
    exports pruebaventanasfx;
}
