/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t05ampliacion03;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Test {

    static float cuenta;
    static float beneficio;

    public static int mostrarMenu(Plato p1, Plato p2, Plato p3) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Pulse 1 para servir " + p1.getNombre() + " (Precio: " + p1.getPrecio() + " euros - Stock: " + p1.getRacionesDisponibles() + " raciones).");
        System.out.println("Pulse 2 para servir " + p2.getNombre() + " (Precio: " + p2.getPrecio() + " euros - Stock: " + p2.getRacionesDisponibles() + " raciones).");
        System.out.println("Pulse 3 para servir " + p3.getNombre() + " (Precio: " + p3.getPrecio() + " euros - Stock: " + p3.getRacionesDisponibles() + " raciones).");
        return entrada.nextInt();
    }

    public static void pedirRaciones(Plato p) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("¿Cuantas raciones?");
        int raciones = entrada.nextInt();
        if (raciones <= p.getRacionesDisponibles()) {
            //actualizo el valor de la cuenta y del beneficio
            cuenta += raciones * p.getPrecio();
            beneficio += raciones * p.getBeneficioNeto();
            //actualizo raciones disponibles
            p.setRacionesDisponibles(p.getRacionesDisponibles() - raciones);
            System.out.println("Comanda servida correctamente.");
        } else {
            System.out.println("Lo sentimos, actualmente disponemos de " + p.getRacionesDisponibles() + " raciones disponibles.");
        }
    }

    public static boolean seguirSirviendo() {
        Scanner entrada = new Scanner(System.in);

        System.out.println("¿Desea servir mas platos?");
        String eleccion = entrada.nextLine();

        return eleccion.equalsIgnoreCase("si");
    }

    public static void main(String[] args) {
        //Creo 3 Platos distintos
        Plato plato1 = new Plato("Nachos", 8, 35, 5);
        Plato plato2 = new Plato("Burritos", 12, 10, 8);
        Plato plato3 = new Plato("Tacos", 10, 20, 5);

        //Muestro menu
        System.out.println("==========LOS POLLOS HERMANOS==========");

        do {
            switch (mostrarMenu(plato1, plato2, plato3)) {
                case 1:
                    System.out.println("Va a servir Nachos.");
                    pedirRaciones(plato1);
                    break;
                case 2:
                    System.out.println("Va a servir Burritos.");
                    pedirRaciones(plato2);
                    break;
                case 3:
                    System.out.println("Va a servir Tacos.");
                    pedirRaciones(plato3);
                    break;
                default:
                    System.out.println("ERROR: Por favor introduzca una opcion valida.");
            }
        } while (seguirSirviendo());

        System.out.println("============CUENTA============");
        System.out.println("La cuenta asciende a " + cuenta + " euros " + "(Beneficio Neto: " + beneficio + " euros).");
        System.out.println("Muchas gracias.Atentamente, Walter Martin.");
    }
}
