package ejercicio2_3;

import java.util.List;

public class ListaPeliculas {
    private List<String> clasicos;
    private List<String> pixar;

//    Constructores

    public ListaPeliculas() {

    }

    public ListaPeliculas(List<String> clasicos, List<String> pixar) {
        this.clasicos = clasicos;
        this.pixar = pixar;
    }

//    Getter y Setter

    public List<String> getClasicos() {
        return clasicos;
    }

    public void setClasicos(List<String> clasicos) {
        this.clasicos = clasicos;
    }

    public List<String> getPixar() {
        return pixar;
    }

    public void setPixar(List<String> pixar) {
        this.pixar = pixar;
    }

    //    Mostrar
    public void mostrar() {
        System.out.println("----- LISTA PELICULAS -----");
        System.out.println("CLASICOS:");
        for (String s : clasicos) {
            System.out.println(s);
        }
        System.out.println("PIXAR:");
        for (String s : pixar) {
            System.out.println(s);
        }
    }
}
