package t12ejercicio26;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Bebida implements Serializable {

    private String nombre;
    private float precio;
    private int stock;

    public Bebida() {
        nombre = "";
        precio = 0;
        stock = 0;
    }

    public Bebida(String nombre, float precio, int stock) {
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

    public void rellenar() {
        System.out.println("------INTRODUCIENDO BEBIDA------");
        this.nombre = pedirNombre();
        this.precio = pedirPrecio();
        this.stock = pedirStock();
    }

    private String pedirNombre() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce el nombre:");
        return entrada.nextLine();
    }

    private int pedirStock() {
        Scanner entrada = new Scanner(System.in);
        int stockBebidas = 0;
        System.out.println("Introduce el stock:");
        try {
            stockBebidas = entrada.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ERROR.DEBES INTRODUCIR UNA LETRA");
            entrada.next();
            pedirStock();
        }
        return stockBebidas;
    }

    private float pedirPrecio() {
        Scanner entrada = new Scanner(System.in);
        float precioBebida = 0;
        System.out.println("Introduce el precio:");
        try {
            precioBebida = entrada.nextFloat();
        } catch (InputMismatchException e) {
            System.out.println("ERROR.DEBES INTRODUCIR UNA LETRA");
            entrada.next();
            pedirPrecio();
        }
        return precioBebida;
    }
}
