package com.mycompany.proyectofinal.administrador;

import com.mycompany.proyectofinal.Utileria;
import com.mycompany.proyectofinal.modelos.Clase;
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
public class ConsultarSemanaController implements Initializable {

    //Variables globales
    private Date fechaInicio;
    private Date fechaFin;
    
    @FXML
    private TableView<Clase> tablaClasesSemana;
    //ObservableList
    private ObservableList<Clase> listaClases;
    @FXML
    private TableColumn columnaNombreClase;
    @FXML
    private TableColumn columnaFecha;
    @FXML
    private TableColumn columnaHora;
    @FXML
    private Button botonVerUsuariosApuntados;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicio fechas
        this.fechaInicio = Date.valueOf(LocalDate.now());
        this.fechaFin = Date.valueOf(LocalDate.now().plusDays(7));
        // Configuro las columnas
        columnaNombreClase.setCellValueFactory(new PropertyValueFactory<>("tipoClase"));
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columnaHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        //Inicio el ObservableLists
        listaClases = FXCollections.observableArrayList();
        //Actualizo la tabla
        actualizarTabla();
    }
    
    @FXML
    private void verUsuariosApuntados(ActionEvent event) {
        // Obtengo la clase seleccionada
        Clase claseSeleccionada = tablaClasesSemana.getSelectionModel().getSelectedItem();
        if (claseSeleccionada != null) {
            
            try {
                // Cargo la vista
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectofinal/administrador/usuariosApuntados.fxml"));
                // Cargo la ventana
                Parent root = loader.load();
                // Cojo el controlador
                UsuariosApuntadosController controlador = loader.getController();
                //Mando el objeto Clase
                controlador.initAttributes(claseSeleccionada);
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
        //Cargo la listaUsuarios con los datos de la bd
        Utileria.cargarDatosClasesFecha(listaClases, fechaInicio, fechaFin);
        //Cargo la tabla
        tablaClasesSemana.setItems(listaClases);
    }
    
}
