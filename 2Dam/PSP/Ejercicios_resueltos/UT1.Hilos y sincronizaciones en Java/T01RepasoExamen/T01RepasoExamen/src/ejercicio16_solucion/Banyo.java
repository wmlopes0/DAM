package ejercicio16_solucion;

import java.util.Random;

public class Banyo {
    final int MAX_PERSONAS = 3;
    int numPersonas;

    public Banyo() {
        numPersonas = 0; // Inicialmente no hay nadie
    }

    // El método no puede ser completamente sincronizado, porque quiero que entren
    // varias personas por el baño a la vez.
    public void entrabanio(String nombre) {
        // Compruebo que puedo entrar
        synchronized (this) {
            while (numPersonas == MAX_PERSONAS) {
                System.out.println("¡" + nombre + " espera porque el baño está lleno!");
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
            numPersonas++; // modifico numPersonas todavía en exclusión mútua
            System.out.println("Entra " + nombre + "---------- Hay " + numPersonas);
        }

        // Hace algo en el baño un tiempo aleatorio:
        Random rd = new Random();
        int tiempo = rd.nextInt(20) + 1;
        System.out.println("\t" + nombre + " permanece en el baño " + tiempo + " milisegundos.");
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // A la salida, tiene que volver a estar sincronizado, porque toco la variable
        // común numPersonas y hay que hacer un notifyAll
        synchronized (this) {
            numPersonas--;
            System.out.println("\t\tSale " + nombre + "---------- Hay " + numPersonas);
            notifyAll();
        }

    }
}
