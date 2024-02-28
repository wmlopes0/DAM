package t10ejercicio06;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class T10Ejercicio06 {

    public static int mostrarMenu() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("\n====================MENU====================");
        System.out.println("1. Rellenar ventas de coches mensuales.\n"
                + "2. Mostrar las ventas.\n"
                + "3. Mostrar las ventas al revés.\n"
                + "4. Mostrar la suma total de ventas del año.\n"
                + "5. Mostrar las ventas totales de los meses cuyo nombre contenga la letra 'a'.\n"
                + "6. Mostrar el nombre del mes o meses con más ventas.\n"
                + "7. Salir del programa.");
        System.out.println("============================================");

        return entrada.nextInt();
    }

    public static void rellenarVentas(ArrayList<Integer> ventas, String[] mes) {
       Scanner entrada = new Scanner(System.in);
        for (int i = 0; i < mes.length; i++) {
            System.out.println("Introduzca las ventas de "+mes[i]+": ");
            ventas.add(entrada.nextInt());
        }
    }
    
    public static void mostrarVentas(ArrayList<Integer> ventas, String[] mes){
        for (int i = 0; i < ventas.size(); i++) {
            System.out.println(mes[i]+": "+ventas.get(i));
        }
    }

    public static void main(String[] args) {
        /*ArrayList<Integer> ventas = new ArrayList<>();
        String[] mes = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        int opcion;
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    rellenarVentas(ventas, mes);
                    break;
                case 2:
                    mostrarVentas(ventas, mes);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    System.out.println("¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Error.Introduzca una opción valida.");
            }

        } while (opcion != 7);*/
        System.out.println("NO LO TENGO ACABADO NI QUIERO HACERLO.");
    }

}
