package ejercicio13;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Persona implements Serializable {

    //Atributos
    private String dni;
    private String nombre;
    private String apellidos;
    private int telefono;
    private String email;

    //Constructor
    public Persona() {
        rellenarInfoPersona();
    }

    //Getter y Setter
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //Métodos propios
    private void rellenarInfoPersona() {
        System.out.println("## INTRODUCE LOS DATOS:");
        this.dni = pedirCadena("Introduzca el DNI: ");
        this.nombre = pedirCadena("Introduzca el nombre: ");
        this.apellidos = pedirCadena("Introduzca los apellidos: ");
        this.telefono = pedirEntero("Introduzca el número de teléfono: ");
        this.email = pedirCadena("Introduzca el email: ");

    }

    private static int pedirEntero(String texto) {
        Scanner entrada = new Scanner(System.in);
        int entero;
        System.out.println(texto);
        try {
            entero = entrada.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ERROR: No puedes introducir una letra.");
            entero = pedirEntero(texto);
        }
        return entero;
    }

    private static String pedirCadena(String texto) {
        Scanner entrada = new Scanner(System.in);
        System.out.println(texto);
        return entrada.nextLine();
    }

    @Override
    public String toString() {
        return "Persona [" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", telefono=" + telefono +
                ", email='" + email + '\'' +
                ']';
    }
}
