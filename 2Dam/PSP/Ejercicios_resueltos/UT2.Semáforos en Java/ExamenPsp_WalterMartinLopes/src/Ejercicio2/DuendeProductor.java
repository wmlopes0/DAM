package Ejercicio2;

public class DuendeProductor extends Thread {

    //Atributos
    private String nombre;
    private int numJuguetes;
    private FabricaJuguetes fabricaJuguetes;

    //Constructor
    public DuendeProductor(String nombre, int numJuguetes, FabricaJuguetes fabrica) {
        this.nombre = nombre;
        this.numJuguetes = numJuguetes;
        this.fabricaJuguetes = fabrica;
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
            fabricaJuguetes.introducir_juguetes(this);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
    }
}
