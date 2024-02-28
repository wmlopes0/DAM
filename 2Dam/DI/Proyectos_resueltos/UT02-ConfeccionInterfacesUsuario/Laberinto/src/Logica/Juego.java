package Logica;

/**
 *
 * @author wmartinl01
 */
public class Juego {

    private String nombre;
    private int tiempo;
    private String nivel;

    //Constructores
    public Juego() {
        nombre = "";
        tiempo = 0;
        nivel = "";
    }

    public Juego(String nombre, int tiempo, String nivel) {
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.nivel = nivel;
    }

    //Getter y Setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    //Método para retornar la información del juego en un String []
    public String[] toArrayString() {
        String[] juego = {nombre, String.valueOf(tiempo) + " segundos", nivel};
        return juego;
    }

}
