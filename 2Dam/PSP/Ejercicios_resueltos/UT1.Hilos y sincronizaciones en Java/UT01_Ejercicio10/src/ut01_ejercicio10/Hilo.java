package ut01_ejercicio10;

/**
 *
 * @author wmartinl01
 */
public class Hilo extends Thread {

    Pantalla pantalla;

    public Hilo(Pantalla _pantalla) {
        this.pantalla = _pantalla;
    }

    @Override
    public void run() {
        //Sincronizamos la ejecucion sobre el objeto:
        pantalla.pintarTabla();
    }
}
