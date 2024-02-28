package com.mycompany.ut6tarea4;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Walter
 */
public class VentanaController implements Initializable {

    @FXML
    private TableView<Persona> tabla;

    private ObservableList<Persona> listaPersonas;

    @FXML
    private TableColumn nombre;
    @FXML
    private TableColumn apellidos;
    @FXML
    private TableColumn edad;
    @FXML
    private Button botonAgregar;
    @FXML
    private Button botonModificar;
    @FXML
    private Button botonEliminar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configuro las columnas para mostrar las propiedades de Persona
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        edad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        //Inicio el ObservableLists
        listaPersonas = FXCollections.observableArrayList();
        listaPersonas.add(new Persona("Walter", "Martín Lopes", "25"));
        listaPersonas.add(new Persona("Raquel", "Barbero Sánchez", "27"));
        //Cargo la tabla
        tabla.setItems(listaPersonas);
    }

    @FXML
    private void agregarPersona(ActionEvent event) {
        try {
            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/ut6tarea4/formAddPersona.fxml"));

            // Cargo la ventana
            Parent root = loader.load();

            // Cojo el controlador
            FormAddPersonaController controlador = loader.getController();
            controlador.initAttributes(listaPersonas);

            // Creo el Scene
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            // cojo la persona devuelta
            Persona p = controlador.getPersona();
            if (p != null) {
                // Añado la persona
                this.listaPersonas.add(p);

                // Refresco la tabla
                this.tabla.refresh();
            }
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void modificarPersona(ActionEvent event) {
        // Obtengo la persona seleccionada
        Persona personaSeleccionada = tabla.getSelectionModel().getSelectedItem();
        try {
            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/ut6tarea4/formAddPersona.fxml"));

            // Cargo la ventana
            Parent root = loader.load();

            // Cojo el controlador
            FormAddPersonaController controlador = loader.getController();
            controlador.initAttributes(personaSeleccionada);

            // Creo el Scene
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            //Actualizo la tabla
            this.tabla.refresh();

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }

    }

    @FXML
    private void eliminarPersona(ActionEvent event) {
        // Obtengo la persona seleccionada
        Persona personaSeleccionada = tabla.getSelectionModel().getSelectedItem();
        if (personaSeleccionada != null) {
            //Check
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Atención");
            alert.setTitle("Eliminar");
            alert.setContentText("¿Desea eliminar esta persona?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                // Elimina la persona seleccionada de la lista
                listaPersonas.remove(personaSeleccionada);

            }
        }
    }

}
