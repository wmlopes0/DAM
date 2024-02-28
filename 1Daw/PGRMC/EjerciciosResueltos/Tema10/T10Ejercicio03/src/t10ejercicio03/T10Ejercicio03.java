package t10ejercicio03;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class T10Ejercicio03 {

    public static void rellenarLista(ArrayList<Integer> numeros) {
        Scanner entrada = new Scanner(System.in);
        do {
            System.out.print("Introduce un numero: ");
            numeros.add(entrada.nextInt());
        } while (seguir());
    }

    public static boolean seguir() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("¿Desea seguir introduciendo?");
        return entrada.nextLine().equalsIgnoreCase("si");
    }

    public static void mostrarLista(ArrayList<Integer> numeros) {
        for (int i = 0; i < numeros.size(); i++) {
            System.out.println("Posicion " + (i + 1) + ": " + numeros.get(i));
        }
    }

    public static void intercambiarPosicion(ArrayList<Integer> numeros) {
        int aux = numeros.get(1);
        numeros.set(1, numeros.get(3));
        numeros.set(3, aux);
    }

    public static void main(String[] args) {
        ArrayList<Integer> numeros = new ArrayList<Integer>();
        System.out.println("=======RELLENAR NUMEROS=======");
        rellenarLista(numeros);
        System.out.println("=======MOSTRAR NUMEROS=======");
        mostrarLista(numeros);
        System.out.println("Intercambiando la posicion 2 y 4...");
        intercambiarPosicion(numeros);
        System.out.println("=======MOSTRAR NUMEROS=======");
        mostrarLista(numeros);
    }

}
