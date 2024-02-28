package com.mycompany.proyectofinal.administrador;

import com.mycompany.proyectofinal.modelos.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Walter
 */
public class FormularioAltaUsuarioController implements Initializable {

    //Variables globales
    private Usuario usuario;

    @FXML
    private TextField inputNombre;
    @FXML
    private PasswordField inputContrasena;
    @FXML
    private TextField inputFotografia;
    @FXML
    private TextField inputDatos;
    @FXML
    private ComboBox<String> comboBoxTipoUsuario;
    @FXML
    private Button botonAlta;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicio comboBox
        comboBoxTipoUsuario.getItems().addAll("Administrador", "Usuario");
    }

    //Método que me devuelve el usuario
    public Usuario getUsuario() {
        return this.usuario;
    }

    @FXML
    private void alta(ActionEvent event) {
        //Recupero valores
        String nombre = inputNombre.getText();
        String contrasena = inputContrasena.getText();
        String fotografia = inputFotografia.getText();
        String datos = inputDatos.getText();
        String tipoUsuario = comboBoxTipoUsuario.getValue();
        int clasesPagadas = 0;
        //Controlo los campos que deseo que sean obligatorios
        if (nombre.equalsIgnoreCase("") || contrasena.equalsIgnoreCase("") || tipoUsuario == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("¡Los campos nombre,contraseña y tipo de usuario son obligatorios!");
            alert.showAndWait();
        } else {
            usuario = new Usuario(nombre, contrasena, fotografia, datos, clasesPagadas, tipoUsuario);
            // Cerrar ventana
            Stage stage = (Stage) botonAlta.getScene().getWindow();
            stage.close();
        }
    }
}
