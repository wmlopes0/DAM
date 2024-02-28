package t02_ejercicio8_walter;

import java.util.concurrent.Semaphore;

public class SalaBaile {

    private int hombres;
    private int mujeres;
    private Semaphore mutex;
    private Semaphore entrarSalaBaile;

    public SalaBaile() {
        this.hombres = 0;
        this.mujeres = 0;
        this.mutex = new Semaphore(1, true);
        this.entrarSalaBaile = new Semaphore(0, true);
    }

    public void bailar(Persona persona) throws InterruptedException {
        //INTENTAR ENTRAR ================================================
        mutex.acquire();
        System.out.println(persona.getNombre() + " quiere entrar en el salón de baile [M:" + mujeres + ", H:" + hombres + "]");
        while (hombres != mujeres && (persona.esHombre() && (hombres + 1) != mujeres || !persona.esHombre() && (mujeres + 1) != hombres)) {
            mutex.release();
            entrarSalaBaile.acquire();//Bloqueo y espero a que salga uno
            mutex.acquire();
        }

        //ENTRA Y BAILA ================================================
        if (persona.esHombre()) {
            hombres++;
        } else {
            mujeres++;
        }
        System.out.println("\t" + persona.getNombre() + " está bailando [M:" + mujeres + ", H:" + hombres + "]");
        mutex.release();
        Thread.sleep(1000);//Baila 1 segundo porque se cansa enseguida

        //SALIR ========================================================
        mutex.acquire();
        if (persona.esHombre()) {
            hombres--;
        } else {
            mujeres--;
        }
        System.out.println("\t\t" + persona.getNombre() + " terminó de bailar [M:" + mujeres + ", H:" + hombres + "]");
        entrarSalaBaile.release();
        mutex.release();
    }
}
