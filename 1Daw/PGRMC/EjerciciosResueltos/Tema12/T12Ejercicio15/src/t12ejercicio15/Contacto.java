package t12ejercicio15;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Contacto {

    private String nombre;
    private int edad;
    private int telefono;

    //CONSTRUCTORES
    public Contacto() {
        nombre = "";
        edad = 0;
        telefono = 0;
    }

    public Contacto(String nombre, int edad, int telefono) {
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
    }

    //SETTER Y GETTER
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public int getTelefono() {
        return telefono;
    }

    //METODOS RELLENAR
    public void rellenarContacto() {
        System.out.println("=====CONTACTO=====");
        nombre = pedirCadena("el nombre");
        edad = pedirEntero("la edad");
        telefono = pedirEntero("el telefono");
    }

    private String pedirCadena(String texto) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce " + texto + ":");
        return entrada.nextLine();
    }

    private int pedirEntero(String texto) {
        Scanner entrada = new Scanner(System.in);
        int entero = 0;
        System.out.println("Introduce " + texto + ":");
        try {
            entero = entrada.nextInt();
        } catch (InputMismatchException e) {
            pedirEntero(texto);
        }
        return entero;
    }
}
