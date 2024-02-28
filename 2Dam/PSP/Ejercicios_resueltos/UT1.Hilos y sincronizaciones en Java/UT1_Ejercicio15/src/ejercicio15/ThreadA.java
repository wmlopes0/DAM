package ejercicio15;

import java.util.Random;

public class ThreadA extends Thread {
    private int contador = 0;
    private Print print;

    public ThreadA(Print print) {
        this.setName("A");
        this.print = print;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            print.pintar();
            try {
                Thread.sleep((new Random().nextInt(100)));
            } catch (InterruptedException e) {
                this.interrupt();
            }
            if (!this.isInterrupted()) {
                contador++;
            }
        }
    }
}
