package t02_ejercicio7_walter;

public class Empleado extends Thread {

    //Atributos
    private String nombre;
    private Banio banio;

    //Constructor
    public Empleado(String nombre, Banio banio) {
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
        try {
            banio.limpiarBanio(this);
        } catch (InterruptedException e) {
//                throw new RuntimeException(e);
        }
    }
}

