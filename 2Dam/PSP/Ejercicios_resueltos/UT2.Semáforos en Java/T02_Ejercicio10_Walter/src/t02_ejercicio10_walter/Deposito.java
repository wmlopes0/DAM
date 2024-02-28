package t02_ejercicio10_walter;

import java.util.concurrent.Semaphore;

public class Deposito {

    //Atributos, variables y Semáforos
    private int capacidadTotal;
    private float aguaActual;
    private Semaphore mutex;
    private Semaphore meterAgua;
    private Semaphore sacarAgua;

    //Constructor
    public Deposito(int capacidad) {
        capacidadTotal = capacidad;
        aguaActual = 0.0f;
        mutex = new Semaphore(1, true);
        meterAgua = new Semaphore(0, true);
        sacarAgua = new Semaphore(0, true);
    }

    public void mete_agua(TuberiaMeterAgua tuberia) throws InterruptedException {
        //Mensaje intención meter agua
        mutex.acquire();
        System.out.println(tuberia.getNombre() + " METER AGUA: agua actual=" + aguaActual + " litros, a meter=" + tuberia.getLitros());
        //Compruebo si puede meter el agua
        while ((aguaActual + tuberia.getLitros()) > capacidadTotal) {
            System.out.println("\t\t" + tuberia.getNombre() + " bloqueado hasta que se vacíe un poco el depósito");
            mutex.release();
            meterAgua.acquire();//Bloqueo el hilo hasta que vuelvan a vaciar
            mutex.acquire();
        }
        //Meto el agua y muestro mensaje
        aguaActual += tuberia.getLitros();
        System.out.println("\t\t" + tuberia.getNombre() + " metiendo " + tuberia.getLitros() + " litros de agua: agua actual=" + aguaActual);
        mutex.release();
        sacarAgua.release();//Libero un permiso de sacar agua para que la tuberia que le toque vuelva a comprobar si hay litros suficientes para sacar
    }

    public void saca_agua(TuberiaSacarAgua tuberia) throws InterruptedException {
        //Mensaje intención sacar agua
        mutex.acquire();
        System.out.println(tuberia.getNombre() + " SACAR AGUA: agua actual=" + aguaActual + " litros, a sacar=" + tuberia.getLitros());
        //Compruebo si puede meter el agua
        while ((aguaActual - tuberia.getLitros()) < 0) {
            System.out.println("\t\t" + tuberia.getNombre() + " bloqueado hasta que se haya agua en el depósito");
            mutex.release();
            sacarAgua.acquire();//Bloqueo el hilo hasta que vuelvan a llenar
            mutex.acquire();
        }
        //Saco el agua y muestro mensaje
        aguaActual -= tuberia.getLitros();
        System.out.println("\t\t" + tuberia.getNombre() + " sacando " + tuberia.getLitros() + " litros de agua, quedan=" + aguaActual);
        mutex.release();
        meterAgua.release();//Libero un permiso de meter agua para que la tuberia que le toque vuelva a comprobar si puede meter los litros
    }
}
