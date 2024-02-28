/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t06ejercicio14;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class T06Ejercicio14 {
    
    public static int mostrarMenu() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("\n====================MENU====================");
        System.out.println("1. Rellenar las temperaturas.\n"
                + "2. Mostrar las temperaturas.\n"
                + "3. Visualizar la temperatura media del mes.\n"
                + "4. Día o días más calurosos del mes.\n"
                + "5. Salir del programa.");
        System.out.println("============================================");
        
        return entrada.nextInt();
    }
    
    public static int pedirTemperatura(int semana, int dia) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce la temperatura del dia " + dia + " de la semana " + semana + ": ");
        return entrada.nextInt();
    }
    
    public static void rellenarTemperaturas(int[][] temperaturas) {
        for (int i = 0; i < temperaturas.length; i++) {
            for (int j = 0; j < temperaturas[i].length; j++) {
                //temperaturas[i][j] = pedirTemperatura(i, j);
                temperaturas[i][j] = (int) Math.round(Math.random() * 100);
            }
        }
    }
    
    public static void mostrarTemperaturas(int[][] temperaturas, String[] diaSemana) {
        for (int i = 0; i < temperaturas.length; i++) {
            System.out.println("\n====================SEMANA " + i + "===================");
            for (int j = 0; j < temperaturas[i].length; j++) {
                System.out.println("- " + diaSemana[j] + ": " + temperaturas[i][j] + "º");
            }
        }
    }
    
    
    public static int mediaTemperaturas(int[][] temperaturas) {
        int media = 0;
        for (int i = 0; i < temperaturas.length; i++) {
            for (int j = 0; j < temperaturas[i].length; j++) {
                media += temperaturas[i][j];
            }
        }
        //CALCULAMOS LA MEDIA
        media = media / (temperaturas.length * temperaturas[0].length);
        //RETORNAMOS MEDIA
        return media;
    }
    
    public static void diasMasCalurosos(int[][] temperaturas, String[] diaSemana) {
        System.out.println("El dia o dias mas calurosos fueron:");
        int masCaluroso = temperaturas[0][0];
        
        //RECORRO EL ARRAY PARA BUSCAR EL VALOR MAS ALTO
        for (int i = 0; i < temperaturas.length; i++) {
            for (int j = 0; j < temperaturas[i].length; j++) {
                if (masCaluroso < temperaturas[i][j]) {
                    masCaluroso = temperaturas[i][j];
                }
            }
        }
        
        //VUELVO A RECORRER EL ARRAY PARA MOSTRAR LA INFORMACION
        for (int i = 0; i < temperaturas.length; i++) {
            for (int j = 0; j < temperaturas[i].length; j++) {
                if (masCaluroso == temperaturas[i][j]) {
                    System.out.println(" - El "+diaSemana[j]+" de la semana "+i+" con "+temperaturas[i][j]+"º.");
                }
            }
        }
    }
    
    public static void main(String[] args) {
        //DECLARAMOS ARRAYS NECESARIOS
        int[][] temperaturas = new int[4][7];
        String[] diasSemana = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};

        //DECLARAMOS VARIABLES NECESARIAS
        int opcion;

        //MENU
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    System.out.println("==========RELLENA LAS TEMPERATURAS==========");
                    rellenarTemperaturas(temperaturas);
                    System.out.println("TEMPERATURAS RELLENADAS AUTOMATICAMENTE");
                    break;
                case 2:
                    System.out.println("==========MOSTRANDO LAS TEMPERATURAS==========");
                    mostrarTemperaturas(temperaturas, diasSemana);
                    break;
                case 3:
                    System.out.println("============MEDIA DE TEMPERATURAS============");
                    System.out.print(mediaTemperaturas(temperaturas) + "º\n");
                    break;
                case 4:
                    System.out.println("=============DIAS MAS CALUROSOS==============");
                    diasMasCalurosos(temperaturas, diasSemana);
                    break;
                case 5:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("ERROR: Por favor, introduzca una opcion valida.");
            }
        } while (opcion != 5);
    }
}
