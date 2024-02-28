package Ejercicio1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utileria {

    //Método para pedir al usuario una cadena
    public static String pedirCadena(String texto) {
        Scanner entrada = new Scanner(System.in);
        System.out.println(texto);
        return entrada.nextLine();
    }

    //Método para pedir al usuario un año
    public static int pedirAnio(String texto) {
        Scanner entrada = new Scanner(System.in);
        int anio;
        System.out.println(texto);
        try {
            anio = entrada.nextInt();
            //Controlo que el año se encuentre comprendido entre 1970 y 2024
            if (anio < 1970 || anio > 2024) {
                System.out.println("ERROR: Introduzca un año válido comprendido entre 1970 y 2024.");
                anio = pedirAnio(texto);
            }
        } catch (InputMismatchException e) {
            System.out.println("ERROR: No puedes introducir carácteres.");
            anio = pedirAnio(texto);
        }
        return anio;
    }


    //Método para pedir al usuario una matrícula
    public static String pedirMatricula(String texto) {
        Scanner entrada = new Scanner(System.in);
        char[] matricula;
        System.out.println(texto);
        matricula = entrada.nextLine().toCharArray();

        //Hago las comprobaciones pertinentes, la matrícula debe comenzar por 4 números y terminar con 1 a 3 letras
        //Si la matrícula no tiene la longitud deseada, mínimo 5 caráteres y máximo 7
        if (matricula.length < 5 || matricula.length > 7) {
            System.out.println("ERROR: La matrícula debe estar compuesta por 4 numeros y de 1 a 3 letras. (Ej.8479HGY)");
            matricula = pedirMatricula(texto).toCharArray();
        }
        //Si la matrícula no tiene 4 números
        if (!comprobar4numeros(matricula)) {
            System.out.println("ERROR: La matrícula debe estar compuesta por 4 numeros y de 1 a 3 letras. (Ej.8479HGY)");
            matricula = pedirMatricula(texto).toCharArray();
        }
        return matricula.toString();
    }

    //Método para preguntar si desea seguir
    public static boolean seguir(String texto) {
        Scanner entrada = new Scanner(System.in);
        System.out.println(texto);
        return entrada.nextLine().equalsIgnoreCase("si");
    }

    //Método que comprueba que la matrícula tenga 4 números
    private static boolean comprobar4numeros(char[] matricula) {
        int contador = 0;
        for (char c : matricula) {
            if (Character.isDigit(c)) {
                contador++;
            }
        }
        return contador == 4;
    }
}
