package t02_ejercicio13_walter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Productor extends Thread {

    //Atributos
    private String nombre;
    private Platanera platanera;
    private List<String> calidades;


    //Constructor
    public Productor(String nombre, Platanera platanera) {
        this.nombre = nombre;
        this.platanera = platanera;
        this.calidades = new ArrayList<>();
        calidades.add("A");
        calidades.add("B");
    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        //Produzco platanos de una calidad aleatoria cada vez
        try {
            while (true) {
                Collections.shuffle(calidades);
                platanera.producir(this, calidades.get(0));
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
//                throw new RuntimeException(e);
        }
    }
}
