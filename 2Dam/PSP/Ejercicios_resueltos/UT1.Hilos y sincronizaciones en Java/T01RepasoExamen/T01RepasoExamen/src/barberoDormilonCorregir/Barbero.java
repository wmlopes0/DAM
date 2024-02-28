package barberoDormilonCorregir;

public class Barbero extends Thread {
    private String nombre;
    private Barberia barberia;

    public Barbero(String nombre, Barberia barberia) {
        this.nombre = nombre;
        this.barberia = barberia;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        while (true) {
            barberia.cortarPelo(this);
        }
    }
}
