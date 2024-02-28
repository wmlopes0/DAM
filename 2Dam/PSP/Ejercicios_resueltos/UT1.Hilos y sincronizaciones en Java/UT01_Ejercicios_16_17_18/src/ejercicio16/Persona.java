package ejercicio16;

import java.util.Random;

public class Persona extends Thread {
    private Wc wc;
    private String nombre;

    //    Constructor
    public Persona(Wc wc, String nombre) {
        this.wc = wc;
        this.nombre = nombre;
    }

    //   Getter
    public String getNombre() {
        return nombre;
    }

    //        Este método retorna una cantidad aleatoria de ms entre 5 y 20ms
    public int tiempoAleatorio() {
        return new Random().nextInt((20 + 1) - 5) + 5;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
//        Entrar
            try {
                wc.entrar(this);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//        Tiempo aleatorio dentro
            try {
                int tiempoAleatorio = tiempoAleatorio();
                System.out.println("        " + this.nombre + " permanece en el baño " + tiempoAleatorio + " milisegundos.");
                sleep(tiempoAleatorio);
            } catch (InterruptedException e) {
                //Nada
            }
//        Salir
            wc.salir(this);
//        Espera de 50s después de ir al baño
            try {
                sleep(500);
            } catch (InterruptedException e) {
                //Nada
            }
        }
    }
}
