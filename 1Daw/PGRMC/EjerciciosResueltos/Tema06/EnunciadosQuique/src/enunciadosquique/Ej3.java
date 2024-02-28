package enunciadosquique;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Ej3 {

    public static int pedirNumero() {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduzca un numero: ");
        return entrada.nextInt();
    }

    public static void rellenarArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) Math.round(Math.random() * 299 + 1);
        }
    }

    public static void busquedaArray(int[] array, int num) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 10 == num) {
                System.out.println("Posicion " + (i + 1) + ": " + array[i]);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=======ELIGE LA LONGITUD DEL ARRAY=======");
        int[] array = new int[pedirNumero()];
        rellenarArray(array);
        System.out.println("RELLENANDO ARRAY...");
        System.out.println("========BUSQUEDA POR TERMINACION=========");
        busquedaArray(array, pedirNumero());
    }
}
