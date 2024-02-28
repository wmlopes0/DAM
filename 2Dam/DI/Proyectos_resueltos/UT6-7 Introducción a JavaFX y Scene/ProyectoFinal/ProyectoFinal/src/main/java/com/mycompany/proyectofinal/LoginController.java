package com.mycompany.proyectofinal;

import com.mycompany.proyectofinal.usuario.InterfazUsuarioController;
import com.mycompany.proyectofinal.administrador.InterfazAdministradorController;
import com.mycompany.proyectofinal.modelos.Usuario;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Walter
 */
public class LoginController implements Initializable {

    @FXML
    private TextField inputUsuario;
    @FXML
    private PasswordField inputContrasena;
    @FXML
    private Button botonIniciarSesion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void iniciarSesion(ActionEvent event) throws IOException {
        //Recupero los campos
        String usuario = inputUsuario.getText();
        String contrasena = "";
        try {
            contrasena = Utileria.generarHash(inputContrasena.getText());
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error al generar Hash");
        }
        //Obtengo el usuario de la bd si existe
        Usuario user = Utileria.obtenerUsuario(usuario, contrasena);
        //Si el usuario es distinto de null significa que existe
        if (user != null) {
            //Si el usuario es administrador, cargo la interfaz de administrador
            if (user.getTipoUsuario().equalsIgnoreCase("Administrador")) {
                cargarInterfazAdministrador(user);
            } else {
                cargarInterfazUsuario(user);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error de acceso");
            alert.setContentText("Datos incorrectos");
            alert.showAndWait();
            //Limpio los datos
            inputUsuario.setText("");
            inputContrasena.setText("");
        }
        //Limpio campos
        inputContrasena.setText("");
        inputUsuario.setText("");
    }

    //Método para cargar la interfaz Administrador
    private void cargarInterfazAdministrador(Usuario user) {
        try {
            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectofinal/administrador/interfazAdministrador.fxml"));

            // Cargo la ventana
            Parent root = loader.load();

            // Cojo el controlador
            InterfazAdministradorController controlador = loader.getController();
            controlador.initAttributes(user);//Le paso el objeto usuario

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
    }

    //Método para cargar la interfaz Usuario
    private void cargarInterfazUsuario(Usuario user) {
        try {
            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectofinal/usuario/interfazUsuario.fxml"));

            // Cargo la ventana
            Parent root = loader.load();

            // Cojo el controlador
            InterfazUsuarioController controlador = loader.getController();
            controlador.initAttributes(user); //Le paso el objeto usuario

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
    }

}
