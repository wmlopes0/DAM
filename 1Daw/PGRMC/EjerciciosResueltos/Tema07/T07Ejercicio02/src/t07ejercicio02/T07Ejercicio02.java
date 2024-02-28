package t07ejercicio02;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 *
 * @author Walter
 */
public class T07Ejercicio02 {

    public static void rellenarArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) Math.round(Math.random() * 5 + 1);
        }
    }

    public static void mostrarArray(int[] array) {
        System.out.println("==========ARRAY==========");
        for (int i : array) {
            System.out.println("# " + i);
        }
    }

    public static int pedirLongitud() {
        Scanner entrada = new Scanner(System.in);
        int numero = 0;
        boolean datoCorrecto;
        do {
            datoCorrecto = true;
            System.out.println("");//Salto de linea
            try {
                System.out.println("Por favor, introduzca una longitud del 1 al 10: ");
                numero = entrada.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: ¡No puedes introducir una letra!.");
                entrada.next();//Limpio el buffer
                datoCorrecto = false;
            }
            if (datoCorrecto == true && (numero < 1 || numero > 10)) {
                System.out.println("Error: ¡Debes introducir un numero del 1 al 10!");
                datoCorrecto = false;
            }
        } while (!datoCorrecto);

        return numero;
    }

    public static void main(String[] args) {
        int[] array = new int[pedirLongitud()];
        rellenarArray(array);
        mostrarArray(array);

    }

}
