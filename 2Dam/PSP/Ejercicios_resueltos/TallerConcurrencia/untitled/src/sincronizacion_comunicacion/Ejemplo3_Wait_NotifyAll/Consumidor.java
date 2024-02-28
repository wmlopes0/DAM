package sincronizacion_comunicacion.Ejemplo3_Wait_NotifyAll;

public class Consumidor extends Thread {
    private Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 01; i <= 10; i++) {
            try {
                buffer.consumir(i);
//                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

