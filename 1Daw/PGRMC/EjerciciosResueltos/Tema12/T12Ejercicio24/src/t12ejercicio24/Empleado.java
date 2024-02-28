package t12ejercicio24;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Empleado implements Serializable{

    private String nombre;
    private int horas;
    private float tarifa;

    //CONSTRUCTORES
    public Empleado() {
        this.nombre = "";
        this.horas = 0;
        this.tarifa = 0;
    }

    public Empleado(String nombre, int horas, float tarifa) {
        this.nombre = nombre;
        this.horas = horas;
        this.tarifa = tarifa;
    }

    //SETTER Y GETTER
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public void setTarifa(float tarifa) {
        this.tarifa = tarifa;
    }

    public String getNombre() {
        return nombre;
    }

    public int getHoras() {
        return horas;
    }

    public float getTarifa() {
        return tarifa;
    }

    //METODOS PROPIOS
    public void rellenar() {
        System.out.println("------EMPLEADO------");
        this.nombre = pedirNombre();
        this.horas = pedirHoras();
        this.tarifa = pedirTarifa();
    }

    private String pedirNombre() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce el nombre:");
        return entrada.nextLine();
    }

    private int pedirHoras() {
        Scanner entrada = new Scanner(System.in);
        int horasTrabajadas = 0;
        System.out.println("Introduce las horas:");
        try {
            horasTrabajadas = entrada.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ERROR.DEBES INTRODUCIR UNA LETRA");
            entrada.next();
            pedirHoras();
        }
        return horasTrabajadas;
    }

    private float pedirTarifa() {
        Scanner entrada = new Scanner(System.in);
        float tarifaHora = 0;
        System.out.println("Introduce la tarifa:");
        try {
            tarifaHora = entrada.nextFloat();
        } catch (InputMismatchException e) {
            System.out.println("ERROR.DEBES INTRODUCIR UNA LETRA");
            entrada.next();
            pedirHoras();
        }
        return tarifaHora;
    }

    //Calcular sueldo bruto
    private float calcularSueldo() {
        float sueldo = 0;
        if (this.horas < 40) {
            sueldo = sueldo * tarifa;
        } else {
            sueldo = horas*(tarifa + (tarifa / 2));
        }
        return sueldo;
    }
    
    //Mostrar
    public void mostrar(){
        System.out.println("====="+this.nombre+"=====");
        System.out.println("Horas: "+this.horas);
        System.out.println("Tarifa: "+this.tarifa);
        System.out.println("Sueldo: "+calcularSueldo());
    }
}

