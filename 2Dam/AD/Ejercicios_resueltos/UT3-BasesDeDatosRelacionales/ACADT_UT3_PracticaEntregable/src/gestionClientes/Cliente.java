package gestionClientes;

import general.Utileria;

public class Cliente {

    //Atributos
    private String nif;
    private String nombre;
    private String apellidos;

    //Constructores
    public Cliente(String nif, String nombre, String apellidos) {
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Cliente() {
    }

    //Getter y Setter
    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
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

    //Métodos propios
    public void rellenar() {
        System.out.println("\n----- RELLENANDO INFORMACIÓN CLIENTE -----");
        nif = Utileria.pedirString("NIF: ");
        nombre = Utileria.pedirString("Nombre: ");
        apellidos = Utileria.pedirString("Apellidos: ");
    }

    public void mostrar() {
        System.out.println("- " + nombre + " " + apellidos + ", " + nif);
    }
}
