package com.mycompany.proyectofinal.administrador;

import com.mycompany.proyectofinal.Utileria;
import com.mycompany.proyectofinal.modelos.Clase;
import com.mycompany.proyectofinal.modelos.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Walter
 */
public class UsuariosApuntadosController implements Initializable {

    @FXML
    private TableView<Usuario> tablaUsuarios;
    //ObservableList
    private ObservableList<Usuario> listaUsuarios;
    @FXML
    private TableColumn columnaId;
    @FXML
    private TableColumn columnaNombre;

    private Clase claseSeleccionada;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    void initAttributes(Clase claseSeleccionada) {
        this.claseSeleccionada = claseSeleccionada;
        // Configuro las columnas para mostrar las propiedades de Usuario
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        //Inicio el ObservableLists
        listaUsuarios = FXCollections.observableArrayList();
        //Actualizo la tabla
        actualizarTabla();
    }

    private void actualizarTabla() {
        //Limpio
        this.listaUsuarios.clear();
        //Cargo la listaUsuarios con los datos de la bd
        Utileria.cargarDatosUsuariosClase(listaUsuarios, claseSeleccionada);
        //Cargo la tabla
        tablaUsuarios.setItems(listaUsuarios);
    }

}
