package p5_ejercicio3;

import java.util.List;

public class Libro {
    private String ISBN;
    private String titulo;
    private List<String> autores;
    private String editorial;

//    Constructores

    public Libro() {
    }

    public Libro(String ISBN, String titulo, List<String> autores, String editorial) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autores = autores;
        this.editorial = editorial;
    }

//    Getter y Setter
    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getAutores() {
        return autores;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

//    Mostrar
    public void mostrar(){
        System.out.println("===== LIBRO =====");
        System.out.println("ISBN = " + ISBN);
        System.out.println("titulo = " + titulo);
        System.out.println("Autores: ");
        for (String s :
                autores) {
            System.out.print(s+", ");
        }
        System.out.println("\ntitulo = " + editorial);
    }
}
