package ejercicio1;

import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Empresa implements Serializable {

    private String nombre;
    private int beneficiosAproximados;

    //CONSTRUCTORES
    public Empresa() {
        this.nombre = "";
        this.beneficiosAproximados = 0;
    }

    public Empresa(String nombre, int beneficiosAproximados) {
        this.nombre = nombre;
        this.beneficiosAproximados = beneficiosAproximados;
    }

    //SETTER Y GETTER
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setBeneficiosAproximados(int beneficiosAproximados) {
        this.beneficiosAproximados = beneficiosAproximados;
    }

    public String getNombre() {
        return nombre;
    }

    public int getBeneficiosAproximados() {
        return beneficiosAproximados;
    }

    //MÉTODOS PROPIOS
    public void rellenarInfoEmpresa() {
        System.out.println("=====EMPRESA=====");
        this.nombre = pedirNombre();
        this.beneficiosAproximados = pedirBeneficiosAproximados();
    }

    private String pedirNombre() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduzca el nombre: ");
        return entrada.nextLine();
    }

    private int pedirBeneficiosAproximados() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduzca los beneficios aproximados: ");
        return entrada.nextInt();
    }

    public void mostrarInfoEmpresa() {
        System.out.println("#" + this.nombre);
        System.out.println("Beneficios aproximados: " + this.beneficiosAproximados + " euros.");
    }
}
