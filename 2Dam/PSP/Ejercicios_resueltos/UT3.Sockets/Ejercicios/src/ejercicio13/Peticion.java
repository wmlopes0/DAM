package ejercicio13;

import java.io.Serializable;

public class Peticion implements Serializable {

    //Atributos
    private int peticion;
    private Persona persona;

    //Constructor
    public Peticion(int peticion, Persona persona) {
        this.peticion = peticion;
        this.persona = persona;
    }

    //Getter y Setter
    public int getPeticion() {
        return peticion;
    }

    public void setPeticion(int peticion) {
        this.peticion = peticion;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
