package ejercicio17;

public class Barco extends Thread{
    private String nombre;

    private Puente puente;

    public Barco(String nombre, Puente puente) {
        this.nombre = nombre;
        this.puente = puente;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            puente.pasaBarco(this);
        }
    }
}
