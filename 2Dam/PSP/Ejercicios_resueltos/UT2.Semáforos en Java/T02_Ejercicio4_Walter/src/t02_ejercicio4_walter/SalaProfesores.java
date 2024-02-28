package t02_ejercicio4_walter;

import java.util.concurrent.Semaphore;

public class SalaProfesores {

    //Atributos
    private Semaphore acceso;
    private Semaphore mutex;//Mutex que utilizo para asegurarme que la variable profesoresDentro se modifique en exclusión mutua
    private int profesoresDentro = 0;


    //Constructor
    public SalaProfesores(int capacidad) {
        this.acceso = new Semaphore(capacidad, true); //Inicializo semáforo con la capacidad indicada y justicia FIFO
        this.mutex = new Semaphore(1, true);//Inicializo el mutex
    }

    public void entrar(Profesor profesor) throws InterruptedException {
        //Adquiero un permiso si hay alguno disponible, sino espero
        acceso.acquire();
        //Muestro mensaje de "Profesor se dirije a la sala de profesores"
        System.out.println(profesor.getNombre() + " se dirige a la Sala de Profesores");
        //Incremento contador y muestro mensaje en exclusión mutua utilizando el mutex
        mutex.acquire();
        profesoresDentro++;
        System.out.println("\t\t+++ " + profesor.getNombre() + " está dentro de la Sala de Profesores. En estos momentos hay [" + profesoresDentro + "]");
        mutex.release();
        Thread.sleep(200);//Espero 200ms dentro
        //Decremento el contador y muestro el mensaje en exclusión mutua utilizando el mutex, después  libero el permiso para que otro pueda entrar.
        mutex.acquire();
        profesoresDentro--;
        System.out.println("\t\t\t--- " + profesor.getNombre() + " abandona la Sala de Profesores. En estos momentos hay [" + profesoresDentro + "]");
        mutex.release();
        acceso.release();
    }

}
