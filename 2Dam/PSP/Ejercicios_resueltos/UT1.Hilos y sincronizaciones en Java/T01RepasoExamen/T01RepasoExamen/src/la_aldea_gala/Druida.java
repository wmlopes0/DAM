package la_aldea_gala;

public class Druida extends Thread {
    private String nombre;
    private Marmita marmita;

    public Druida(String nombre, Marmita marmita) {
        this.nombre = nombre;
        this.marmita = marmita;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        while (true) {
            marmita.rellenarMarmita(this);
        }
    }
}
