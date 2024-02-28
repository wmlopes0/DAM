package Ejercicio2;

import java.io.Serializable;
import java.util.Random;

public class Habitacion implements Serializable {

    //Atributos
    private int numHabitacion;
    private float temperatura;

    //Constructor
    public Habitacion() {
        this.numHabitacion = 1;
        //Genero aleatoriamente la temperatura
        generarTemperatura();
    }

    //Getter y Setter
    public int getNumHabitacion() {
        return numHabitacion;
    }

    public void setNumHabitacion(int numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    //Métodos propios
    //Método que genera la temperatura de la habitación de manera aleatoria entre 10 y 40º
    public void generarTemperatura() {
        this.temperatura = new Random().nextFloat(10, 41);
    }

    @Override
    public String toString() {
        return "Habitacion[" +
                "numHabitacion=" + numHabitacion +
                ", temperatura=" + temperatura +
                ']';
    }
}
