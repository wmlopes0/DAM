package t04ejercicio11;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class T04Ejercicio11 {

    public static float pedirNumero() {
        Scanner entrada = new Scanner(System.in);
        float num;
        do {
            System.out.println("Por favor, introduzca un numero: ");
            num = entrada.nextFloat();
            if (num < 0) {
                System.out.println("ERROR: El numero introducido no puede ser negativo.");
            }
        } while (num < 0);

        return num;
    }

    public static float calcularRaiz(float num) {
        float raiz = (float) Math.sqrt(num);
        return raiz;
    }

    public static void mostrarResultado(float num, float resultado) {
        System.out.println("La raiz cuadrada de " + num + " es " + resultado);
    }

    public static void main(String[] args) {
        float num = pedirNumero();
        mostrarResultado(num, calcularRaiz(num));
    }

}
