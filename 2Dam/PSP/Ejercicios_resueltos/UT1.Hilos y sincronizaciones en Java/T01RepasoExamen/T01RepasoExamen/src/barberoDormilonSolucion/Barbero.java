package barberoDormilonSolucion;

public class Barbero extends Thread{
    private Barberia barberia;

    public Barbero(Barberia barberia) {
        this.barberia = barberia;
    }

    @Override
    public void run() {
        while (true) {

            try {
                barberia.esperarCliente();
                //Cortar pelo
                Thread.sleep(5000);
                barberia.acabarCorte();
                //Decansa un poco
                Thread.sleep(1000);
            } catch (InterruptedException e){};
        }

    }
}
