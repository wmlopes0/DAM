/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3;

import java.util.Scanner;

/**
 *
 * @author final23
 */
public class Usuario {

    private String nombre;
    private String dni;

    //CONSTRUCTORES
    public Usuario() {
        nombre = "";
        dni = "";
    }

    public Usuario(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    //SETTER Y GETTER
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    //METODOS PROPIOS
    public void rellenarInfo() {
        System.out.println("****** U S U A R I O ******");
        nombre = pedirCadena("Introduce el nombre: ");
        dni = pedirCadena("Introduce su dni: ");
    }

    public void mostrarInfo() {
        System.out.println("****** U S U A R I O ******");
        System.out.println("Nombre: " + nombre);
        System.out.println("DNI: " + dni);
    }

    //Este metodo es privado porque no tiene sentido llamarlo si no es desde el rellenar info
    private String pedirCadena(String mensaje) {
        Scanner entrada = new Scanner(System.in);
        System.out.println(mensaje);
        return entrada.nextLine();
    }
}
