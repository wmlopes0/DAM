package t02_ejercicio10_walter;

import java.util.Random;

public class TuberiaMeterAgua extends Thread {

    //Atributos
    private String nombre;
    private float litros;
    private Deposito deposito;

    //Constructor
    public TuberiaMeterAgua(String nombre, Deposito deposito) {
        this.nombre = nombre;
        this.deposito = deposito;
        litros = new Random().nextFloat(1, 150);
    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    public float getLitros() {
        return litros;
    }

    @Override
    public void run() {
        try {
            while (true) {
                deposito.mete_agua(this);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
    }
}
