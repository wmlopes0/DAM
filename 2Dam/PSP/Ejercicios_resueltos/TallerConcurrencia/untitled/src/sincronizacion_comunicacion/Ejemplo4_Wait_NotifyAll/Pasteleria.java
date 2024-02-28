package sincronizacion_comunicacion.Ejemplo4_Wait_NotifyAll;

public class Pasteleria {
    private volatile int bizcochos;
    private volatile boolean disponible = false;
    private volatile boolean pasteleroStarted = false;

    public Pasteleria() {
    }

    public synchronized void producir(int numBizcochos) {
        while (disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        bizcochos = numBizcochos;
        pasteleroStarted = true;
        disponible = true;
        notifyAll();
    }

    public synchronized int consumir() {
        while (!pasteleroStarted || !disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        disponible = false;
        notifyAll();
        return bizcochos;
    }
}