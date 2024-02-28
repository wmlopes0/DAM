package com.clase.ut6tarea5;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wmartinl01
 */
public class VentanaImagenController implements Initializable {

    @FXML
    private ImageView imageView;
    @FXML
    private Button botonCerrar;

    private Fotografia fotografia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void initAttributes(Fotografia fotografia) {
        // Asigna el objeto fotografia proporcionado al campo de la clase.
        this.fotografia = fotografia;

        // Crea un nuevo hilo para procesar la carga de la imagen.
        new Thread(() -> {
            try {
                // Obtiene la URL de la imagen del objeto fotografia.
                String imageUrl = fotografia.getUrl();
                // Crea una nueva imagen desde la URL, con carga en segundo plano.
                Image image = new Image(imageUrl, true);

                // Actualiza la interfaz de usuario con la imagen en el hilo principal.
                Platform.runLater(() -> imageView.setImage(image));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start(); // Inicia la ejecución del hilo.

    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
        // Cierro ventana
        Stage stage = (Stage) this.botonCerrar.getScene().getWindow();
        stage.close();
    }

}
