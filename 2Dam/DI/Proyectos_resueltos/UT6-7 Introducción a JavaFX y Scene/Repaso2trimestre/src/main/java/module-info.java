module com.mycompany.repaso2trimestre {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.repaso2trimestre to javafx.fxml;
    exports com.mycompany.repaso2trimestre;
}
