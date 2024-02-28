package Ejercicio1;

import java.util.concurrent.Semaphore;

public class Parking {

    //Variables y semáforos
    private boolean[] plazas;//Si está a TRUE significa que está ocupada
    private Semaphore mutex;
    private Semaphore ambulanciaEsperando;

    //Constructor
    public Parking(int numPlazas) {
        plazas = new boolean[numPlazas];
        mutex = new Semaphore(1, true);
        ambulanciaEsperando = new Semaphore(0, true); //Semáforo para hacer esperar a las ambulancias cuando no pueden entrar
    }

    public void aparcar(Ambulancia ambulancia) throws InterruptedException {
        //AMBULANCIA INTENTA ENTRAR EN EL PARKING
        mutex.acquire();
        System.out.println("\t\t||La " + ambulancia.getNombre() + " llega al parking e intenta entrar");
        while (totalPlazasLibres() == 0) {
            mutex.release();
            ambulanciaEsperando.acquire();//Si el parking está lleno hago esperar a la ambulancia hasta que salga alguna
            mutex.acquire();
        }

        //ENTRA EN EL PARKING
        System.out.println("\t\t\t\t=>La " + ambulancia.getNombre() + " entra al Parking");

        //APARCA EN LA PRIMERA PLAZA LIBRE QUE VE
        ambulancia.setNumPlaza(plazaLibre());//Asigno el número de plaza a la ambulancia
        plazas[ambulancia.getNumPlaza()] = true;//Ocupo la plaza en el array de booleanos
        System.out.println("\t\t\t\t---------La " + ambulancia.getNombre() + " ha aparcado en la plaza " + ambulancia.getNumPlaza() + ". Quedan libres " + totalPlazasLibres());
        mutex.release();

        //TIEMPO DENTRO DEL PARKING
        Thread.sleep(4000);

        //SALE DEL PARKING
        mutex.acquire();
        plazas[ambulancia.getNumPlaza()] = false;//Libero la plaza en el array de booleanos
        ambulancia.setNumPlaza(-1);//Asigno de nuevo -1 al atributo número de plaza de Ambulancia
        System.out.println("\t\t\t\t\t\t<=La " + ambulancia.getNombre() + " abandona el Parking. Quedan libres " + totalPlazasLibres());
        ambulanciaEsperando.release();//Libero un permiso para que pueda entrar otra ambulancia
        mutex.release();
    }

    //Este método hace una búsqueda en el parking y devuelve la posicion de la primera plaza libre
    //Si el parking está lleno devolverá -1
    private int plazaLibre() {
        for (int i = 0; i < plazas.length; i++) {
            if (!plazas[i]) {
                return i;
            }
        }
        return -1;
    }

    //Este método retorna el total de plazas libres del parking en ese momento
    private int totalPlazasLibres() {
        int plazasLibres = 0;
        for (boolean plaza : plazas) {
            if (!plaza) {
                plazasLibres++;
            }
        }
        return plazasLibres;
    }

}
