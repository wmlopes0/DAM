package p1_ejercicio3;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.SQLOutput;


public class Libro implements Serializable {

    private String ISBN;
    private String titulo;
    private String autor;
    private String editorial;

    //Constructor

    public Libro() {

    }

    public Libro(String ISBN, String titulo, String autor, String editorial) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
    }

    //Getter y Setter
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void mostrar(){
        System.out.println("---- L I B R O ----");
        System.out.println("ISBN = " + ISBN);
        System.out.println("titulo = " + titulo);
        System.out.println("autor = " + autor);
        System.out.println("editorial = " + editorial);
    }
}
