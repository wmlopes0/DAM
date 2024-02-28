package t10ejercicio01;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class T10Ejercicio01 {

    public static void rellenarLista(ArrayList<String> nombres) {
        Scanner entrada = new Scanner(System.in);
        do {
            System.out.print("Introduce un nombre: ");
            nombres.add(entrada.nextLine());
        } while (seguir());
    }

    public static boolean seguir() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("¿Desea seguir introduciendo?");
        return entrada.nextLine().equalsIgnoreCase("si");
    }

    public static void mostrarLista(ArrayList<String> nombres) {
        for (int i = 0; i < nombres.size(); i++) {
            System.out.println(nombres.get(i));
        }
    }

    public static void main(String[] args) {
        ArrayList<String> nombres = new ArrayList<String>();
        System.out.println("=======RELLENAR NOMBRES=======");
        rellenarLista(nombres);
        System.out.println("=======MOSTRAR NOMBRES=======");
        mostrarLista(nombres);
    }

}
