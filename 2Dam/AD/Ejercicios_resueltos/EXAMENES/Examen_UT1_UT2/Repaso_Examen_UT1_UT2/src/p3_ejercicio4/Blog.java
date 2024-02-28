package p3_ejercicio4;

import java.util.List;

public class Blog {
    private String autor;
    private List<Entrada> entradas;

//    Constructores
    public Blog() {
    }

    public Blog(String autor, List<Entrada> entradas) {
        this.autor = autor;
        this.entradas = entradas;
    }

//    Getter y Setter

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public List<Entrada> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<Entrada> entradas) {
        this.entradas = entradas;
    }

    //    Mostrar
    public void mostrar() {
        System.out.println("============ B L O G ============");
        System.out.println("autor = " + autor);
        System.out.println("-------- ENTRADAS --------");
        for (Entrada entrada :
                entradas) {
            entrada.mostrar();
        }
    }
}
