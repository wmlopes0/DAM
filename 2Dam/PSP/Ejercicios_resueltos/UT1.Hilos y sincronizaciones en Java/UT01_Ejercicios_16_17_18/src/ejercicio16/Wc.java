package ejercicio16;

import java.util.Random;

public class Wc {
    private int aforo = 0;
    private boolean hueco = true;

    //    Constructor
    public Wc() {
    }

    //Método sincronizado para entrar
    public synchronized void entrar(Persona persona) throws InterruptedException {
        while (!hueco) {
            System.out.println("¡" + persona.getNombre() + " espera porque el baño está lleno!");
            wait();
        }
        aforo++;
        actualizarHueco();
        System.out.println("Entra " + persona.getNombre() + "---------- Hay " + aforo);
        notifyAll();
    }

    //Método sincronizado para salir
    public synchronized void salir(Persona persona) {
        aforo--;
        actualizarHueco();
        System.out.println("        Sale " + persona.getNombre() + "---------- Hay " + aforo);
        notifyAll();
    }

    //Método que actualiza la variable booleana hueco
    public void actualizarHueco() {
        if (aforo == 3) {
            hueco = false;
        } else {
            hueco = true;
        }
    }
}
