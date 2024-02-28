package sincronizacion_comunicacion.Ejemplo3_Wait_NotifyAll;

public class Buffer {
    private int bizcochos = 0;

    public synchronized void producir(int i) throws InterruptedException {
        while (bizcochos > 0) { // Si hay bizcochos, esperar
            wait();
        }
        bizcochos = i;
        System.out.println("El productor ha producido " + bizcochos + " bizcochos.");
        notifyAll(); // Notificar al consumidor
    }

    public synchronized void consumir(int i) throws InterruptedException {
        while (bizcochos == 0) { // Si no hay bizcochos, esperar
            wait();
        }
        System.out.println("El consumidor ha consumido " + bizcochos + " bizcochos.");
        bizcochos -= i;
        notifyAll(); // Notificar al productor
    }
}
