package metodosPrincipalesThread;

public class Ejemplo5_Priority_Volatile {
//    EXPLICACIÓN:
    /*
    *El programa que proporcionaste intenta mostrar la diferencia en la ejecución de hilos con
    *diferentes prioridades. Sin embargo, hay un problema potencial que puede hacer que los
    *hilos no se detengan como esperas.
    *
    *El problema radica en la visibilidad de los cambios realizados en la variable stopHilo por
    *diferentes hilos. Aunque el hilo principal (el método main) cambia el valor de stopHilo a
    *true para detener los hilos, los hilos de trabajo que están en el bucle while pueden no
    *ver este cambio inmediatamente o nunca debido a optimizaciones y al sistema de caché del
    *procesador.
    *
    *Para solucionar este problema, puedes declarar la variable stopHilo como volatile. La
    *palabra clave volatile en Java garantiza que una variable sea leída directamente desde la
    *memoria principal y escrita directamente en la memoria principal. Esto asegura que los
    *cambios realizados por un hilo sean visibles para todos los demás hilos de inmediato.
    * */
    public static void main(String[] args) throws InterruptedException {
//      Creo hilos
        Hilo hiloPrioridadBaja = new Hilo();
        Hilo hiloPrioridadMedia = new Hilo();
        Hilo hiloPrioridadAlta = new Hilo();
//      Establezco prioridades
        hiloPrioridadBaja.setPriority(Thread.MIN_PRIORITY);
        hiloPrioridadMedia.setPriority(Thread.NORM_PRIORITY);
        hiloPrioridadAlta.setPriority(Thread.MAX_PRIORITY);
//      Inicio hilos
        hiloPrioridadBaja.start();
        hiloPrioridadMedia.start();
        hiloPrioridadAlta.start();
//      Duermo el hilo principal 3 segundos para que cuenten los hilos
        Thread.sleep(1000);
//      Paro los hilos
        hiloPrioridadBaja.pararHilo();
        hiloPrioridadMedia.pararHilo();
        hiloPrioridadAlta.pararHilo();
//      Muestro contador
        System.out.println("Hilo LOW: " + hiloPrioridadBaja.getC());
        System.out.println("Hilo NORM: " + hiloPrioridadMedia.getC());
        System.out.println("Hilo MAX: " + hiloPrioridadAlta.getC());
    }


}

class Hilo extends Thread {
    private int c = 0;
    private volatile boolean stopHilo = false;

    public int getC() {
        return c;
    }

    public void pararHilo() {
        stopHilo = true;
    }

    @Override
    public void run() {
        while (!stopHilo) c++;
    }
}
