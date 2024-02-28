package t02_ejercicio11_walter;

import java.util.concurrent.Semaphore;

public class Ascensor {

    //Variables y Semáforos
    private int aforoMaximo;
    private int aforoActual;
    private float pesoActual;
    private int personasEsperando;
    private float pesoMaximo;
    private Semaphore mutex;
    private Semaphore subirAscensor;

    //Constructor
    public Ascensor(int aforoMaximo, float pesoMaximo) {
        this.aforoMaximo = aforoMaximo;
        this.pesoMaximo = pesoMaximo;
        aforoActual = 0;
        pesoActual = 0f;
        personasEsperando = 0;
        mutex = new Semaphore(1, true);
        subirAscensor = new Semaphore(0, true);
    }

    public void sube_persona(Persona persona) throws InterruptedException {
        //Mensaje de llamar al ascensor
        mutex.acquire();
        System.out.println(persona.getNombre() + " llama al ascensor, su peso es de " + persona.getPeso() + "Kg");
        //Compruebo si puede entrar
        while ((aforoActual + 1) > aforoMaximo || (pesoActual + persona.getPeso()) > pesoMaximo) {
            personasEsperando++;
            System.out.println(persona.getNombre() + " tiene que esperar, las condiciones actuales son [" + aforoActual + ", " + pesoActual + "Kg]");
            mutex.release();
            subirAscensor.acquire();//Bloqueo hasta que salga alguno del ascensor y vuelva a comprobar
            mutex.acquire();
        }
        //Entro
        aforoActual++;
        pesoActual += persona.getPeso();
        System.out.println("\t\t" + persona.getNombre() + " sube al ascensor");
        mutex.release();
    }

    public void baja_persona(Persona persona) throws InterruptedException {
        mutex.acquire();
        aforoActual--;
        pesoActual -= persona.getPeso();
        System.out.println("\t\t\t==>" + persona.getNombre() + " baja del ascensor.Las condiciones actuales son [" + aforoActual + ", " + pesoActual + "Kg]");
        subirAscensor.release(personasEsperando);//Libero los permisos necesarios para que las personas que están esperando vuelvan a comprobar si pueden subir
        personasEsperando = 0;//Reseteo el contador de porsonas esperando
        mutex.release();
    }
}
