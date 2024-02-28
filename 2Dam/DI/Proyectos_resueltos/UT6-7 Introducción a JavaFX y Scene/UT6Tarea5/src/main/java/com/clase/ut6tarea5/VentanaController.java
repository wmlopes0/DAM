package com.clase.ut6tarea5;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wmartinl01
 */
public class VentanaController implements Initializable {

    @FXML
    private TableView<Fotografia> tablaFotografias;

    private ObservableList<Fotografia> listaFotografias;

    @FXML
    private Button botonVerImagen;
    @FXML
    private TextField inputNombre;
    @FXML
    private TextField inputUrl;
    @FXML
    private Button botonAniadir;
    @FXML
    private TableColumn nombre;
    @FXML
    private TableColumn foto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configuro las columnas para mostrar las propiedades de Fotografia
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        foto.setCellValueFactory(new PropertyValueFactory<>("url"));
        //Inicio el ObservableLists
        listaFotografias = FXCollections.observableArrayList();
        listaFotografias.add(new Fotografia("Gato", "https://imagenes.elpais.com/resizer/ryg0VAzr1f9Tf6ZP1X_EN_WjMXM=/1960x1470/filters:focal(1133x620:1143x630)/cloudfront-eu-central-1.images.arcpublishing.com/prisa/ZZIDTAAXOZEQXPZ2BP3VGO7KIY.jpg"));
        //Cargo la tabla
        tablaFotografias.setItems(listaFotografias);
    }

    @FXML
    private void verImagenVentanaNueva(ActionEvent event) {
        // Obtengo la persona seleccionada
        Fotografia fotografiaSeleccionada = tablaFotografias.getSelectionModel().getSelectedItem();

        if (fotografiaSeleccionada != null) {
            try {
                // Cargo la vista
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/clase/ut6tarea5/ventanaImagen.fxml"));

                // Cargo la ventana
                Parent root = loader.load();

                // Cojo el controlador
                VentanaImagenController controlador = loader.getController();
                controlador.initAttributes(fotografiaSeleccionada);

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
            alert.setContentText("¡Debes seleccionar una fotografía!");
            alert.showAndWait();
        }
    }

    @FXML
    private void aniadirFotografia(ActionEvent event) {
        //Recupero los valores
        String nombre = inputNombre.getText();
        String url = inputUrl.getText();
        if (nombre != "" && url != "") {
            //Creo una nueva fotografia
            Fotografia fotografia = new Fotografia(nombre, url);
            //Añado la fotografia al ObservableList
            this.listaFotografias.add(fotografia);
            // Refresco la tabla
            this.tablaFotografias.refresh();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("¡Debes introducir todos los datos!");
            alert.showAndWait();
        }
    }

}
