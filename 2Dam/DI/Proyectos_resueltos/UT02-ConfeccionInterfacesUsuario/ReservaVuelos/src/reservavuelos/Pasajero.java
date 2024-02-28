package reservavuelos;

/**
 *
 * @author Walter
 */

public class Pasajero {

    private String nombre;
    private int pasaporte;
    private int edad;

    //Constructores
    public Pasajero() {
        nombre = "";
        pasaporte = 0;
        edad = 0;
    }

    public Pasajero(String nombre, int pasaporte, int edad) {
        this.nombre = nombre;
        this.pasaporte = pasaporte;
        this.edad = edad;
    }

    //Getter y Setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(int pasaporte) {
        this.pasaporte = pasaporte;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    //Métodos propios
    public void rellenarPasajero() {
        System.out.println("******************************");
        System.out.println("** INTRODUCE LA INFORMACIÓN **");
        System.out.println("******** DEL PASAJERO ********");
        System.out.println("******************************");
        this.nombre = Utilidades.pedirCadena("Nombre: ");
        this.pasaporte = Utilidades.pedirEntero("Pasaporte: ");
        this.edad = Utilidades.pedirEntero("Edad: ");
    }

    
}
