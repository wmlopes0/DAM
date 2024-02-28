package ejercicio1;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Test {

    public static int mostrarMenu() {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        System.out.println("*****************************************************");
        System.out.println("****************** QUEDATE EN CASA ******************");
        System.out.println("*****************************************************");
        System.out.println("1.- Añadir actividad. ");
        System.out.println("2.- Mostrar actividades.");
        System.out.println("3.- Mostrar actividades para toda la familia.");
        System.out.println("4.- Mostrar actividades por tipo.");
        System.out.println("5.- Mostrar numero de actividades.");
        System.out.println("6.- Salir del programa.");
        System.out.println("-----------------------------------------------------");
        System.out.println("Por favor, introduzca una opcion: ");
        try {
            opcion = entrada.nextInt();
        } catch (InputMismatchException e) {
            opcion = -1;
        }
        return opcion;
    }

    public static void main(String[] args) {
        int opcion;
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    System.out.println("********************************");
                    System.out.println("******* AÑADIR ACTIVIDAD *******");
                    System.out.println("********************************");
                    break;
                case 2:
                    System.out.println("********************************");
                    System.out.println("***** MOSTRAR ACTIVIDADES ******");
                    System.out.println("********************************");
                    break;
                case 3:
                    System.out.println("********************************");
                    System.out.println("***** MOSTRAR ACTIVIDADES ******");
                    System.out.println("***** PARA TODA LA FAMILIA *****");
                    System.out.println("********************************");
                    break;
                case 4:
                    System.out.println("*********************************");
                    System.out.println("****** MOSTRAR ACTIVIDADES ******");
                    System.out.println("*********** POR TIPO ***********");
                    System.out.println("*********************************");
                    break;
                case 5:
                    System.out.println("*********************************");
                    System.out.println("***** NUMERO DE ACTIVIDADES *****");
                    System.out.println("*********************************");
                    break;
                case 6:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("ERROR. INTRODUZCA UNA OPCIÓN VALIDA.");
            }

        } while (opcion != 6);
    }
}
