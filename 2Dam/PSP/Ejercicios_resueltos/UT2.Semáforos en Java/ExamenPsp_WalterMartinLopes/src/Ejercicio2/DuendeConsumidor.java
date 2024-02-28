package Ejercicio2;

public class DuendeConsumidor extends Thread {
    //Atributos
    private String nombre;
    private int numJuguetes;
    private FabricaJuguetes fabricaJuguetes;

    //Constructor
    public DuendeConsumidor(String nombre, int numJuguetes, FabricaJuguetes fabricaJuguetes) {
        this.nombre = nombre;
        this.numJuguetes = numJuguetes;
        this.fabricaJuguetes = fabricaJuguetes;
    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    public int getNumJuguetes() {
        return numJuguetes;
    }

    //Run
    @Override
    public void run() {
        try {
            fabricaJuguetes.recoger_juguetes(this);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
    }
}
