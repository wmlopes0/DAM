package pRepaso_ejercicio7;

import java.util.List;

public class Esdla {
    private String autor;
    private CiudadesWrapper ciudades;
    private ListaPersonajes personajes;

    // Constructor

    public Esdla() {
    }

    public Esdla(String autor, CiudadesWrapper ciudades, ListaPersonajes personajes) {
        super();
        this.autor = autor;
        this.ciudades = ciudades;
        this.personajes = personajes;
    }

    // Getter y Setter
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public CiudadesWrapper getCiudades() {
        return ciudades;
    }

    public void setCiudades(CiudadesWrapper ciudades) {
        this.ciudades = ciudades;
    }

    public ListaPersonajes getPersonajes() {
        return personajes;
    }

    public void setPersonajes(ListaPersonajes personajes) {
        this.personajes = personajes;
    }

    public void mostrar() {
        System.out.println("AUTOR: " + autor);
        mostrarCiudades();
        personajes.mostrar();
    }

    private void mostrarCiudades() {
        for (Ciudad ciudad : ciudades.getCiudad()) {
            ciudad.mostrar();
        }
    }
}
