package com.mycompany.repaso2trimestre;

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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Walter
 */
public class VentanaPrincipalController implements Initializable {

    @FXML
    private TableView<Hamburguesa> tablaPedido;

    private ObservableList<Hamburguesa> lHamburguesas;

    @FXML
    private TableColumn columnaHamburguesa;
    @FXML
    private TableColumn columnaPan;
    @FXML
    private TableColumn columnaExtras;
    @FXML
    private TableColumn columnaPrecio;
    @FXML
    private Button botonEliminarDelPedido;
    @FXML
    private Button botonCobrar;
    @FXML
    private ImageView imagen;
    @FXML
    private ComboBox<String> comboBoxHamburguesas;
    @FXML
    private Button botonAniadirAlPedido;
    @FXML
    private RadioButton panIntegral;
    @FXML
    private ToggleGroup grupo;
    @FXML
    private RadioButton panNormal;
    @FXML
    private RadioButton mollete;
    @FXML
    private CheckBox extraQueso;
    @FXML
    private CheckBox extraBacon;
    @FXML
    private CheckBox extraHuevo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicializo el ObservableList y las columnas de la tabla
        this.lHamburguesas = FXCollections.observableArrayList();
        this.columnaHamburguesa.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.columnaPan.setCellValueFactory(new PropertyValueFactory("tipoPan"));
        this.columnaExtras.setCellValueFactory(new PropertyValueFactory("extras"));
        this.columnaPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        //Añado el Observable list a la tabla
        this.tablaPedido.setItems(lHamburguesas);
        //Inicio Combobox
        iniciarComboBox();
        //Inicio Imageview
        this.imagen.setPreserveRatio(false);
        this.imagen.setImage(new Image(getClass().getResourceAsStream("/com/mycompany/repaso2trimestre/IMG/verata.jpg")));
        //Inicio group radioButton con valor por defecto
        this.panNormal.setSelected(true);
    }

    @FXML
    private void eliminarDelPedido(ActionEvent event) {
        Hamburguesa hamburguesa = tablaPedido.getSelectionModel().getSelectedItem();
        if (hamburguesa != null) {
            lHamburguesas.remove(hamburguesa);
            tablaPedido.refresh();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("¡Debes seleccionar un elemento del pedido!");
            alert.showAndWait();
        }
    }

    @FXML
    private void cobrar(ActionEvent event) {
        try {
            //Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/repaso2trimestre/cuenta.fxml"));
            //Cargo la ventana
            Parent root = loader.load();
            //Controller
            CuentaController controlador = loader.getController();
            controlador.iniciarAtributos(lHamburguesas);
            //Creo el Scene
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
//            ex.printStackTrace();
        }
    }

    private String recuperarTipoPan() {
        String tipoPan;
        if (panNormal.isSelected()) {
            tipoPan = "Pan normal";
        } else {
            if (panIntegral.isSelected()) {
                tipoPan = "Pan integral";
            } else {
                tipoPan = "Mollete";
            }
        }
        return tipoPan;
    }

    private String recuperarExtras() {
        String extras = "";
        if (extraBacon.isSelected()) {
            extras += " Bacon ";
        }
        if (extraQueso.isSelected()) {
            extras += " Queso ";
        }
        if (extraHuevo.isSelected()) {
            extras += " Huevo ";
        }
        return extras;
    }

    @FXML
    private void aniadirAlPedido(ActionEvent event) {
        //Recupero valores hamburguesa
        String nombre = comboBoxHamburguesas.getValue();
        String tipoPan = recuperarTipoPan();
        String extras = recuperarExtras();
        float precio = calcularPrecio();
        //Creo objeto hamburugesa
        Hamburguesa hamburguesa = new Hamburguesa(nombre, tipoPan, extras, precio);
        //Añado la hamburguesa al Observable
        lHamburguesas.add(hamburguesa);
        tablaPedido.refresh();
        //Reseteo campos
        panNormal.setSelected(true);
        extraBacon.setSelected(false);
        extraQueso.setSelected(false);
        extraHuevo.setSelected(false);
    }

    //Método para iniciar el comboBox
    private void iniciarComboBox() {
        this.comboBoxHamburguesas.getItems().add("Verata 10€");
        this.comboBoxHamburguesas.getItems().add("Robusta 10€");
        this.comboBoxHamburguesas.getItems().add("Lotus 11€");
        this.comboBoxHamburguesas.setValue("Verata 10€");
    }
   
    @FXML
    private void seleccionHamburguesa(ActionEvent event) {
        switch (this.comboBoxHamburguesas.getValue()) {
            case "Verata 10€":
                this.imagen.setImage(new Image(getClass().getResourceAsStream("/com/mycompany/repaso2trimestre/IMG/verata.jpg")));
                break;
            case "Robusta 10€":
                this.imagen.setImage(new Image(getClass().getResourceAsStream("/com/mycompany/repaso2trimestre/IMG/robusta.jpg")));
                break;
            case "Lotus 11€":
                this.imagen.setImage(new Image(getClass().getResourceAsStream("/com/mycompany/repaso2trimestre/IMG/lotus.jpg")));
                break;
        }
    }

    //Método para calcular el precio de la hamburguesa
    private float calcularPrecio() {
        float precio = 0.0f;

        //Tipo hamburguesa
        switch (this.comboBoxHamburguesas.getValue()) {
            case "Verata 10€":
            case "Robusta 10€":
                precio += 10;
                break;
            case "Lotus 11€":
                precio += 11;
                break;
        }

        //Extras
        if (extraBacon.isSelected()) {
            precio += 1.5;
        }
        if (extraHuevo.isSelected()) {
            precio += 2;
        }
        if (extraQueso.isSelected()) {
            precio += 1;
        }

        return precio;
    }

}
