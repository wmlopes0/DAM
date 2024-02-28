package t02_repaso_examen2;

import java.util.concurrent.Semaphore;

public class Pantalla {

    private Semaphore pintarA = new Semaphore(0, true);
    private Semaphore pintarB = new Semaphore(0, true);
    private Semaphore pintarC = new Semaphore(1, true);

    private Semaphore mutex = new Semaphore(1, true);
    private int contadorA = 0;

    public void pintarA(LetraA a) throws InterruptedException {
        pintarA.acquire();
        mutex.acquire();
        if (contadorA < 0) {
            System.out.print(a.getLetra() + " ");
            contadorA++;
        }
        mutex.release();
        pintarA.release();
        pintarB.release();//Después va B
    }

    public void pintarB(LetraB b) throws InterruptedException {
        pintarB.acquire();
        System.out.print(b.getLetra() + " ");
        mutex.acquire();
        contadorA--;
        mutex.release();
        pintarB.release();
        pintarA.release();//Ahora va la A
    }

    public void pintarC(LetraC c) throws InterruptedException {
        pintarC.acquire();
        System.out.print(c.getLetra() + " ");
        pintarC.release();
        pintarB.release();//La B va despues
    }
}

