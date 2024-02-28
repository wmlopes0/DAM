package com.mycompany.proyectofinal.administrador;

import com.mycompany.proyectofinal.Utileria;
import com.mycompany.proyectofinal.modelos.Clase;
import com.mycompany.proyectofinal.modelos.TipoClase;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Walter
 */
public class GestionClasesController implements Initializable {

    @FXML
    private TableView<Clase> tablaClases;
    //ObservableList
    private ObservableList<Clase> listaClases;

    @FXML
    private TableColumn columnaId;
    @FXML
    private TableColumn columnaTipoClase;
    @FXML
    private TableColumn columnaFecha;
    @FXML
    private TableColumn columnaHora;
    @FXML
    private Button botonAlta;
    @FXML
    private Button botonEliminar;
    @FXML
    private Button botonAddTipoClase;
    @FXML
    private AnchorPane panelPrincipal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configuro las columnas
        columnaId.setCellValueFactory(new PropertyValueFactory<>("clase_id"));
        columnaTipoClase.setCellValueFactory(new PropertyValueFactory<>("tipoClase"));
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columnaHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        //Inicio el ObservableLists
        listaClases = FXCollections.observableArrayList();
        //Actualizo la tabla
        actualizarTabla();
    }

    @FXML
    private void altaClase(ActionEvent event) {
        try {
            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectofinal/administrador/formularioAltaClase.fxml"));

            // Cargo la ventana
            Parent root = loader.load();

            // Cojo el controlador
            FormularioAltaClaseController controlador = loader.getController();

            // Creo el Scene
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            //Cojo la clase de vuelta
            Clase clase = controlador.getClase();
            if (clase != null) {
                //Añado la clase a la bd
                Utileria.anadirClase(clase);
                //Actualizo la tabla
                actualizarTabla();
                //Check
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Check");
                alert.setContentText("¡Clase creada correctamente!");
                alert.showAndWait();
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
    private void eliminarClase(ActionEvent event) {
        // Obtengo la clase seleccionada
        Clase claseSeleccionada = tablaClases.getSelectionModel().getSelectedItem();
        if (claseSeleccionada != null) {
            //Check
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Atención");
            alert.setTitle("Baja");
            alert.setContentText("¿Desea dar eliminar esta clase?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                //Reembolso la clase pagada a los usuarios que se habian apuntado
                Utileria.reembolsarClase(claseSeleccionada);
                //Elimino la clase de la bd
                Utileria.eliminarClase(claseSeleccionada);
                //Actualizo la tabla
                actualizarTabla();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("¡Debes elegir una clase!");
            alert.showAndWait();
        }
    }

    @FXML
    private void addTipoClase(ActionEvent event) {
        try {
            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectofinal/administrador/formularioAltaTipoClase.fxml"));

            // Cargo la ventana
            Parent root = loader.load();

            // Cojo el controlador
            FormularioAltaTipoClaseController controlador = loader.getController();

            // Creo el Scene
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            //Cojo el tipoClase de vuelta
            TipoClase tipoClase = controlador.getTipoClase();
            if (tipoClase != null) {
                //Añado el tipoClase a la bd
                Utileria.anadirTipoClase(tipoClase);
                //Actualizo la tabla
                actualizarTabla();
                //Check
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Check");
                alert.setContentText("¡Tipo de clase creado correctamente!");
                alert.showAndWait();
            }
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    private void actualizarTabla() {
        //Limpio
        this.listaClases.clear();
        //Cargo la listaUsuarios con los datos de la bd
        Utileria.cargarDatosClases(listaClases);
        //Cargo la tabla
        tablaClases.setItems(listaClases);
    }
}
