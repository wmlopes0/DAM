package t04ejercicio21;

import java.util.Scanner;

/**
 *
 * @author wmartinl01
 */
public class T04Ejercicio21 {

    public static int pedirNum1() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Por favor, introduzca el primer numero: ");
        int num1 = entrada.nextInt();

        return num1;
    }

    public static int pedirNum2(int num1) {
        Scanner entrada = new Scanner(System.in);
        int num2;
        do {
            System.out.println("Por favor, introduzca el segundo numero: ");
            num2 = entrada.nextInt();
            if (num2 < num1) {
                System.out.println("ERROR: El numero introducido debe ser mayor al primero.");
            }
        } while (num2 < num1);

        return num2;
    }

    public static void mostrarResultado(int num1, int num2) {
        int suma = 0;
        for (int i = num1; i <= num2; i++) {
            if (i % 2 == 0) {
                suma += i;
            }
        }
        System.out.println("La suma total de todos los números pares comprendidos entre ambos es: " + suma);
    }

    public static void main(String[] args) {
        int num1, num2;

        num1 = pedirNum1();
        num2 = pedirNum2(num1);

        mostrarResultado(num1, num2);
    }

}
