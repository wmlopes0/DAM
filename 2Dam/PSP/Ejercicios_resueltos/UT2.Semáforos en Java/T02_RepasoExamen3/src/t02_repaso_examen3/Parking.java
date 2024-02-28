package t02_repaso_examen3;

import java.util.concurrent.Semaphore;

public class Parking {

    //Variables y semáforos
    private int numPlazas;
    private boolean[] plazas;//True ocupada
    private Semaphore mutex;
    private Semaphore ambulanciaEsperando;

    //Constructor
    public Parking(int numPlazas) {
        this.numPlazas = numPlazas;
        plazas = new boolean[numPlazas];
        mutex = new Semaphore(1, true);
        ambulanciaEsperando = new Semaphore(0, true);
    }

    public void aparcar(Ambulancia ambulancia) throws InterruptedException {
        mutex.acquire();
        //Comprueba plaza libre
        while (plazaLibre() == -1) {
            System.out.println(ambulancia.getNombre() + " espera porque todas las plazas están ocupadas.");
            mutex.release();
            ambulanciaEsperando.acquire();//Hago esperar a la ambulancia
            mutex.acquire();
        }
        //Si hay sitio entra
        ambulancia.setNumPlaza(plazaLibre());
        plazas[ambulancia.getNumPlaza()] = true;
        System.out.println("##" + ambulancia.getNombre() + " aparca en la plaza " + ambulancia.getNumPlaza());
        mostrarPlazasLibres();
        mutex.release();
        //Tiempo en el parking
        Thread.sleep(4000);//Está 4 segundos
        //Sale del parking
        mutex.acquire();
        plazas[ambulancia.getNumPlaza()] = false;
        System.out.println("---" + ambulancia.getNombre() + " sale de la plaza " + ambulancia.getNumPlaza());
        ambulancia.setNumPlaza(-1);
        mostrarPlazasLibres();
        ambulanciaEsperando.release();//Libero 1 permiso para que entre otra ambulancia
        mutex.release();
    }

    public int plazaLibre() {
        //Este método retorna -1 si no hay ninguna plaza libre o la primera posicion libre si la hay
        for (int i = 0; i < plazas.length; i++) {
            if (!plazas[i]) {
                return i;
            }
        }
        return -1;
    }

    public void mostrarPlazasLibres() {
        System.out.println("\n    PLAZAS LIBRES    ");
        System.out.println("---------------------");
        for (int i = 0; i < plazas.length; i++) {
            if (i == plazas.length - 1) {
                if (plazas[i]) {
                    System.out.print("| P" + i + " |");
                } else {
                    System.out.print("|    |");
                }
            } else {
                if (plazas[i]) {
                    System.out.print("| P" + i + " ");
                } else {
                    System.out.print("|    ");
                }
            }
        }
        System.out.println("\n---------------------");
    }
}
