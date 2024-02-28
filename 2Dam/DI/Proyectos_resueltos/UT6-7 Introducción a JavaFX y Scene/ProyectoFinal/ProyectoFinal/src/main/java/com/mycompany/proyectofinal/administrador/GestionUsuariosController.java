package com.mycompany.proyectofinal.administrador;

import com.mycompany.proyectofinal.Utileria;
import com.mycompany.proyectofinal.modelos.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Walter
 */
public class GestionUsuariosController implements Initializable {

    @FXML
    private TableView<Usuario> tablaUsuarios;
    //ObservableList
    private ObservableList<Usuario> listaUsuarios;
    @FXML
    private TableColumn columnaId;
    @FXML
    private TableColumn columnaNombre;
    @FXML
    private TableColumn columnaDatos;
    @FXML
    private TableColumn columnaClasesPagadas;
    @FXML
    private TableColumn columnaTipoUsuario;
    @FXML
    private Button botonAlta;
    @FXML
    private Button botonBaja;
    @FXML
    private Button botonAddClasesPagadas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configuro las columnas para mostrar las propiedades de Usuario
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaDatos.setCellValueFactory(new PropertyValueFactory<>("datos"));
        columnaClasesPagadas.setCellValueFactory(new PropertyValueFactory<>("clasesPagadas"));
        columnaTipoUsuario.setCellValueFactory(new PropertyValueFactory<>("tipoUsuario"));
        //Inicio el ObservableLists
        listaUsuarios = FXCollections.observableArrayList();
        //Actualizo la tabla
        actualizarTabla();
    }

    @FXML
    private void alta(ActionEvent event) {
        try {
            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectofinal/administrador/formularioAltaUsuario.fxml"));

            // Cargo la ventana
            Parent root = loader.load();

            // Cojo el controlador
            FormularioAltaUsuarioController controlador = loader.getController();

            // Creo el Scene
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            //Cojo el usuario de vuelta
            Usuario user = controlador.getUsuario();
            if (user != null) {
                //Añado el usuario a la bd
                Utileria.anadirUsuario(user);
                //Actualizo la tabla
                actualizarTabla();
                //Check
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Check");
                alert.setContentText("¡Usuario dado de alta correctamente!");
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
    private void baja(ActionEvent event) {
        // Obtengo el usuario seleccionado
        Usuario usuarioSeleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
        if (usuarioSeleccionado != null) {
            //Check
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Atención");
            alert.setTitle("Baja");
            alert.setContentText("¿Desea dar de baja este usuario?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                //Elimina el usuario de la bd
                Utileria.bajaUsuario(usuarioSeleccionado);
                //Actualizo la tabla
                actualizarTabla();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("¡Debes elegir un usuario!");
            alert.showAndWait();
        }
    }

    @FXML
    private void anadirClasesPagadas(ActionEvent event) {
        // Obtengo el usuario seleccionado
        Usuario usuarioSeleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
        if (usuarioSeleccionado != null) {
            //Pido numero al usuario
            int clasesPagadas = pedirNumeroClases();
            //Actualizo las clases pagadas
            usuarioSeleccionado.setClasesPagadas(usuarioSeleccionado.getClasesPagadas() + clasesPagadas);
            // Actualizo el usuario en la bd
            Utileria.actualizarClasesPagadas(usuarioSeleccionado);
            //Actualizo la tabla
            actualizarTabla();
            //Check
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Check");
            alert.setContentText("¡Clases añadidas correctamente!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("¡Debes elegir un usuario!");
            alert.showAndWait();
        }
    }

    //Alerta personalizada para pedir un entero
    private int pedirNumeroClases() {
        AtomicInteger numeroIngresado = new AtomicInteger();
        // Creo el Alert
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Ingresar Clases Pagadas");
        alert.setHeaderText("Por favor, ingresa el número de clases:");

        // Creo TextField para el ingreso del número
        TextField textFieldNumero = new TextField();
        textFieldNumero.setPromptText("Clases pagadas");

        // Creo un GridPane para colocar el TextField
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        // Agrego TextField al GridPane
        grid.add(textFieldNumero, 0, 0);

        // Añado el GridPane al Alert
        alert.getDialogPane().setContent(grid);

        // Añado botones al Alert
        alert.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Muestro el Alert y espero la respuesta del usuario
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // El usuario presionó OK, puedes obtener el número ingresado
            String textoIngresado = textFieldNumero.getText();
            if (!textoIngresado.isEmpty()) {
                try {
                    numeroIngresado.set(Integer.parseInt(textoIngresado));
                } catch (NumberFormatException e) {
                    // Manejo la excepción en caso de que no sea un entero válido
                    Alert alertError = new Alert(Alert.AlertType.ERROR);
                    alertError.setTitle("Error de Validación");
                    alertError.setHeaderText(null);
                    alertError.setContentText("Debes ingresar un número entero válido.");
                    alertError.showAndWait();
                    return pedirNumeroClases(); // Llamada recursiva para pedir de nuevo
                }
            } else {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("Error de Validación");
                alertError.setHeaderText(null);
                alertError.setContentText("Debes ingresar un número entero.");
                alertError.showAndWait();
                return pedirNumeroClases(); // Llamada recursiva para pedir de nuevo
            }
        }
        //Retorno el entero
        return numeroIngresado.get();
    }

    private void actualizarTabla() {
        //Limpio
        this.listaUsuarios.clear();
        //Cargo la listaUsuarios con los datos de la bd
        Utileria.cargarDatosUsuarios(listaUsuarios);
        //Cargo la tabla
        tablaUsuarios.setItems(listaUsuarios);
    }
}
