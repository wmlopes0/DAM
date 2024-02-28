package t02_ejercicio13_walter;

import java.util.*;
import java.util.concurrent.Semaphore;

public class Platanera {

    //Variables y semáforos
    private List<String> cintaProduccion;
    private int consumidoresEsperando;
    private Semaphore mutex;
    private Semaphore consumidorEsperando;


    //Pruductor
    public Platanera() {
        cintaProduccion = new ArrayList<>();
        consumidoresEsperando = 0;
        mutex = new Semaphore(1, true);
        consumidorEsperando = new Semaphore(0, true);
    }

    //Getter cintaProcuccion
    public List<String> getCintaProduccion() {
        return cintaProduccion;
    }

    public void producir(Productor productor, String platanos) throws InterruptedException {
        mutex.acquire();
        System.out.println(productor.getNombre() + ", etiquetando manojo: platanos-" + platanos);
        cintaProduccion.add(0, platanos);
        mostrarCintaProduccion();
        mutex.release();
    }

    public void consumir(Consumidor consumidor) throws InterruptedException {
        mutex.acquire();
        while (!cintaProduccion.get(cintaProduccion.size() - 1).equalsIgnoreCase(consumidor.getCalidad())) {
            consumidoresEsperando++;
            mutex.release();
            consumidorEsperando.acquire();//Hago esperar al consumidor hasta que pueda coger su calidad
            mutex.acquire();
        }
        System.out.println(consumidor.getNombre() + " ==> recoge platanos");
        cintaProduccion.remove(cintaProduccion.size() - 1);//Consumo
        mostrarCintaProduccion();//Muestro
        consumidorEsperando.release(consumidoresEsperando);//Suelto permiso para avisar al otro
        consumidoresEsperando = 0;//Reseteo contador
        mutex.release();
    }

    public void mostrarCintaProduccion() {
        System.out.println("-------------------------------------------------------------------------------");
        cintaProduccion.forEach(platano -> {
            System.out.print("| platanos-" + platano + " ");
        });
        System.out.println("\n-------------------------------------------------------------------------------");
    }
}
