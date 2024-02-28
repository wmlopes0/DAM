/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t02ejercicio19;

/**
 *
 * @author Walter Martín Lopes
 */
public class T02Ejercicio19 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int a = 3, b = 6, c;
        c = a / b;
        System.out.println("El valor de c es: "+c); //1El resultado es 0 porque c es una variable de tipo int, por lo que no muestra decimales.
        
        c = a % b;
        System.out.println("El valor de c es: "+c); //2 En esta operación se muestra el resto de la operacion a/b.
        
        //a++;
       // System.out.println("El valor de a es: "+a); //3En esta operación se muestra el valor de a incrementado.
        
        ++a;
        System.out.println("El valor de a es: "+a); //En esta operación se muestra el valor de a incrementado nuevamente.
        
        c = ++a + b++;
        System.out.println("El valor de a es: "+a); //Se incrementa a.
        System.out.println("El valor de b es: "+b); //Se incrementa b.
        System.out.println("El valor de c es: "+c); //Se muestra la suma de a y b.
        
        c = ++a + ++b;
        System.out.println("El valor de a es: "+a); //Se incrementa a.
        System.out.println("El valor de b es: "+b); //Se incrementa b.
        System.out.println("El valor de c es: "+c); //Se muestra la suma de a y b.
        
        
    
    }
    
}
