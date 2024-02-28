package com.mycompany.proyectofinal.usuario;

import com.mycompany.proyectofinal.modelos.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Walter
 */
public class InterfazUsuarioController implements Initializable {

    //Usuario que ha iniciado sesión
    private Usuario user;
    @FXML
    private Button botonConsultarClasesApuntado;
    @FXML
    private Button botonConsultarClasesSemana;
    @FXML
    private ImageView imagenUsuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initAttributes(Usuario user) {
        this.user = user;
        //Cargo la imagen del usuario
        imagenUsuario.setImage(new Image(getClass().getResourceAsStream("/img/" + user.getFotografia())));
    }

    @FXML
    private void consultarClasesApuntado(ActionEvent event) {
        try {
            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectofinal/usuario/clasesApuntado.fxml"));
            // Cargo la ventana
            Parent root = loader.load();
            // Cojo el controlador
            ClasesApuntadoController controlador = loader.getController();
            //Mando el objeto user
            controlador.initAttributes(user);
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

    @FXML
    private void consultarClasesSemana(ActionEvent event) {
        try {
            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectofinal/usuario/visualizarClasesSemana.fxml"));
            // Cargo la ventana
            Parent root = loader.load();
            // Cojo el controlador
            VisualizarClasesSemanaController controlador = loader.getController();
            controlador.initAttributes(user);
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
