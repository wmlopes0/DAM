package t02_ejercicio9_walter;

import java.util.concurrent.Semaphore;

public class Jaula {

    //Variables
    private int canariosEsperando;
    private int canariosComiendo;
    private Semaphore comedero;
    private Semaphore mutex;
    private Semaphore comerAlpiste;
    private Semaphore reponerAlpiste;
    private boolean quiereReponer;

    public Jaula() {
        comedero = new Semaphore(3, true);
        mutex = new Semaphore(1, true);
        comerAlpiste = new Semaphore(0, true);//Semáforo para quedar esperando a los canarios cuando el encargado quiere reponer el alpiste
        reponerAlpiste = new Semaphore(0, true);//Semáforo para quedar esperando al encargado mientras salen los canarios para poder reponer el alpiste
        canariosEsperando = 0;//Necesito contabilizar los canarios esperando para poder soltar los permisos necesarios
        canariosComiendo = 0;
        quiereReponer = false;
    }

    public void comerAlpiste(Canario canario) throws InterruptedException {
        //ENTRAR AL COMEDERO========================
        comedero.acquire();
        mutex.acquire();
        if (quiereReponer) {
            canariosEsperando++;
            mutex.release();
            comerAlpiste.acquire();//Bloqueo el canario hasta que el encargado termine de reponer el alpiste
            mutex.acquire();
        }
        canariosComiendo++;
        System.out.println("-----" + canario.getNombre() + " está comiendo alpiste <0)");
        mutex.release();
        //SALIR DEL COMEDERO=====================================
        mutex.acquire();
        canariosComiendo--;
        System.out.println("\t\t" + canario.getNombre() + " abandona el comedero WW_O_WW");
        if (quiereReponer == true && canariosComiendo == 0) {
            reponerAlpiste.release();//Si es el último canario en salir llamo al encargado
        }
        mutex.release();
        comedero.release();
    }

    public void reponerAlpiste(Encargado encargado) throws InterruptedException {
        mutex.acquire();
        quiereReponer = true;
        System.out.println(encargado.getNombre() + " quiere reponer alpiste");
        mutex.release();
        reponerAlpiste.acquire();//Bloqueo haste que terminen de salir todos
        mutex.acquire();
        System.out.println("+++++Reponiendo alpiste");
        quiereReponer = false;
        mutex.release();
        reponerAlpiste.release();
        comerAlpiste.release(canariosEsperando);
        mutex.acquire();
        canariosEsperando = 0;
        mutex.release();
    }
}
