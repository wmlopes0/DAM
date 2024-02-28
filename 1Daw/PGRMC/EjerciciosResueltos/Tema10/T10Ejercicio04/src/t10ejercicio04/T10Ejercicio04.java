/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t10ejercicio04;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class T10Ejercicio04 {

    public static int pedirNumero() {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduce un numero: ");
        return entrada.nextInt();
    }

    public static void rellenarLista(ArrayList<Integer> numeros) {
        int num = pedirNumero();

        while (num >= 0) {
            numeros.add(num);
            num = pedirNumero();
        }
    }

    public static void mostrarLista(ArrayList<Integer> numeros) {
        for (int i = 0; i < numeros.size(); i++) {
            System.out.print(numeros.get(i) + " - ");
        }
    }

    public static void sustituirDuplicados(ArrayList<Integer> numeros) {
        int posComparar = 0;
        boolean repetido;

        for (int i = posComparar; i < numeros.size(); i++) {
            repetido = false;
            for (int j = posComparar + 1; j < numeros.size(); j++) {
                if (numeros.get(posComparar) == numeros.get(j)) {
                    repetido = true;
                    numeros.set(j, 0);
                }
            }
            if (repetido) {
                numeros.set(posComparar, 0);
            }
            posComparar++;
        }

    }

    public static void main(String[] args) {
        ArrayList<Integer> numeros = new ArrayList<Integer>();
        System.out.println("=======RELLENAR NUMEROS=======");
        System.out.println("#Para parar de introducir numeros introduzca un numero negativo.");
        rellenarLista(numeros);
        System.out.println("=======MOSTRANDO NUMEROS=======");
        mostrarLista(numeros);
        System.out.println("\nCAMBIANDO DUPLICADOS POR 0...");
        sustituirDuplicados(numeros);
        mostrarLista(numeros);
    }

}
