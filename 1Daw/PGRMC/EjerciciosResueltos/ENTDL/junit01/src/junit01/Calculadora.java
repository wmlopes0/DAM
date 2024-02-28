/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package junit01;

/**
 *
 * @author Walter
 */
public class Calculadora {
    
    public int suma(int a, int b) {
        return a+b;
    }
    public int resta(int a, int b) {
        return a-b;
    }
    public int multiplicacion(int a, int b) {
        return a*b;
    }
    public int division(int a, int b) {
        return a/b;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Calculadora calc = new Calculadora();
        System.out.println(calc.suma(4,2));
        System.out.println(calc.resta(4,2));
        System.out.println(calc.multiplicacion(4,2));
        System.out.println(calc.division(4,2));
    }
    
}