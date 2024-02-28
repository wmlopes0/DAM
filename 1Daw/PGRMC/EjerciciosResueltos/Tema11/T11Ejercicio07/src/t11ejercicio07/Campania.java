/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t11ejercicio07;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Walter
 */
public class Campania {

    private String nombre;
    private Set<Donacion> donaciones;

    //CONSTRUCTORES
    public Campania() {
        nombre = "";
        donaciones = new TreeSet<>();
    }

    public Campania(String nombre) {
        this.nombre = nombre;
        this.donaciones = new TreeSet<>();
    }

    public Campania(String nombre, Set<Donacion> donaciones) {
        this.nombre = nombre;
        this.donaciones = donaciones;
    }

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

    //===============METODOS PROPIOS=================
    public void introducirDonacion(String nombre, float cantidad) {
        donaciones.add(new Donacion(nombre, cantidad));
    }

    public void mostrarCampania() {
        System.out.println("*******************");
        System.out.println("******CAMPAÑA******");
        System.out.println("*******************");
        System.out.println("NOMBRE: "+nombre);
        mostrarDonaciones();
    }

    private void mostrarDonaciones() {
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
