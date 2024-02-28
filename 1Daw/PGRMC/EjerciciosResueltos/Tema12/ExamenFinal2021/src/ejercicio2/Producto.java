package ejercicio2;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Producto {

    private String nombre;
    private float precio;
    private int stock;

    //CONSTRUCTORES
    public Producto() {
        this.nombre = "";
        this.precio = 0;
        this.stock = 0;
    }

    public Producto(String nombre, float precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    //SETTER Y GETTER
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    //METODOS PROPIOS
    public void rellenarInfoProducto() {
        System.out.println("=====PRODUCTO=====");
        this.nombre = pedirNombre();
        this.precio = pedirPrecio();
        this.stock = pedirStock();
    }

    private String pedirNombre() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduzca el nombre: ");
        return entrada.nextLine();
    }

    private int pedirStock() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduzca el stock: ");
        return entrada.nextInt();
    }

    private float pedirPrecio() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduzca el precio: ");
        return entrada.nextFloat();
    }

    public void mostrarInfoProducto() {
        System.out.println("Nombre: " + nombre + ".");
        System.out.println("Precio: " + precio + ".");
        System.out.println("Stock: " + stock + ".");
    }
}
