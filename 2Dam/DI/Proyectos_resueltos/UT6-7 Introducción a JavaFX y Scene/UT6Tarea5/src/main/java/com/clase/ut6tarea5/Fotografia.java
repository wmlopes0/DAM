package com.clase.ut6tarea5;

/**
 *
 * @author wmartinl01
 */
public class Fotografia {

    //Atributos
    private String nombre;
    private String url;

    //Constructores
    public Fotografia() {
    }

    public Fotografia(String nombre, String url) {
        this.nombre = nombre;
        this.url = url;
    }

    //Getter y Setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
