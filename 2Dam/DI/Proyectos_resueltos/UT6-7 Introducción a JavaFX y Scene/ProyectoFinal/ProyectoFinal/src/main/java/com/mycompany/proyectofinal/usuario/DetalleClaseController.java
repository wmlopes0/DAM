package com.mycompany.proyectofinal.usuario;

import com.mycompany.proyectofinal.Utileria;
import com.mycompany.proyectofinal.modelos.Clase;
import com.mycompany.proyectofinal.modelos.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Walter
 */
public class DetalleClaseController implements Initializable {

    //Variables globales
    private Clase clase;
    private Usuario user;

    @FXML
    private Label labelNombreClase;
    @FXML
    private ImageView imagenClase;
    @FXML
    private Label labelFecha;
    @FXML
    private Label labelHora;
    @FXML
    private Button botonConfirmarReserva;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    void initAttributes(Clase claseSeleccionada, Usuario user) {
        this.clase = claseSeleccionada;
        this.user = user;
        //Inicio datos
        labelNombreClase.setText(clase.getTipoClase());
        labelFecha.setText(clase.getFecha().toString());
        labelHora.setText(clase.getHora().toString());
        imagenClase.setImage(new Image(getClass().getResourceAsStream("/img/" + Utileria.obtenerRutaImagenClase(clase.getTipo_clase_id()))));
    }

    @FXML
    private void confirmarReserva(ActionEvent event) {
        //Compruebo que el usuario no haya reservado ya la clase
        if (!Utileria.usuarioReservadoClase(clase, user)) {
            //Incremento clases pagadas usuario
            user.setClasesPagadas(user.getClasesPagadas() + 1);
            Utileria.actualizarClasesPagadas(user);
            //Creo reserva y la inserto en la bd
            Utileria.reservarClase(clase, user);
            //Check
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Check");
            alert.setContentText("¡Clase reservada correctamente!");
            alert.showAndWait();
            // Cerrar ventana
            Stage stage = (Stage) botonConfirmarReserva.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("¡Ya tienes reservada esta clase!");
            alert.showAndWait();
        }
    }

}
