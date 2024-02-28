package Ejercicio2Examen_20min;

import java.io.Serializable;
import java.util.Random;

public class Habitacion implements Serializable {

    //Atributos
    private int numHabitacion;
    private int temperatura;

    //Constructor
    public Habitacion(int numHabitacion) {
        this.numHabitacion = numHabitacion;
        this.temperatura = new Random().nextInt(10, 36);
    }

    //Getter y Setter
    public int getNumHabitacion() {
        return numHabitacion;
    }

    public void setNumHabitacion(int numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "numHabitacion=" + numHabitacion +
                ", temperatura=" + temperatura +
                '}';
    }
}
