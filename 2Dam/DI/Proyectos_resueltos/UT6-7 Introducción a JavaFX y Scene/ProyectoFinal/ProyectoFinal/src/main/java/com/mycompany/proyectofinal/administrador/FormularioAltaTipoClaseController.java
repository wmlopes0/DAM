package com.mycompany.proyectofinal.administrador;

import com.mycompany.proyectofinal.modelos.TipoClase;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Walter
 */
public class FormularioAltaTipoClaseController implements Initializable {

    @FXML
    private TextField inputNombre;
    @FXML
    private TextField inputCuposDisponibles;
    @FXML
    private TextField inputFotografia;
    @FXML
    private Button botonAlta;

    private TipoClase tipoClase;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public TipoClase getTipoClase() {
        return this.tipoClase;
    }

    @FXML
    private void alta(ActionEvent event) {
        //Recupero valores
        String nombre = inputNombre.getText();
        int cuposDisponibles = Integer.parseInt(inputCuposDisponibles.getText());
        String fotografia = inputFotografia.getText();

        //Controlo que estén completos los campos que quiero
        if (nombre.equalsIgnoreCase("") || cuposDisponibles == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("¡Los campos nombre y cupos disponibles son obligatorios!");
            alert.showAndWait();
        } else {
            this.tipoClase = new TipoClase(nombre, cuposDisponibles, fotografia);
            // Cerrar ventana
            Stage stage = (Stage) botonAlta.getScene().getWindow();
            stage.close();
        }
    }

}
