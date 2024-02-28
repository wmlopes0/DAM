module com.mycompany.proyectofinal {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    // Abro los paquetes al módulo javafx.fxml
    opens com.mycompany.proyectofinal to javafx.fxml;
    opens com.mycompany.proyectofinal.administrador to javafx.fxml;
    opens com.mycompany.proyectofinal.usuario to javafx.fxml;
    opens com.mycompany.proyectofinal.modelos to javafx.base;

    // Exporto los paquetes para que sean accesibles por otros módulos
    exports com.mycompany.proyectofinal;
    exports com.mycompany.proyectofinal.administrador;
    exports com.mycompany.proyectofinal.usuario;
}
