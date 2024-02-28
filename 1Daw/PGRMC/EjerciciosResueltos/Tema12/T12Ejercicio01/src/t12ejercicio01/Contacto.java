/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t12ejercicio01;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Contacto implements Comparable<Contacto> {

    private String nombre;
    private int edad;
    private int numeroMovil;

    public Contacto() {
        nombre = "";
        edad = 0;
        numeroMovil = 0;
    }

    public Contacto(String nombre, int edad, int numeroMovil) {
        this.nombre = nombre;
        this.edad = edad;
        this.numeroMovil = numeroMovil;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setNumeroMovil(int numeroMovil) {
        this.numeroMovil = numeroMovil;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public int getNumeroMovil() {
        return numeroMovil;
    }

    //RELLENAR
    public void rellenarContacto() throws InputMismatchException {
        System.out.println("----CONTACTO----");
        nombre = pedirNombre();
        edad = pedirNumero("Edad");
        numeroMovil = pedirNumero("Numero de movil");
    }

    private String pedirNombre() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce un nombre: ");
        return entrada.nextLine();
    }

    private int pedirNumero(String texto) throws InputMismatchException {
        Scanner entrada = new Scanner(System.in);
        System.out.println(texto + ": ");
        return entrada.nextInt();
    }

    //MOSTRAR
    public void mostrar() {
        System.out.println("====" + nombre + "====");
        System.out.println("Edad: " + edad);
        System.out.println("Telefono: " + numeroMovil);
    }

    @Override
    public int compareTo(Contacto t) {
        if (this.edad > t.edad) {
            return 1;
        } else if (this.edad < t.edad) {
            return -1;
        } else {
            return 0;
        }
    }
}
