package t02_ejercicio7_walter;

public class Persona extends Thread {

    //Atributos
    private String nombre;
    private Banio banio;

    //Constructor
    public Persona(String nombre, Banio banio) {
        this.nombre = nombre;
        this.banio = banio;
    }

    //Getter nombre
    public String getNombre() {
        return nombre;
    }

    //Run
    @Override
    public void run() {
//        for (int i = 0; i < 2; i++) {
        try {
            banio.usarBanio(this);
        } catch (InterruptedException e) {
            //throw new RuntimeException(e);
        }
//        }
    }
}

