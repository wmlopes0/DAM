package t02_repaso_examen3;

public class Ambulancia extends Thread {

    //Atributos
    private String nombre;
    private Parking parking;
    private int numPlaza;

    //Constructor
    public Ambulancia(String nombre, Parking parking) {
        this.nombre = nombre;
        this.parking = parking;
        numPlaza = -1;
    }

    //Getter
    public String getNombre() {
        return nombre;
    }

    public int getNumPlaza() {
        return numPlaza;
    }

    public void setNumPlaza(int numPlaza) {
        this.numPlaza = numPlaza;
    }

    @Override
    public void run() {
        try {
            parking.aparcar(this);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
    }
}
