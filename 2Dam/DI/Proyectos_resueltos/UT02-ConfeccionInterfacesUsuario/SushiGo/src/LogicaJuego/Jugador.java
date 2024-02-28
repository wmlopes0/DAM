package LogicaJuego;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wmartinl01
 */
//Esta clase representa un jugador del juego, puede ser el USER o CPU
public class Jugador {

    //Atributos
    private String nombre;
    private List<Carta> mano;//Cartas que va seleccionando el jugador
    private List<Carta> cartasRepartidas;//Cartas que le son repartidas
    private int puntuacion;
    private int puddings;//Número de puddings, este atributo se usará para calcular la puntuación final
    private String tipo;//USER O CPU

    //Constructores
    public Jugador(String nombre, List<Carta> cartasVisibles, int puntuacion, int puddings, String tipo) {
        this.nombre = nombre;
        this.cartasRepartidas = cartasVisibles;
        this.mano = new ArrayList<>();
        this.puntuacion = puntuacion;
        this.puddings = puddings;
        this.tipo = tipo;
    }

    //Getter y Setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Carta> getMano() {
        return mano;
    }

    public void setMano(List<Carta> mano) {
        this.mano = mano;
    }

    public List<Carta> getCartasVisibles() {
        return cartasRepartidas;
    }

    public void setCartasVisibles(List<Carta> cartasVisibles) {
        this.cartasRepartidas = cartasVisibles;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getPuddings() {
        return puddings;
    }

    public void setPuddings(int puddings) {
        this.puddings = puddings;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    //MÉTODOS PROPIOS
    //Retorna true si el jugador es tipo CPU
    public boolean esCPU() {
        return tipo.equalsIgnoreCase("CPU");
    }

    //Añadir pudding
    public void aniadirPudding() {
        puddings++;
    }
}
