package ejercicio13;

import java.io.Serializable;
import java.util.List;

public class Respuesta implements Serializable {

    //Atributos
    private List<Persona> personasApuntadas;

    //Constructor
    public Respuesta(List<Persona> personasApuntadas) {
        this.personasApuntadas = personasApuntadas;
    }

    //Getter y Setter
    public List<Persona> getPersonasApuntadas() {
        return personasApuntadas;
    }

    public void setPersonasApuntadas(List<Persona> personasApuntadas) {
        this.personasApuntadas = personasApuntadas;
    }

    //Métodos propios
    public void mostrarPersonasInscritas() {
        personasApuntadas.forEach(persona -> {
            System.out.println(persona);
        });
    }
}
