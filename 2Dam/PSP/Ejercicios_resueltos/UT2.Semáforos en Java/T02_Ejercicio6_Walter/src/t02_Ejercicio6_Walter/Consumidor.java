package t02_Ejercicio6_Walter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import static t02_Ejercicio6_Walter.Main.FICHERO;

public class Consumidor extends Thread {
    String name;
    Semaphore vacio;
    Semaphore lleno;
    Semaphore mutex;

    public Consumidor(String name, Semaphore vacio, Semaphore lleno, Semaphore mutex) {
        this.name = name;
        this.vacio = vacio;
        this.lleno = lleno;
        this.mutex = mutex;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                vacio.acquire();
                mutex.acquire();

                String primeraLinea = leerYBorrarPrimeraLinea();

                if (primeraLinea != null) {
                    System.out.println("Consumidor " + name + "==> " + primeraLinea);
                } else {
                    System.out.println("Consumidor " + name + "==> No hay nada para consumir");
                }

                mutex.release();
                lleno.release();
                Thread.yield();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String leerYBorrarPrimeraLinea() throws IOException {
        // Abro flujos de lectura
        BufferedReader br = new BufferedReader(new FileReader(FICHERO));
        // Leo la primera línea
        String primeraLinea = br.readLine();

        // Lista para almacenar las líneas restantes
        List<String> lineasRestantes = new ArrayList<>();
        String linea;
        while ((linea = br.readLine()) != null) {
            lineasRestantes.add(linea);
        }

        br.close(); // Cierro el flujo de lectura

        // Reescribir el archivo sin la primera línea
        PrintWriter pw = new PrintWriter(new FileWriter(FICHERO));//Abro flujos de escritura sin respetar lo que había escrito
        for (String lineaRestante : lineasRestantes) {
            pw.println(lineaRestante);
        }

        pw.close(); // Cierro el flujo de escritura

        return primeraLinea; // Devuelve la primera línea leída
    }
}
