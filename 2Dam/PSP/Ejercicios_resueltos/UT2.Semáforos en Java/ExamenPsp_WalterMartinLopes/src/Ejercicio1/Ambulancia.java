package Ejercicio1;

public class Ambulancia extends Thread {

    //Atributos
    private String nombre;
    private int numPlaza;
    private Parking parking;

    //Constructor
    public Ambulancia(String nombre, Parking parking) {
        this.nombre = nombre;
        this.numPlaza = -1; //Inicio el número de plaza en -1, será el indicador de que no está aparcada
        this.parking = parking;
    }

    //Getter
    public String getNombre() {
        return nombre;
    }

    public void setNumPlaza(int numPlaza) {
        this.numPlaza = numPlaza;
    }

    public int getNumPlaza() {
        return numPlaza;
    }

    //Run
    @Override
    public void run() {
        try {
            System.out.println("Arranca la " + nombre);
            parking.aparcar(this);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
    }
}
