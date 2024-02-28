package t02_Ejercicio6_Walter;

import java.io.File;
import java.util.concurrent.Semaphore;

public class Main {

    //Variables globales
    public final static File FICHERO = new File("buffer.txt");

    public static void main(String[] args) {
        Semaphore vacio = new Semaphore(0);//semáforo sin permisos, el primero que llame a acquire () se bloqueará
        Semaphore lleno = new Semaphore(5); //semáforo que se bloqueará cuando el buffer esté lleno
        Semaphore mutex = new Semaphore(1); //semáforo para la sección crítica.
        
        //Consumidores
        Consumidor[] consumidores = new Consumidor[2];

        //Productores
        Productor[] productores = new Productor[2];

        //Instancio e inicio consumidores
        for (int i = 0; i < consumidores.length; i++) {
            consumidores[i] = new Consumidor("C" + (i + 1), vacio, lleno, mutex);
            consumidores[i].start();
        }

        //Instancio e inicio productores
        for (int i = 0; i < productores.length; i++) {
            productores[i] = new Productor("P" + (i + 1), vacio, lleno, mutex);
            productores[i].start();
        }
    }
}

