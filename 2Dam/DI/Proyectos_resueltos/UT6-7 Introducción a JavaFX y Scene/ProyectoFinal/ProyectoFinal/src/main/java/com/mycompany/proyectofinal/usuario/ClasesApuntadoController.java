package com.mycompany.proyectofinal.usuario;

import com.mycompany.proyectofinal.Utileria;
import com.mycompany.proyectofinal.modelos.Clase;
import com.mycompany.proyectofinal.modelos.Usuario;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Walter
 */
public class ClasesApuntadoController implements Initializable {

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
    private Button botonDarseDeBaja;

    private Usuario user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configuro las columnas
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("tipoClase"));
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columnaHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        //Inicio el ObservableLists
        listaClases = FXCollections.observableArrayList();
    }

    public void initAttributes(Usuario user) {
        this.user = user;
        //Actualizo la tabla
        actualizarTabla();
    }

    @FXML
    private void darseDeBaja(ActionEvent event) {
        // Obtengo la clase seleccionada
        Clase claseSeleccionada = tablaClases.getSelectionModel().getSelectedItem();
        if (claseSeleccionada != null) {
            //Check
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Atención");
            alert.setTitle("Baja");
            alert.setContentText("¿Deseas darte de baja en esta clase?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                //Reembolsar clase
                user.setClasesPagadas(user.getClasesPagadas() - 1);
                Utileria.actualizarClasesPagadas(user);
                //Cambiar de estado la reserva
                Utileria.cambiarEstadoReservaCancelada(user, claseSeleccionada);
                //Check
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Check");
                alert.setContentText("¡Baja efectuada correctamente!");
                alert.showAndWait();
                //Actualizar tabla
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

    private void actualizarTabla() {
        //Limpio
        this.listaClases.clear();
        //Cargo la listaClases
        Utileria.cargarDatosClasesApuntado(listaClases, user);
        //Cargo la tabla
        tablaClases.setItems(listaClases);
    }

}
