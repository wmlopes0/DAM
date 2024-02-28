package t11ejercicio03;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Conductor {

    private String dni;
    private String nombre;

    public Conductor() {
        dni = "";
        nombre = "";

    }

    public Conductor(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    //METODOS PROPIOS
    //Rellenar
    public void rellenarConductor() {
        System.out.println("=====Conductor=====");
        dni = pedirDni();
        nombre = pedirNombre();
        System.out.println("-------------------");
    }

    private String pedirDni() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce el DNI: ");
        return entrada.nextLine();
    }

    private String pedirNombre() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce el nombre: ");
        return entrada.nextLine();
    }

    //Mostrar
    public void mostrarConductor() {
        System.out.println("------CONDUCTOR------");
        System.out.println("DNI: " + dni);
        System.out.println("Nombre: " + nombre);
    }
}
