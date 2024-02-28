package t04ejercicio13;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class T04Ejercicio13 {
    
    public static int pedirNum() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Por favor, introduzca un numero mayor que 1: ");
        int num = entrada.nextInt();
        
        return num;
    }
    
    public static boolean comprobarNum(int num) {
        boolean mayor = false;
        if (num > 1) {
            mayor = true;
        } else {
            System.out.println("ERROR: El numero introducido debe ser mayor a 1.");
        }
        return mayor;
    }
    
    public static void mostrarResultado(int num) {
        System.out.println("Los numeros existentes entre el numero 1 y el numero " + num + " son:");
        
        for (int i = 1; i <= num; i++) {
            System.out.println(i);
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
