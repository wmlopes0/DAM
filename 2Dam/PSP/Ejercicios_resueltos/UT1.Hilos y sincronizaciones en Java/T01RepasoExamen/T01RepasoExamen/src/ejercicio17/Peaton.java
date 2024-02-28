package ejercicio17;

public class Peaton extends Thread {
    private String nombre;
    private Puente puente;

    public Peaton(String nombre, Puente puente) {
        this.nombre = nombre;
        this.puente = puente;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            puente.pasaPeaton(this);
        }
    }
}
