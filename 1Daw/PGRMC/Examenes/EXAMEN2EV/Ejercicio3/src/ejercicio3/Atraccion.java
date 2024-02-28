package ejercicio3;

import java.util.Scanner;

/**
 *
 * @author java2
 */
public class Atraccion {

    private String nombre;
    private Viaje[] viajes;

    public Atraccion() {
        nombre = "";
        viajes = new Viaje[3];
    }

    public Atraccion(String nombre) {
        this.nombre = nombre;
        viajes = new Viaje[3];
    }

    public Atraccion(String nombre, Viaje[] viajes) {
        this.nombre = nombre;
        this.viajes = viajes;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setViajes(Viaje[] viajes) {
        this.viajes = viajes;
    }

    public String getNombre() {
        return nombre;
    }

    public Viaje[] getViajes() {
        return viajes;
    }

    //METODOS PROPIOS DE ATRACCION
    public void rellenarAtraccion() {
        System.out.println("=====ATRACCION=====");
        nombre = pedirNombre();
        rellenarViajes();
    }

    //Estos metodos rellenar son privados porque no me interesa que se puedan llamar desde la clase Test.
    private void rellenarViajes() {
        for (int i = 0; i < viajes.length; i++) {
            viajes[i] = new Viaje();
            viajes[i].rellenarViaje();
        }
    }

    private String pedirNombre() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduzca el nombre: ");
        return entrada.nextLine();
    }

    //-------------------------------------------------------------------------------------
    //Calcula los ingresos totales de la atracion recorriendo el atributo viajes y sumando los ingresos de cada viaje en la variable "ingresos"
    public float ingresos() {
        float ingresos = 0;
        for (Viaje viaje : viajes) {
            ingresos += viaje.getIngresos();
        }
        return ingresos;
    }

    //METODOS MOSTRAR
    //Este metodo es privado porque no me interesa llamarlo desde la clase Test, solo desde aqui
    private void mostrarViajes() {
        for (Viaje viaje : viajes) {
            viaje.mostrarViaje();
        }
    }

    public void mostrarAtraccion() {
        System.out.println("==========" + nombre.toUpperCase() + "==========");
        mostrarViajes();
    }
}
