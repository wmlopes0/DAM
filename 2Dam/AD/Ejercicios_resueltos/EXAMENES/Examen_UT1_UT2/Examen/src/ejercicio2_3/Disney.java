package ejercicio2_3;

import java.util.List;

public class Disney {
    private List<String> franquicias;
    private ListaPeliculas peliculas;
    private List<Parque> parques;

    //    Constructores
    public Disney() {

    }

    public Disney(List<String> franquicias, ListaPeliculas peliculas, List<Parque> parques) {
        this.franquicias = franquicias;
        this.peliculas = peliculas;
        this.parques = parques;
    }

    //    Getter y Setter
    public List<String> getFranquicias() {
        return franquicias;
    }

    public void setFranquicias(List<String> franquicias) {
        this.franquicias = franquicias;
    }

    public ListaPeliculas getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(ListaPeliculas peliculas) {
        this.peliculas = peliculas;
    }

    public List<Parque> getParques() {
        return parques;
    }

    public void setParques(List<Parque> parques) {
        this.parques = parques;
    }

    //    Mostrar
    public void mostrar() {
        System.out.println("======= D I S N E Y =======");
        System.out.println("FRANQUICIAS:");
        for (String s : franquicias) {
            System.out.println(s);
        }
        peliculas.mostrar();
        System.out.println("PARQUES:");
        for (Parque p : parques) {
            p.mostrar();
        }
    }
}
