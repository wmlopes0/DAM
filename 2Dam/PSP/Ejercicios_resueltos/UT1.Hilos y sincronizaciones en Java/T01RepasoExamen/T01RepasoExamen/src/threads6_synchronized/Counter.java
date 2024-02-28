package threads6_synchronized;

public class Counter {
    private boolean sincronizado;

    public Counter(boolean sincronizado) {
        this.sincronizado = sincronizado;
    }

    public boolean isSincronizado() {
        return sincronizado;
    }

    public synchronized void mostrarNumerosSincronizado(int id, int n) {
        System.out.println("Inicio hilo " + id);
        for (int i = 0; i <= n; i++) {
            System.out.println("Hilo " + id + ": " + i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Fin hilo " + id);
    }

    public void mostrarNumerosNoSincronizado(int id, int n) {
        System.out.println("Inicio hilo " + id);
        for (int i = 0; i <= n; i++) {
            System.out.println("Hilo " + id + ": " + i);
        }
        System.out.println("Fin hilo " + id);
    }
}
