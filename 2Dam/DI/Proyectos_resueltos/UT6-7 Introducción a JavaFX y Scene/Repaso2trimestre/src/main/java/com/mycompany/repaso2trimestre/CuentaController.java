package com.mycompany.repaso2trimestre;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Walter
 */
public class CuentaController implements Initializable {

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
    private Label precioTotal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicializo el ObservableList y las columnas de la tabla
        this.columnaHamburguesa.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.columnaPan.setCellValueFactory(new PropertyValueFactory("tipoPan"));
        this.columnaExtras.setCellValueFactory(new PropertyValueFactory("extras"));
        this.columnaPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
    }

    public void iniciarAtributos(ObservableList<Hamburguesa> lHamburguesas) {
        this.lHamburguesas = lHamburguesas;
        //Añado el Observable list a la tabla
        this.tablaPedido.setItems(lHamburguesas);
        //Total
        calcularTotal();
    }

    public void calcularTotal() {
        float total = 0.0f;
        for (Hamburguesa hamburguesa : lHamburguesas) {
            total += hamburguesa.getPrecio();
        }
        //Seteo el label
        precioTotal.setText(String.valueOf(total) + "€");
    }
}
