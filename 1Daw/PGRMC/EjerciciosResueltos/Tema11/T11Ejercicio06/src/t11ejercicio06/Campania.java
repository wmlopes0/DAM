package t11ejercicio06;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Walter
 */
public class Campania {

    private static Campania campaniaCorona;
    private String nombre;
    private Set<Donacion> donaciones;

    //CONSTRUCTORES (Privados por el patron Singleton)
    private Campania() {
        nombre = "";
        donaciones = new TreeSet<>();
    }

    private Campania(String nombre) {
        this.nombre = nombre;
        this.donaciones = new TreeSet<>();
    }

    private Campania(String nombre, Set<Donacion> donaciones) {
        this.nombre = nombre;
        this.donaciones = donaciones;
    }

    //SETTER Y GETTER
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDonaciones(Set<Donacion> donaciones) {
        this.donaciones = donaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public Set<Donacion> getDonaciones() {
        return donaciones;
    }

    //PATRON DE DISEÑO SINGLETON
    public static Campania getInstance() {
        if (campaniaCorona == null) {
            campaniaCorona = new Campania();
        }
        return campaniaCorona;
    }

    //===============METODOS PROPIOS=================
    public void introducirDonacion(String nombre, float cantidad) {
        donaciones.add(new Donacion(nombre, cantidad));
    }

    public void mostrarDonaciones() {
        for (Donacion donacion : donaciones) {
            donacion.mostrar();
        }
    }

    //Metodo que muestra solo las donaciones cuyo nombre coincide
    public void mostrarDonaciones(String nombre) {
        for (Donacion donacion : donaciones) {
            if (donacion.getNombre().equalsIgnoreCase(nombre)) {
                donacion.mostrar();
            }
        }
    }

    public int numeroDonaciones() {
        return donaciones.size();
    }

    public float totalRecaudacion() {
        float recaudacion = 0;
        for (Donacion donacion : donaciones) {
            recaudacion += donacion.getCantidad();
        }
        return recaudacion;
    }
}
