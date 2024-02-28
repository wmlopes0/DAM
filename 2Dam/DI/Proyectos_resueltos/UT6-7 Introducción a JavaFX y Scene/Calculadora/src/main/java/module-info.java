module com.clase.calculadora {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.clase.calculadora to javafx.fxml;
    exports com.clase.calculadora;
}
