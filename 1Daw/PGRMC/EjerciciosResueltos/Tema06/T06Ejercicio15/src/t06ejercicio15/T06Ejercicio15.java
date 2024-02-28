/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t06ejercicio15;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class T06Ejercicio15 {

    public static int mostrarMenu() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("\n====================MENU====================");
        System.out.println("1. Rellenar ventas de coches mensuales.\n"
                + "2. Mostrar las ventas.\n"
                + "3. Mostrar las ventas al revés.\n"
                + "4. Mostrar la suma total de ventas del año.\n"
                + "5. Mostrar las ventas totales de los meses pares.\n"
                + "6. Mostrar el nombre del mes con más ventas.\n"
                + "7. Salir del programa.");
        System.out.println("============================================");

        return entrada.nextInt();
    }

    public static void rellenarVentas(int[] ventas) {
        for (int i = 0; i < ventas.length; i++) {
            ventas[i] = (int) Math.round(Math.random() * 100 + 10);
        }
    }

    public static void mostrarVentas(int[] ventas, String[] meses) {
        for (int i = 0; i < ventas.length; i++) {
            System.out.println("- " + meses[i] + ": " + ventas[i]);
        }
    }

    public static void mostrarVentasReves(int[] ventas, String[] meses) {
        for (int i = (ventas.length - 1); i >= 0; i--) {
            System.out.println("- " + meses[i] + ": " + ventas[i]);
        }
    }

    public static String ventasTotales(int[] ventas) {
        int sumaVentas = 0;
        for (int i = 0; i < ventas.length; i++) {
            sumaVentas += ventas[i];
        }
        return "Ventas totales: "+sumaVentas;
    }

    public static String ventasMesesPares(int[] ventas, String[] meses) {
        int sumaVentas = 0;
        for (int i = 0; i < ventas.length; i++) {
            if (i % 2 != 0) {
                System.out.println("- " + meses[i] + ": " + ventas[i]);
                sumaVentas += ventas[i];
            }
        }
        return "\nVentas totales: "+sumaVentas;
    }

    public static void mesMasVentas(int[] ventas, String[] meses) {
        int mesMasVentas = ventas[0];

        //RECORRO EL ARRAY PARA BUSCAR EL VALOR MAS ALTO
        for (int i = 0; i < ventas.length; i++) {
            if (mesMasVentas < ventas[i]) {
                mesMasVentas = ventas[i];
            }
        }

        //VUELVO A RECORRER EL ARRAY PARA BUSCAR LA POSICION
        System.out.println("El mes con mas ventas ha sido: ");
        for (int i = 0; i < ventas.length; i++) {
            if (mesMasVentas == ventas[i]) {
                System.out.println("- " + meses[i]);
            }
        }
    }

    public static void main(String[] args) {
        //DECLARAMOS ARRAYS NECESARIOS
        int[] ventas = new int[12];
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        //DECLARAMOS VARIABLES NECESARIAS
        int opcion;

        //MENU
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    System.out.println("==========RELLENANDO VENTAS==========");
                    rellenarVentas(ventas);
                    System.out.println("Ventas rellenadas automaticamente");
                    break;
                case 2:
                    System.out.println("===========MOSTRANDO VENTAS==========");
                    mostrarVentas(ventas, meses);
                    break;
                case 3:
                    System.out.println("======MOSTRANDO VENTAS AL REVES======");
                    mostrarVentasReves(ventas, meses);
                    break;
                case 4:
                    System.out.println("======MOSTRANDO VENTAS TOTALES=======");
                    System.out.println(ventasTotales(ventas));
                    break;
                case 5:
                    System.out.println("===MOSTRANDO VENTAS DE MESES PARES===");
                    System.out.println(ventasMesesPares(ventas, meses));
                    break;
                case 6:
                    System.out.println("====MOSTRANDO MES CON MAS VENTAS=====");
                    mesMasVentas(ventas, meses);
                    break;
                case 7:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("ERROR: Por favor, introduzca una opcion valida.");
            }
        } while (opcion != 7);
    }
}
