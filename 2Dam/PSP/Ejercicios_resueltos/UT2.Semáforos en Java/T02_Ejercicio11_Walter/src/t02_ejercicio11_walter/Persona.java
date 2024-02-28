package t02_ejercicio11_walter;

import java.util.Random;

public class Persona extends Thread {

    //Atributos
    private String nombre;
    private float peso;
    private Ascensor ascensor;

    //Constructor
    public Persona(String nombre, Ascensor ascensor) {
        this.nombre = nombre;
        peso = new Random().nextFloat(40, 100);
        peso = Math.round(peso * 100) / 100;
        this.ascensor = ascensor;
    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    public float getPeso() {
        return peso;
    }

    @Override
    public void run() {
        try {
            ascensor.sube_persona(this);
            Thread.sleep(1500);//Tiempo en el ascensor
            ascensor.baja_persona(this);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
    }
}
