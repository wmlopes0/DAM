package tablacoches;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Walter
 */
public class LogicaNegocio {

    private List<Coche> listaCoches = new ArrayList<>();

    //Constructor
    public LogicaNegocio() {
        //Instanciamos coches
        Coche coche1 = new Coche("Toyota Corolla", "Blanco", 23000.00f, "10-02-2021");
        Coche coche2 = new Coche("Honda Civic", "Negro", 22000.00f, "05-05-2022");
        Coche coche3 = new Coche("Tesla Model 3", "Rojo", 38000.00f, "15-06-2022");
        Coche coche4 = new Coche("Ford Focus", "Azul", 21000.00f, "28-08-2020");
        Coche coche5 = new Coche("BMW Serie 3", "Gris", 41000.00f, "01-01-2021");
        Coche coche6 = new Coche("Volkswagen Golf", "Verde", 24000.00f, "23-03-2021");
        Coche coche7 = new Coche("Chevrolet Cruze", "Amarillo", 20000.00f, "12-04-2022");
        Coche coche8 = new Coche("Mazda 3", "Morado", 22500.00f, "20-07-2021");

        //Añadimos a la lista
        listaCoches.add(coche1);
        listaCoches.add(coche2);
        listaCoches.add(coche3);
        listaCoches.add(coche4);
        listaCoches.add(coche5);
        listaCoches.add(coche6);
        listaCoches.add(coche7);
        listaCoches.add(coche8);
    }

    //Getter y Setter
    public List<Coche> getListaCoches() {
        return listaCoches;
    }

    public void setListaCoches(List<Coche> listaCoches) {
        this.listaCoches = listaCoches;
    }
}
