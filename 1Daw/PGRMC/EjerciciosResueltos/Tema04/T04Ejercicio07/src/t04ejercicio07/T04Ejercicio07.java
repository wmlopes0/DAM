/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t04ejercicio07;

import java.util.Scanner;

/**
 *
 * @author wmartinl01
 */
public class T04Ejercicio07 {

   public static void ordenar(int num1, int num2, int num3, int num4){
       int contador,aux;
       
       for (contador = 3; contador >= 0; contador--) {
            if (num1 > num2) {
                aux = num1;
                num1 = num2;
                num2 = aux;
            }
            if (num2 > num3) {
                aux = num2;
                num2 = num3;
                num3 = aux;
            }
            if (num3 > num4) {
                aux = num3;
                num3 = num4;
                num4 = aux;
            }
        }
       
       System.out.println("El orden de los números introducidos es el: "+num1+"-"+num2+"-"+num3+"-"+num4);
   }
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int num1, num2, num3, num4;

        System.out.println("Por favor, introduzca el primer numero: ");
        num1 = entrada.nextInt();

        System.out.println("Ahora, introduzca el segundo numero: ");
        num2 = entrada.nextInt();

        System.out.println("A continuacion, introduzca el tercer numero: ");
        num3 = entrada.nextInt();

        System.out.println("Finalmente, introduzca el cuarto numero: ");
        num4 = entrada.nextInt();
        
        ordenar(num1, num2, num3, num4);
    }

}
