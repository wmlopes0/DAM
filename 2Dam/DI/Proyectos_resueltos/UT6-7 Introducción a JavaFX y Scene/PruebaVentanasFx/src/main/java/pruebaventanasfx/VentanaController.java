/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package pruebaventanasfx;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rdlrosac01
 */
public class VentanaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /*El el cargar ventana Throws IOExeptionm
     */
    @FXML
    private void cargarventana(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("segundaventana.fxml"));

        Parent root = loader.load();

        SegundaVentanaController controlador = (SegundaVentanaController) loader.getController();
        controlador.getDatos().setText("Mi texto propio");

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    private void abrirAlerta(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Atención");
        alert.setTitle("cuidado");
        alert.setContentText("Estamos en mantenimiento");
        alert.showAndWait();
    }

    @FXML
    private void pedirConfirmacion(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Atención");
        alert.setTitle("cuidado");
        alert.setContentText("Continuar instalación");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            System.out.println("Ha pulsado OK");
        } else {
            System.out.println("Ha pulsado Cancel");
        }
    }

    @FXML
    private void pedirTexto(ActionEvent event) {
        TextInputDialog tid = new TextInputDialog(); 
        tid.setHeaderText(null);
        tid.setTitle("Insertar");
        tid.setContentText("Introduce un valor");
        Optional<String> texto = tid.showAndWait();
        System.out.println(texto.get());
        
    }
}
