package t04ejercicio26;

import java.util.Scanner;

/**
 *
 * @author wmartinl01
 */
public class T04Ejercicio26 {

    public static int pedirNum() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Por favor, introduzca un numero: ");
        int num = entrada.nextInt();

        return num;
    }

    public static boolean esPrimo(int num) {
        boolean primo = true;
        int i = 2;
        while (i < num && primo == true) {
            if (num % i == 0) {
                primo = false;
            }
            i++;
        }
        return primo;
    }

    public static void main(String[] args) {
        int num = pedirNum();

        if (esPrimo(num)) {
            System.out.println("El numero " + num + " es un numero primo.");
        } else {
            System.out.println("El numero " + num + " no es un numero primo.");
        }
    }

}
