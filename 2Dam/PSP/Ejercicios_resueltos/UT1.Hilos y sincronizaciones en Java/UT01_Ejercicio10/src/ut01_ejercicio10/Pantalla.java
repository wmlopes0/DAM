package ut01_ejercicio10;

/**
 *
 * @author wmartinl01
 */
public class Pantalla {

    public synchronized void pintarTabla() {
        System.out.println("Soy el hilo " + Thread.currentThread().getName() + " los primeros m�ltiplos son:");
        for (int i = 1; i < 6; i++) {
            System.out.println("M�ltiplo[" + i + "]: " + Integer.parseInt(Thread.currentThread().getName()) * i);
        }
        System.out.println("Fin de los m�ltiplos de " + Thread.currentThread().getName());
    }
}
