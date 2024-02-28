package com.mycompany.ut6tarea4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
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
public class FormAddPersonaController implements Initializable {

    @FXML
    private TextField inputNombre;
    @FXML
    private TextField inputApellidos;
    @FXML
    private TextField inputEdad;
    @FXML
    private Button botonGuardar;
    @FXML
    private Button botonSalir;

    private ObservableList<Persona> listaPersonas;

    private Persona persona;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    //Si le paso un ObservableList estoy llamando al formulario para añadir una persona
    public void initAttributes(ObservableList<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    //Si le paso un objeto Persona estoy llamando al formulario para editar una persona
    public void initAttributes(Persona persona) {
        this.persona = persona;
        //Cargo los datos actuales de la persona en los input
        inputNombre.setText(persona.getNombre());
        inputApellidos.setText(persona.getApellidos());
        inputEdad.setText(persona.getEdad());
    }

    public Persona getPersona() {
        return this.persona;
    }

    @FXML
    private void guardar(ActionEvent event) {
        //Si listaPersonas no es null significa que tengo que añadir una persona sino la tengo que editar
        if (this.listaPersonas != null) {
            // Cojo los datos
            String nombre = this.inputNombre.getText();
            String apellidos = this.inputApellidos.getText();
            String edad = this.inputEdad.getText();

            // Creo la persona
            Persona p = new Persona(nombre, apellidos, edad);

            // Compruebo si la persona existe
            if (!listaPersonas.contains(p)) {
                listaPersonas.add(p);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Información");
                alert.setContentText("Se ha añadido correctamente");
                alert.showAndWait();

                // Cerrar ventana
                Stage stage = (Stage) this.botonGuardar.getScene().getWindow();
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("La persona ya existe");
                alert.showAndWait();
            }
        } else {
            // Cojo los datos
            String nombre = this.inputNombre.getText();
            String apellidos = this.inputApellidos.getText();
            String edad = this.inputEdad.getText();

            // Actualizo la pesona
            this.persona.setNombre(nombre);
            this.persona.setApellidos(apellidos);
            this.persona.setEdad(edad);

            //Muestro Check
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Información");
            alert.setContentText("Se ha actualizado correctamente");
            alert.showAndWait();

            // Cierro ventana
            Stage stage = (Stage) this.botonGuardar.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void salir(ActionEvent event) {
        this.persona = null;
        // Cerrar ventana
        Stage stage = (Stage) this.botonGuardar.getScene().getWindow();
        stage.close();
    }

}
