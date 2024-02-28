package javaapplication151;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class JavaApplication151 {

    public static void retornarException() throws ExceptionMenorEdad2, ExceptionMenorEdad3 {
        retornarException3();
        throw new ExceptionMenorEdad2("LA PERSONA ES MENOR DE EDAD");
    }

    public static void retornarException3() throws ExceptionMenorEdad3 {
        throw new ExceptionMenorEdad3("LA PERSONA ES MENOR DE EDAD");
    }

    public static int pedirEdad() throws Exception {
        Scanner entrada = new Scanner(System.in);
        int edad;
        System.out.println("METE ALGO :");
        edad = entrada.nextInt();

        if (edad >= 18) {
            return edad;
        } else {
            retornarException();
        }
        return edad;
    }

    public static void main(String[] args){
        int edad = 0;
        try {
            edad = pedirEdad();
        } catch (InputMismatchException e) {
            System.out.println("EERRORRRRRRRRRRRRR");
        } catch (Exception e){
            System.out.println("No se permiten menores de edad, porfavor introduzca de nuevo la edad");
            try {
                pedirEdad();
            } catch (Exception c) {
                System.out.println("ERROR");
            } 
        }
    }

}
