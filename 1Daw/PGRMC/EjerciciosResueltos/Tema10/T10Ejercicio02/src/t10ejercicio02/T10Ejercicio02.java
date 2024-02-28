package t10ejercicio02;

import java.util.ArrayList;
import java.util.Scanner;

public class T10Ejercicio02 {

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

    public static int mostrarMayor(ArrayList<Integer> numeros) {
        int mayor = numeros.get(0);
        for (int i = 0; i < numeros.size(); i++) {
            if (mayor < numeros.get(i)) {
                mayor = numeros.get(i);
            }
        }
        return mayor;
    }

    public static int mostrarMenor(ArrayList<Integer> numeros) {
        int menor = numeros.get(0);
        for (int i = 0; i < numeros.size(); i++) {
            if (menor > numeros.get(i)) {
                menor = numeros.get(i);
            }
        }
        return menor;
    }

    public static int mostrarSuma(ArrayList<Integer> numeros) {
        int suma = 0;
        for (int i = 0; i < numeros.size(); i++) {
            suma += numeros.get(i);
        }
        return suma;
    }

    public static void main(String[] args) {
        ArrayList<Integer> numeros = new ArrayList<>();
        System.out.println("=======RELLENAR NUMEROS=======");
        System.out.println("#Para parar de introducir numeros introduzca un numero negativo.");
        rellenarLista(numeros);
        System.out.println("=======MOSTRAR RESULTADO=======");
        System.out.println("El mayor es: " + mostrarMayor(numeros));
        System.out.println("El menor es: " + mostrarMenor(numeros));
        System.out.println("La suma es: " + mostrarSuma(numeros));
    }

}
