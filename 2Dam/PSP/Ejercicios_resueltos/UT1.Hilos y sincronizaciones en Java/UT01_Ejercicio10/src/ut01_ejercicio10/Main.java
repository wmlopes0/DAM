package ut01_ejercicio10;

/**
 *
 * @author wmartinl01
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Pantalla pantalla = new Pantalla();
        Hilo hilo1 = new Hilo(pantalla);
        hilo1.setName(String.valueOf(1));
        
        Hilo hilo2 = new Hilo(pantalla);
        hilo2.setName(String.valueOf(2));
        
        Hilo hilo3 = new Hilo(pantalla);
        hilo3.setName(String.valueOf(3));

        hilo1.start();
        hilo1.join();
        hilo2.start();
        hilo2.join();
        hilo3.start();
        hilo3.join();

    }
}
