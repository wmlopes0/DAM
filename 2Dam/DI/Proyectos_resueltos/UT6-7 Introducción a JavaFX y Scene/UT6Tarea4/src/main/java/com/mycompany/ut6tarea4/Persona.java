package com.mycompany.ut6tarea4;

/**
 *
 * @author Walter
 */
public class Persona {
    
    //Atributos
    private String nombre;
    private String apellidos;
    private String edad;

    //Constructores
    public Persona() {
    }

    public Persona(String nombre, String apellidos, String edad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }
    
    //Getter y setter

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }
    
    
    
}
