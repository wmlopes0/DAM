package com.mycompany.ut6tarea2_vistatabla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController {

    @FXML
    private TableView<Persona> tabla;
    private ObservableList<Persona> listaPersonas;
    @FXML
    private TableColumn columnaNombre;
    @FXML
    private TableColumn columnaApellidos;
    @FXML
    private TableColumn columnaEdad;
    @FXML
    private TextField inputNombre;
    @FXML
    private TextField inputApellidos;
    @FXML
    private TextField inputEdad;
    @FXML
    private Button botonAgregar;
    @FXML
    private Button botonModificar;
    @FXML
    private Button botonEliminar;

    @FXML
    public void initialize() {
        // Configuro las columnas para mostrar las propiedades de Persona
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        columnaEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        //Inicio el ObservableLists
        listaPersonas = FXCollections.observableArrayList();
    }

    @FXML
    private void agregarPersona(ActionEvent event) {
        //Recupero los datos del usuario
        String nombre = inputNombre.getText();
        String apellidos = inputApellidos.getText();
        String edad = inputEdad.getText();

        //Creo Persona y la añado al ObservableList
        Persona persona = new Persona(nombre, apellidos, edad);
        listaPersonas.add(persona);

        //Añado la persona a la lista
        tabla.setItems(listaPersonas);

        //Limpio campos
        inputNombre.setText("");
        inputApellidos.setText("");
        inputEdad.setText("");
    }

    @FXML
    private void modificarPersona(ActionEvent event) {
        // Obtengo la persona seleccionada
        Persona personaSeleccionada = tabla.getSelectionModel().getSelectedItem();
        if (personaSeleccionada != null) {
            // Actualizo los datos de la persona solo en los campos que el usuario ha modificado
            if (!inputNombre.getText().equalsIgnoreCase("")) {
                personaSeleccionada.setNombre(inputNombre.getText());
            }
            if (!inputApellidos.getText().equalsIgnoreCase("")) {
                personaSeleccionada.setApellidos(inputApellidos.getText());
            }
            if (!inputEdad.getText().equalsIgnoreCase("")) {
                personaSeleccionada.setEdad(inputEdad.getText());
            }

            // Refresco la tabla
            tabla.refresh();

            // Limpio los campos
            inputNombre.setText("");
            inputApellidos.setText("");
            inputEdad.setText("");
        }
    }

    @FXML
    private void eliminarPersona(ActionEvent event) {
        // Obtengo la persona seleccionada
        Persona personaSeleccionada = tabla.getSelectionModel().getSelectedItem();
        if (personaSeleccionada != null) {
            // Elimina la persona seleccionada de la lista
            listaPersonas.remove(personaSeleccionada);
        }
    }

}
