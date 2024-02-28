package t11ejercicio09;

import java.time.LocalDate;
import java.util.Arrays;

/**
 *
 * @author Walter
 */
public class Sorteo {

    private LocalDate fecha;
    private int[] numerosGanadores;

    public Sorteo() {
        fecha = LocalDate.now();
        numerosGanadores = new int[4];
        rellenarNumerosGanadores();
    }

    public Sorteo(LocalDate fecha) {
        this.fecha = fecha;
        numerosGanadores = new int[4];
        rellenarNumerosGanadores();
    }

    public Sorteo(LocalDate fecha, int[] numerosGanadores) {
        this.fecha = fecha;
        this.numerosGanadores = numerosGanadores;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setNumerosGanadores(int[] numerosGanadores) {
        this.numerosGanadores = numerosGanadores;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int[] getNumerosGanadores() {
        return numerosGanadores;
    }

    //METODOS PROPIOS
    private void rellenarNumerosGanadores() {
        for (int i = 0; i < numerosGanadores.length; i++) {
            numerosGanadores[i] = (int) Math.round(Math.random() * 99 + 1);
        }
    }
    
    public void mostrar(){
        System.out.println("******************");
        System.out.println("******SORTEO******");
        System.out.println("******************");
        System.out.println("FECHA: "+fecha);
        System.out.println("NUMEROS GANADORES: "+Arrays.toString(numerosGanadores));
    }
}
