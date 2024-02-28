package ejercicio2;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author final23
 */
public class Plataforma {

    private String nombre;
    private int numReproducciones;

    //CONSTRUCTORES
    public Plataforma() {
        nombre = "";
        numReproducciones = 0;
    }

    public Plataforma(String nombre, int numReproducciones) {
        this.nombre = nombre;
        this.numReproducciones = numReproducciones;
    }

    //SETTER Y GETTER
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumReproducciones(int numReproducciones) {
        this.numReproducciones = numReproducciones;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumReproducciones() {
        return numReproducciones;
    }

    //METODOS PROPIOS
    public void rellenarInfo() {
        System.out.println("******** P L A T A F O R M A ********");
        nombre = pedirCadena("Introduce el nombre de la plataforma: ");
        numReproducciones = pedirEntero("Introduce el numero de reproducciones: ");
    }

    public void mostrarInfo() {
        System.out.println("******** P L A T A F O R M A ********");
        System.out.println("Nombre: " + nombre);
        System.out.println("Numero de reproducciones: " + numReproducciones);
    }

    /*Estos métodos de pedir son privados porque no tiene sentido llamarlos si no es desde
    el método rellenar info*/
    private String pedirCadena(String mensaje) {
        Scanner entrada = new Scanner(System.in);
        System.out.println(mensaje);
        return entrada.nextLine();
    }

    private int pedirEntero(String mensaje) {
        Scanner entrada = new Scanner(System.in);
        int numero;
        System.out.println(mensaje);
        try {
            numero = entrada.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ERROR: Debes introducir un numero.");
            entrada.next(); //Limpio el buffer
            numero = pedirEntero(mensaje); //Utilizo la recursividad para asegurarme que me introduce un numero
        }
        return numero;
    }
}
