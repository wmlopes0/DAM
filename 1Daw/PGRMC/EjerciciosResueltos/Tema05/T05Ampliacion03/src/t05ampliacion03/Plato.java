/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t05ampliacion03;

/**
 *
 * @author Walter
 */
public class Plato {

    private String nombre;
    private float precio;
    private int racionesDisponibles;
    private float beneficioNeto;

    public Plato() {
        nombre = "";
        precio = 0.0f;
        racionesDisponibles = 0;
        beneficioNeto = 0.0f;
    }

    public Plato(String nombre, float precio, int racionesDisponibles, float beneficioNeto) {
        this.nombre = nombre;
        this.precio = precio;
        this.racionesDisponibles = racionesDisponibles;
        this.beneficioNeto = beneficioNeto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setRacionesDisponibles(int racionesDisponibles) {
        this.racionesDisponibles = racionesDisponibles;
    }

    public void setBeneficioNeto(float beneficioNeto) {
        this.beneficioNeto = beneficioNeto;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public int getRacionesDisponibles() {
        return racionesDisponibles;
    }

    public float getBeneficioNeto() {
        return beneficioNeto;
    }

}
