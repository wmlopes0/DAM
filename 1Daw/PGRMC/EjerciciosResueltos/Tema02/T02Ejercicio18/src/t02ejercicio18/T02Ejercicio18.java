/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t02ejercicio18;

/**
 *
 * @author Walter
 */
public class T02Ejercicio18 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int a = 2;
        int b = 4;
        int A = -a+5%b-a*a;
        int B = 5+3%7*b*a-b%a;
        int C = (a+1)*(b+1)-b/a;
        
        System.out.println("RESULTADOS");
        System.out.println("A)"+A);
        System.out.println("B)"+B);
        System.out.println("C)"+C);
        
    }
    
}
