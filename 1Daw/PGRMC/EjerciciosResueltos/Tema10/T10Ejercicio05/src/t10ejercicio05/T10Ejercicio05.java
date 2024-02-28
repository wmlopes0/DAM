package t10ejercicio05;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class T10Ejercicio05 {

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

    public static int mostrarMayorPar(ArrayList<Integer> numeros) {
        int mayor = numeros.get(0);
        for (Integer numero : numeros) {
            if (numero % 2 == 0 && mayor < numero) {
                mayor = numero;
            }
        }
        return mayor;
    }

    public static int mostrarMenorImpar(ArrayList<Integer> numeros) {
        int menor = numeros.get(0);
        for (Integer numero : numeros) {
            if (numero % 2 != 0 && menor > numero) {
                menor = numero;
            }
        }
        return menor;
    }

    public static void mostrarLista(ArrayList<Integer> numeros) {
        System.out.println("=======LISTA========");
        for (Integer numero : numeros) {
            System.out.println("# " + numero);
        }
    }

    public static int posicionNumero(int num, ArrayList<Integer> numeros) {
        return numeros.indexOf(num);
    }

    public static void intercambiarPosiciones(int num1, int num2, ArrayList<Integer> numeros) {
        int pos1 = posicionNumero(num1, numeros);
        int pos2 = posicionNumero(num2, numeros);
        int aux;
        //Intercambio
        aux = numeros.get(pos1);
        numeros.set(pos1, numeros.get(pos2));
        numeros.set(pos2, aux);
    }

    public static void main(String[] args) {
        int mayorPar, menorImpar;
        ArrayList<Integer> numeros = new ArrayList<>();

        rellenarLista(numeros);
        mostrarLista(numeros);

        mayorPar = mostrarMayorPar(numeros);
        menorImpar = mostrarMenorImpar(numeros);
        System.out.println("Mayor par: " + mayorPar);
        System.out.println("Menor impar: " + menorImpar);

        intercambiarPosiciones(mayorPar, menorImpar, numeros);
        mostrarLista(numeros);
    }

}
