/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t07ejercicio06;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Test {

    public static int pedirLongitud() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("# ¿Cuantos empleados desea introducir?");
        return entrada.nextInt();
    }

    public static void rellenarEmpleados(Empleado[] empleados) {
        Scanner nombre = new Scanner(System.in);
        Scanner entrada = new Scanner(System.in);
        for (int i = 0; i < empleados.length; i++) {
            System.out.println("\n    --EMPLEADO " + (i + 1) + "--    ");
            empleados[i] = new Empleado();
            System.out.print("Introduzca el nombre del empleado: ");
            empleados[i].setNombre(nombre.nextLine());
            System.out.print("¿Cuantas horas trabajo este mes? ");
            empleados[i].setHorasTrabajadas(entrada.nextInt());
            System.out.print("¿Cual es su tarifa por hora de trabajo? ");
            empleados[i].setTarifaHora(entrada.nextInt());
            System.out.println("-EMPLEADO " + (i + 1) + " ALMACENADO CON EXITO-");
        }
    }

    public static float calcularSB(int horas, float tarifa) {
        float sb = 0;
        if (horas > 40) {
            sb = (40 * tarifa) + ((horas - 40) * (tarifa * (float) 1.5));
        } else {
            sb = horas * tarifa;
        }
        return sb;
    }

    public static void mostrarSueldoBruto(Empleado[] empleados) {
        System.out.println("\n    --SUELDO BRUTO DE LOS EMPLEADOS--    ");
        for (Empleado emp : empleados) {
            System.out.println(emp.getNombre() + " trabajo " + emp.getHorasTrabajadas() + " horas, cobra " + emp.getTarifaHora() + " euros la hora por lo que le corresponde un sueldo de " + calcularSB(emp.getHorasTrabajadas(), emp.getTarifaHora())+" euros.");
        }
    }

    public static void main(String[] args) {
        System.out.println("===============BIENVENIDO AL PROGRAMA DE GESTION DE EMPLEADOS===============");
        Empleado[] empleados = new Empleado[pedirLongitud()];
        rellenarEmpleados(empleados);
        mostrarSueldoBruto(empleados);
    }
}
