/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t12ejercicio05ampliacion;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Contacto {

    private String nombre;
    private int edad;
    private String telefono;

    public Contacto() {
        nombre = "";
        edad = 0;
        telefono = "";
    }

    public Contacto(String nombre, int edad, String telefono) {
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getTelefono() {
        return telefono;
    }

    //RELLENAR
    public void rellenar() {
        System.out.println("======CONTACTO======");
        nombre = pedirCadena("nombre");
        edad = pedirNumero("edad");
        telefono = pedirCadena("telefono");
    }

    private String pedirCadena(String texto) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce " + texto + ": ");
        return entrada.nextLine();
    }

    private int pedirNumero(String texto) {
        int num = 0;
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce " + texto + ": ");
        try {
            num = entrada.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ERROR, INTRODUCE UN NUMERO");
            pedirNumero(texto);
        }
        return num;
    }

    //MOSTRAR
    public void mostrar() {
        System.out.println(nombre + " - " + edad + " - " + telefono);
    }
}
