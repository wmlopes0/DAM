
package com.mycompany.repaso2trimestre;

/**
 *
 * @author Walter
 */
public class Hamburguesa {
    
    //Atributos
    private String nombre;
    private String tipoPan;
    private String extras;
    private float precio;
    
    //Constructor
    public Hamburguesa(String nombre, String tipoPan, String extras, float precio) {
        this.nombre = nombre;
        this.tipoPan = tipoPan;
        this.extras = extras;
        this.precio = precio;
    }
    
    //Getter y Setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoPan() {
        return tipoPan;
    }

    public void setTipoPan(String tipoPan) {
        this.tipoPan = tipoPan;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    
}
