package Ejercicio2;

import java.util.concurrent.Semaphore;

public class FabricaJuguetes {

    //Variables y semáforos
    private int capacidad;
    private int juguetesActuales;
    private int productoresEsperando;
    private int consumidoresEsperando;
    private Semaphore mutex;
    private Semaphore introducir;
    private Semaphore recoger;

    //Constructor
    public FabricaJuguetes(int capacidad) {
        this.capacidad = capacidad;
        this.juguetesActuales = 0;
        this.productoresEsperando = 0;
        this.consumidoresEsperando = 0;
        this.mutex = new Semaphore(1, true);
        this.introducir = new Semaphore(0, true);//Semáforo para hacer esperar a los duendes productores
        this.recoger = new Semaphore(0, true);//Semáforo para hacer esperar a los duendes consumidores
    }

    public void introducir_juguetes(DuendeProductor duende) throws InterruptedException {
        //MENSAJE DE INTENCIÓN DE INTRODUCIR JUGUETES
        mutex.acquire();
        System.out.println("=>" + duende.getNombre() + " quiere INTRODUCIR " + duende.getNumJuguetes() + " juguetes (actuales=" + juguetesActuales + ")");

        //COMPRUEBA SI PUEDE INTRODUCIR LOS JUGUETES SIN EXCEDER LA CAPACIDAD MÁXIMA
        while ((juguetesActuales + duende.getNumJuguetes()) > capacidad) {
            System.out.println("\t\t" + duende.getNombre() + " bloqueado para no reventar la máquina");
            productoresEsperando++;//Incremento los productores esperando
            mutex.release();
            introducir.acquire();//Hago esperar al duende hasta que pueda introducir juguetes
            mutex.acquire();
        }

        //INTRODUCE LOS JUGUETES
        juguetesActuales += duende.getNumJuguetes();//Incremento los juguetes actuales
        System.out.println("\t\t" + duende.getNombre() + " introduciendo " + duende.getNumJuguetes() + " juguete/s: juguetes actuales=" + juguetesActuales);
        recoger.release(consumidoresEsperando);//Suelto permisos para que vuelvan a comprobar si pueden recoger juguetes
        consumidoresEsperando = 0;//Reseteo contador
        mutex.release();
    }

    public void recoger_juguetes(DuendeConsumidor duende) throws InterruptedException {
        //MENSAJE DE INTENCIÓN DE RECOGER JUGUETES
        mutex.acquire();
        System.out.println("<=" + duende.getNombre() + " quiere RECOGER " + duende.getNumJuguetes() + " juguetes (actuales=" + juguetesActuales + ")");

        //COMPRUEBA SI PUEDE RECOGER LOS JUGUETES
        while ((juguetesActuales - duende.getNumJuguetes()) < 0) {
            System.out.println("\t\t" + duende.getNombre() + " bloqueado porque no hay suficientes juguetes para recoger");
            consumidoresEsperando++;//Incremento los consumidores esperando
            mutex.release();
            recoger.acquire();//Hago esperar al duende hasta que haya suficientes juguetes
            mutex.acquire();
        }

        //RECOGE LOS JUGUETES
        juguetesActuales -= duende.getNumJuguetes();//Decremento los juguetes actuales
        System.out.println("\t\t" + duende.getNombre() + " recogiendo " + duende.getNumJuguetes() + " juguetes, quedan=" + juguetesActuales);
        introducir.release(productoresEsperando);//Suelto permisos para que vuelvan a comprobar si pueden producir juguetes
        productoresEsperando = 0;//Reseteo contador
        mutex.release();
    }

}
