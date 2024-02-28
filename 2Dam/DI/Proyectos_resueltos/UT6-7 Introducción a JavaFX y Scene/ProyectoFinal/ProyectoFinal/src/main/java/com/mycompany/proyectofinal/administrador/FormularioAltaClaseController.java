package com.mycompany.proyectofinal.administrador;

import com.mycompany.proyectofinal.Utileria;
import com.mycompany.proyectofinal.modelos.Clase;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Walter
 */
public class FormularioAltaClaseController implements Initializable {

    @FXML
    private DatePicker datePickerFecha;
    @FXML
    private ComboBox<Integer> comboBoxHora;
    @FXML
    private ComboBox<Integer> comboBoxMinuto;
    @FXML
    private ComboBox<String> comboBoxTipoClase;
    @FXML
    private Button botonAltaClase;

    private Clase clase;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicio los comboBox de Time
        for (int hour = 0; hour < 24; hour++) {
            comboBoxHora.getItems().add(hour);
        }
        for (int minute = 0; minute < 60; minute++) {
            comboBoxMinuto.getItems().add(minute);
        }
        //Inicio comboBox tipo clase
        Utileria.iniciarComboBoxTiposClase(comboBoxTipoClase);
    }

    public Clase getClase() {
        return clase;
    }

    @FXML
    private void alta(ActionEvent event) {
        //Recupero los valores
        String nombreTipoClase = comboBoxTipoClase.getValue();
        int tipoClaseId = 0;
        if (!nombreTipoClase.equalsIgnoreCase("")) {
            tipoClaseId = Utileria.obtenerIdTipoClase(nombreTipoClase);
        }
        Date fecha = Date.valueOf(datePickerFecha.getValue());
        Integer horaSeleccionada = comboBoxHora.getValue();
        Integer minutoSeleccionado = comboBoxMinuto.getValue();
        Time hora = null;
        // Verifica que se hayan seleccionado la hora y los minutos
        if (horaSeleccionada != null && minutoSeleccionado != null) {
            // Construye un LocalTime con los valores seleccionados
            LocalTime localTime = LocalTime.of(horaSeleccionada, minutoSeleccionado);
            // Convierte LocalTime a java.sql.Time
            hora = Time.valueOf(localTime);
        }

        //Verifico que hayan rellenado todos los campos
        if (tipoClaseId != 0 && fecha != null && hora != null) {
            //Creo clase con los datos
            this.clase = new Clase(tipoClaseId, fecha, hora);
            // Cerrar ventana
            Stage stage = (Stage) botonAltaClase.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("¡Todos los campos son obligatorios!");
            alert.showAndWait();
        }
    }
}
