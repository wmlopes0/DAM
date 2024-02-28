package ejercicio1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Nave implements Serializable {

    private String nombre;
    private int metrosCuadrados;
    private List<Empresa> empresas;

    //CONSTRUCTORES
    public Nave() {
        this.nombre = "";
        this.metrosCuadrados = 0;
        this.empresas = new ArrayList<>();
    }

    public Nave(String nombre, int metrosCuadrados) {
        this.nombre = nombre;
        this.metrosCuadrados = metrosCuadrados;
        this.empresas = new ArrayList<>();
    }

    //SETTER Y GETTER
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMetrosCuadrados(int metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public String getNombre() {
        return nombre;
    }

    public int getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    //MÉTODOS PROPIOS
    public void rellenarInfoNave() {
        System.out.println("=====NAVE=====");
        this.nombre = pedirNombre();
        this.metrosCuadrados = pedirMetrosCuadrados();
    }

    private String pedirNombre() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduzca el nombre: ");
        return entrada.nextLine();
    }

    private int pedirMetrosCuadrados() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduzca los metros cuadrados: ");
        return entrada.nextInt();
    }

    public void mostrarInfoNave() {
        System.out.println("********************");
        System.out.println("********NAVE********");
        System.out.println("********************");
        System.out.println("Nombre: " + nombre + ".");
        System.out.println("Metros cuadrados: " + metrosCuadrados + ".");
        System.out.println("------EMPRESAS------");
        for (Empresa empresa : empresas) {
            empresa.mostrarInfoEmpresa();
        }
    }
}
