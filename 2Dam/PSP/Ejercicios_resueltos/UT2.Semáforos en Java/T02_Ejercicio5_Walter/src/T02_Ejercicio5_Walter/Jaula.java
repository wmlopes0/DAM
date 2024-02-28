package T02_Ejercicio5_Walter;

import java.util.TreeMap;
import java.util.concurrent.Semaphore;

public class Jaula {

    //Semáforos
    private Semaphore comedero;
    private Semaphore columpio;

    //Constructor

    public Jaula(int maxComedero, int maxColumpio) {
        comedero = new Semaphore(maxComedero, true);
        columpio = new Semaphore(maxColumpio, true);
    }

    public void comer(Canario canario) throws InterruptedException {
        comedero.acquire();//Adquiero un permiso para comer si hay alguno disponible
        System.out.println(canario.getNombre() + " está comiendo alpiste <0)");
        Thread.sleep(5000);//Comen durante 5s
        System.out.println("\t\t" + canario.getNombre() + " terminó de comer");
        comedero.release();//Libero el permiso
    }

    public void columpiarse(Canario canario) throws InterruptedException {
        columpio.acquire();//Adquiero un permiso para columpiarme si hay alguno disponible
        System.out.println("\t\t\t" + canario.getNombre() + " está en el columpio ||__||");
        Thread.sleep(6000);//Se columpian durante 6s
        System.out.println("\t\t\t" + canario.getNombre() + " terminó de columpiarse");
        columpio.release();//Libero el permiso
    }
}
