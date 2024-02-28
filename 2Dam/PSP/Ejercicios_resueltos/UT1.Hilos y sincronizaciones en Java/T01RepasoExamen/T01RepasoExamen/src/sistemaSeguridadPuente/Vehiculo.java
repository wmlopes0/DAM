package sistemaSeguridadPuente;

public class Vehiculo extends Thread {

    //Atributos
    private int idVehiculo;
    private int peso;
    private String tipo;
    private Puente puente;

    //Constructor
    public Vehiculo(int id, int peso, String tipo, Puente puente) {
        this.idVehiculo = id;
        this.peso = peso;
        this.tipo = tipo;
        this.puente = puente;
    }

    //Getter
    public int getPeso() {
        return peso;
    }

    public boolean isAmbulancia() {
        return tipo.equalsIgnoreCase("ambulancia");
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public void run() {
        puente.entrarPuente(this);
        puente.salirPuente(this);
    }
}
