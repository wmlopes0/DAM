package ejercicio3;

import java.util.Scanner;

/**
 *
 * @author java2
 */
public class Viaje {

    private int numClientes;
    private float ingresos;

    public Viaje() {
        numClientes = 0;
        ingresos = 0;
    }

    public Viaje(int numClientes, float ingresos) {
        this.numClientes = numClientes;
        this.ingresos = ingresos;
    }

    public void setNumClientes(int numClientes) {
        this.numClientes = numClientes;
    }

    public void setIngresos(float ingresos) {
        this.ingresos = ingresos;
    }

    public int getNumClientes() {
        return numClientes;
    }

    public float getIngresos() {
        return ingresos;
    }

    //METODOS PROPIOS DE VIAJE
    public void rellenarViaje() {
        System.out.println("--------VIAJE--------");
        numClientes = pedirClientes();
        ingresos = pedirIngresos();
        System.out.println("---------------------");
    }

    //Metodos rellenar, son privados porque no me interesa que se puedan llamar desde la clase Test.
    private int pedirClientes() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduzca el numero de clientes: ");
        return entrada.nextInt();
    }

    private float pedirIngresos() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduzca los ingresos: ");
        return entrada.nextFloat();
    }
    //---------------------------------------------------------------

    public void mostrarViaje() {
        System.out.println("--------VIAJE--------");
        System.out.println("Numero clientes: " + numClientes);
        System.out.println("Ingresos: " + ingresos);
        System.out.println("---------------------");
    }
}
