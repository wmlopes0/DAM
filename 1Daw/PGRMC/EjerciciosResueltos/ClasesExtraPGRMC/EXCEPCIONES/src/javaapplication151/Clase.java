/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication151;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Clase {

    public static int pedirEdad() throws InputMismatchException{
        Scanner entrada = new Scanner(System.in);
        System.out.println("METE ALGO :");
        return entrada.nextInt();
    } 

    public static void main(String[] args) {
        int edad;
        try {
            edad = pedirEdad();
        } catch (InputMismatchException e) {
            System.out.println("INTRODUCE UN NUMERO");
        }
    }
}
