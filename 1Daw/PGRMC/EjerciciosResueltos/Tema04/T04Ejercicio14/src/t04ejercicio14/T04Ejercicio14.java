package t04ejercicio14;

import java.util.Scanner;

/**
 *
 * @author wmartinl01
 */
public class T04Ejercicio14 {

    public static int pedirNum() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Por favor, introduzca un numero mayor que 0: ");
        int num = entrada.nextInt();

        return num;
    }

    public static boolean comprobarNum(int num) {
        boolean mayor = false;
        if (num > 0) {
            mayor = true;
        } else {
            System.out.println("ERROR: El numero introducido debe ser mayor a 0.");
        }
        return mayor;
    }

    public static void mostrarResultado(int num) {
        System.out.println("Los numeros multiplos de 3 existentes entre el numero 1 y el numero " + num + " son:");

        for (int i = 1; i <= num; i++) {
            if (i % 3 == 0) {
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        int num = pedirNum();

        while (comprobarNum(num) != true) {
            num = pedirNum();
        }
        mostrarResultado(num);
    }

}
