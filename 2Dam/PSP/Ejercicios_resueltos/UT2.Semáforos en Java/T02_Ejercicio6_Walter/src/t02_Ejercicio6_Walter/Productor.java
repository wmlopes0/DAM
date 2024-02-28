package t02_Ejercicio6_Walter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Semaphore;

import static t02_Ejercicio6_Walter.Main.FICHERO;

public class Productor extends Thread {
    String name;
    Semaphore vacio; //cola para esperar cuando está vacío
    Semaphore lleno; //cola para esperar cuando está lleno
    Semaphore mutex;

    public Productor(String name, Semaphore vacio, Semaphore lleno, Semaphore mutex) {
        this.name = name;
        this.vacio = vacio;
        this.lleno = lleno;
        this.mutex = mutex;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                lleno.acquire();
                //Manejo con el mutex que la escritura se haga en exlusión mutua
                mutex.acquire();

                String produce = name + "-" + i;

                // Escritura en fichero
                //Abrimos flujos
                PrintWriter pw = new PrintWriter(new FileWriter(FICHERO,true));//Mantenemos lo escrito previamente
                //Escribimos
                pw.println(produce);
                //Muestro por pantalla también lo que produce
                System.out.println("Productor " + name + " produce " + produce);

                //Cerramos flujos
                pw.close();
                mutex.release();
                vacio.release();
            } catch (InterruptedException | IOException e) {
//                e.printStackTrace();
            }
        }
    }
}
