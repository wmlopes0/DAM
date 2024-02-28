package la_aldea_gala;

public class Galo extends Thread{
    private String nombre;
    private Marmita marmita;

    public Galo(String nombre, Marmita marmita) {
        this.nombre = nombre;
        this.marmita = marmita;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            marmita.tomarRacion(this);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }
        }
    }
}
