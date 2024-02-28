package Logica;

import java.time.LocalDate;

/**
 *
 * @author wmartinl01
 */
public class Juego {

    //Variable donde se almacenará el record
    public static Juego mejorJuego = new Juego();

    private String nombre;
    private int tiempo;
    private LocalDate fecha;

    //Constructores
    public Juego() {
        this.nombre = "";
        this.tiempo = 0;
        this.fecha = LocalDate.now();
    }

    public Juego(String nombre, int tiempo, LocalDate fecha) {
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.fecha = fecha;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    //Método para retornar la información del juego en un String []
    public String[] toArrayString() {
        String[] juego = {nombre, String.valueOf(tiempo)+" segundos", dateToString(fecha)};
        return juego;
    }

    //Método que formatea la fecha
    private String dateToString(LocalDate fecha) {
        return String.valueOf(fecha.getDayOfMonth()) + "/" + String.valueOf(fecha.getMonthValue()) + "/" + String.valueOf(fecha.getYear());
    }

}
