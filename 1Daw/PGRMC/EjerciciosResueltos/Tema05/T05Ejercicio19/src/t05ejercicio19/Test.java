/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t05ejercicio19;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Test {

    public static String pedirNombre() {
        Scanner entradaNom = new Scanner(System.in);

        System.out.println("Por favor, introduzca el nombre del empleado: ");
        return entradaNom.nextLine();
    }

    public static int pedirHorasTrabajadas() {
        Scanner entradaH = new Scanner(System.in);

        System.out.println("Ahora,introduzca las horas trabajadas: ");
        return entradaH.nextInt();
    }

    public static int pedirTarifaPorHora() {
        Scanner entrada = new Scanner(System.in);

        System.out.println("Por ultimo,introduzca la tarifa por hora: ");
        return entrada.nextInt();
    }

    public static void mostrarSueldoBruto(Empleado empleado) {
        //Vuelco el valor de los atributos a variables 
        int horasTrabajadas = empleado.getHorasTrabajadas();
        int tarifaHora = empleado.getTarifaPorHora();
        double tarifaHoraExtra = tarifaHora * 1.5;
        double sueldoBruto;
        
        //Calculo
        if (horasTrabajadas <= 40) {
            sueldoBruto = horasTrabajadas * tarifaHora;
        } else {
            sueldoBruto = (40 * tarifaHora) + ((horasTrabajadas - 40) * tarifaHoraExtra);
        }
        
        //Muestro resultado
        System.out.println("- "+empleado.getNombre() + " trabajo " + horasTrabajadas + " horas, cobra " + tarifaHora + " euros la hora por lo que le corresponde un sueldo de " + Math.round(sueldoBruto) + " euros.");

    }

    public static void main(String[] args) {
        //Creo 3 Empleados
        Empleado empleado1 = new Empleado(pedirNombre(), pedirHorasTrabajadas(), pedirTarifaPorHora());
        Empleado empleado2 = new Empleado("Nacho", 25, 27);
        Empleado empleado3 = new Empleado("Hugo", 40, 10);

        //Muestro el sueldo bruto
        mostrarSueldoBruto(empleado1);
        mostrarSueldoBruto(empleado2);
        mostrarSueldoBruto(empleado3);
    }
}
