/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ut01_ejercicio9;

/**
 *
 * @author Walter
 */
public class Coche extends Thread {

    public static final Object CERROJO = new Object(); // Objeto de bloqueo para el puente
    private int idCoche;

    public Coche() {
    }

    public Coche(int idCoche) {
        this.idCoche = idCoche;
    }

    @Override
    public void run() {
        try {
            // Primeros 5 kms
            for (int i = 1; i <= 5; i++) {
                System.out.println("El coche-" + idCoche + " va por el km" + i + " de la carretera");
                Thread.sleep(1000); // Simulando tiempo que tarda en avanzar 1 km
            }
            // Puente (4 kms)
            synchronized (CERROJO) { // Bloque sincronizado
                for (int i = 6; i <= 9; i++) {
                    if (i == 6) {
                        System.out.println("\t***El coche-" + idCoche + " ha COMENZADO a cruzar el puente");
                    }
                    System.out.println("\t***El coche-" + idCoche + " está atravesando el km " + i + " del puente");
                    if (i == 9) {
                        System.out.println("\t***El coche-" + idCoche + " ha TERMINADO de cruzar el puente");
                    }
                    Thread.sleep(1000);
                }
            }

            // Últimos 3 kms
            for (int i = 10; i <= 12; i++) {
                if (i == 12) {
                    System.out.println("El coche-" + idCoche + " va por el km" + i + " de la carretera");
                    System.out.println("*****************************************************************");
                    System.out.println("* El coche-" + idCoche + " ha llegado a Malpartida de Plasencia *");
                    System.out.println("*****************************************************************");
                } else {
                    System.out.println("El coche-" + idCoche + " va por el km" + i + " de la carretera");
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
