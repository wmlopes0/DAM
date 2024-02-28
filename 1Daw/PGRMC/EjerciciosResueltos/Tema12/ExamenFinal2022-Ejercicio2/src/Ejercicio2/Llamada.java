package Ejercicio2;

import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Llamada {

    private String nombrePaciente;
    private LocalDate fechaLlamada;

    //CONSTRUCTORES
    public Llamada() {
        nombrePaciente = "";
        fechaLlamada = LocalDate.now();
    }

    public Llamada(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
        this.fechaLlamada = LocalDate.now();
    }

    //SETTER Y GETTER
    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public void setFechaLlamada(LocalDate fechaLlamada) {
        this.fechaLlamada = fechaLlamada;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public LocalDate getFechaLlamada() {
        return fechaLlamada;
    }

    //METODOS PROPIOS
   
}
