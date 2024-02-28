package ejercicio15;

import java.util.Random;

public class ThreadB extends Thread {
    private Print print;

    public ThreadB(Print print) {
        this.setName("B");
        this.print = print;
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
        }
    }
}
