package ejercicio17;

public class Peaton extends Thread {

    //    Atributos
    private String nombre;
    private Puente puente;

    //    Constructor
    public Peaton(String nombre, Puente puente) {
        this.nombre = nombre;
        this.puente = puente;
    }

    //    Getter
    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                puente.entrar(this, null);
                puente.salir(this, null);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
