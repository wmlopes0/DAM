package t02_repaso_examen1;

import java.util.concurrent.Semaphore;

public class Carretera {

    //Variables y semáforos
    private Semaphore mutex;
    private Semaphore pasarDerecha;
    private Semaphore pasarIzquierda;
    private boolean pasandoDerecha;
    private boolean pasandoIzquierda;
    private int cochesEsperandoIzquierda;
    private int cochesEsperandoDerecha;
    private int cochesActuales;
    private int maxCoches;

    public Carretera(int maxCoches) {
        this.maxCoches = maxCoches;
        pasandoDerecha = false;
        pasandoIzquierda = false;
        cochesActuales = 0;
        mutex = new Semaphore(1, true);
        pasarDerecha = new Semaphore(0, true);
        pasarIzquierda = new Semaphore(0, true);
    }

    public void pasarDerecha(Coche coche) throws InterruptedException {
        mutex.acquire();
        if (cochesActuales == maxCoches || pasandoIzquierda == true) {
            cochesEsperandoDerecha++;
            mutex.release();
            pasarDerecha.acquire();//Bloqueo el hilo
            mutex.acquire();
        }
        pasandoDerecha = true;
        cochesActuales++;
        System.out.println("Pasa el " + coche.getNombre() + " a la Derecha.");
        if (cochesActuales == maxCoches) {
            System.out.println("Salió el útlimo coche de la derecha.");
            pasandoDerecha = false;
            pasarIzquierda.release(cochesEsperandoIzquierda);
            cochesEsperandoIzquierda = 0;
            cochesActuales = 0;
        }
        mutex.release();
    }

    public void pasarIzquierda(Coche coche) throws InterruptedException {
        mutex.acquire();
        if (cochesActuales == maxCoches || pasandoDerecha == true) {
            cochesEsperandoIzquierda++;
            mutex.release();
            pasarIzquierda.acquire();//Bloqueo el hilo
            mutex.acquire();
        }
        pasandoIzquierda = true;
        cochesActuales++;
        System.out.println("Pasa el " + coche.getNombre() + " a la Izquierda.");
        if (cochesActuales == maxCoches) {
            System.out.println("Salió el útlimo coche de la izquierda.");
            pasandoIzquierda = false;
            pasarDerecha.release(cochesEsperandoDerecha);
            cochesEsperandoDerecha = 0;
            cochesActuales = 0;
        }
        mutex.release();
    }
}
