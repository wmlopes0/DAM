package com.mycompany.proyectofinal.modelos;

/**
 *
 * @author Walter
 */
public class TipoClase {

    //Atributos
    private int tipo_clase_id;
    private String nombre;
    private int cupos_disponibles;
    private String fotografia;

    //Constructores
    public TipoClase() {
    }

    public TipoClase(int tipo_clase_id, String nombre, int cupos_disponibles, String fotografia) {
        this.tipo_clase_id = tipo_clase_id;
        this.nombre = nombre;
        this.cupos_disponibles = cupos_disponibles;
        this.fotografia = fotografia;
    }

    public TipoClase(String nombre, int cupos_disponibles, String fotografia) {
        this.nombre = nombre;
        this.cupos_disponibles = cupos_disponibles;
        this.fotografia = fotografia;
    }

    //Getter y Setter
    public int getTipo_clase_id() {
        return tipo_clase_id;
    }

    public void setTipo_clase_id(int tipo_clase_id) {
        this.tipo_clase_id = tipo_clase_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCupos_disponibles() {
        return cupos_disponibles;
    }

    public void setCupos_disponibles(int cupos_disponibles) {
        this.cupos_disponibles = cupos_disponibles;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

}
