package p3_ejercicio3;

import p1_ejercicio3.Libro;

import java.util.List;

public class ListaLibros {
    private List<Libro> libros;

    //    Constructores
    public ListaLibros() {
    }

    public ListaLibros(List<Libro> libros) {
        this.libros = libros;
    }

    //    Getter y Setter
    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    //    Add
    public void add(Libro libro) {
        libros.add(libro);
    }

    //    Mostrar
    public void mostrar() {
        for (Libro libro :
                libros) {
            libro.mostrar();
        }
    }
}
