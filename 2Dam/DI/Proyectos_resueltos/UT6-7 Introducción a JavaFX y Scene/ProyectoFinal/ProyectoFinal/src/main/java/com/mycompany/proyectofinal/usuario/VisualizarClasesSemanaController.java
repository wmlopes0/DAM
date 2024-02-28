package com.mycompany.proyectofinal.usuario;

import com.mycompany.proyectofinal.Utileria;
import com.mycompany.proyectofinal.modelos.Clase;
import com.mycompany.proyectofinal.modelos.Usuario;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
public class VisualizarClasesSemanaController implements Initializable {

    //Variables globales
    private Date fechaInicio;
    private Date fechaFin;
    private Usuario user;
    @FXML
    private TableView<Clase> tablaClases;
    //ObservableList
    private ObservableList<Clase> listaClases;
    @FXML
    private TableColumn columnaNombre;
    @FXML
    private TableColumn columnaFecha;
    @FXML
    private TableColumn columnaHora;
    @FXML
    private Button botonVerClase;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicio fechas
        this.fechaInicio = Date.valueOf(LocalDate.now());
        this.fechaFin = Date.valueOf(LocalDate.now().plusDays(7));
        // Configuro las columnas
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("tipoClase"));
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columnaHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        //Inicio el ObservableLists
        listaClases = FXCollections.observableArrayList();
        //Actualizo la tabla
        actualizarTabla();
    }

    void initAttributes(Usuario user) {
        this.user = user;
    }

    @FXML
    private void verClase(ActionEvent event) {
        // Obtengo la clase seleccionada
        Clase claseSeleccionada = tablaClases.getSelectionModel().getSelectedItem();
        if (claseSeleccionada != null) {
            try {
                // Cargo la vista
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectofinal/usuario/detalleClase.fxml"));
                // Cargo la ventana
                Parent root = loader.load();
                // Cojo el controlador
                DetalleClaseController controlador = loader.getController();
                //Mando el objeto Clase
                controlador.initAttributes(claseSeleccionada, user);
                // Creo el Scene
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("¡Debes seleccionar una clase!");
            alert.showAndWait();
        }
    }

    private void actualizarTabla() {
        //Limpio
        this.listaClases.clear();
        //Cargo la listaClases con los datos de la bd
        Utileria.cargarDatosClasesFecha(listaClases, fechaInicio, fechaFin);
        //Cargo la tabla
        tablaClases.setItems(listaClases);
    }
}
