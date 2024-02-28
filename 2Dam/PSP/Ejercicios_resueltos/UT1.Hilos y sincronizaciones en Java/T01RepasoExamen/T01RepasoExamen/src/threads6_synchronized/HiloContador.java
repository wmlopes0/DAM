package threads6_synchronized;

public class HiloContador extends Thread {
    private int id;
    private Counter counter;
    private int n;

    public HiloContador(int id, Counter counter, int n) {
        this.id = id;
        this.counter = counter;
        this.n = n;
    }

    @Override
    public void run() {
        if (this.counter.isSincronizado()) {
            this.counter.mostrarNumerosSincronizado(id, n);
        } else {
            this.counter.mostrarNumerosNoSincronizado(id, n);
        }
    }
}
