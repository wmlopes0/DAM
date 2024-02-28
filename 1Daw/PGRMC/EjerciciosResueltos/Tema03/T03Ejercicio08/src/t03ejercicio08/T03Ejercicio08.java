/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t03ejercicio08;
import java.util.Scanner;
/**
 *
 * @author wmartinl01
 */
public class T03Ejercicio08 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner entrada = new Scanner(System.in);
        
        int cantidad,aux,billete50,billete20,billete10,billete5,moneda2,moneda1;
        
        System.out.print("Por favor, indique una cantidad de dinero: ");
        cantidad = entrada.nextInt();
    
        billete50 = cantidad/50;
        aux = cantidad%50;
        
        billete20 = aux/20;
        aux = aux%20;
        
        billete10 = aux/10;
        aux = aux%10;
        
        billete5 = aux/5; 
        aux = aux%5;
        
        moneda2 = aux/2; 
        
        moneda1 = aux%2;
        
        System.out.println(cantidad+" Euros se descomponen en: ");
        
        if (billete50>0){
            System.out.println("Billetes de 50: "+billete50);
        }
        if (billete20>0){
            System.out.println("Billetes de 20: "+billete20);
        }
        if (billete10>0){
            System.out.println("Billetes de 10: "+billete10);
        }
        if (billete5>0){
            System.out.println("Billetes de 5: "+billete5);
        }
        if (moneda2>0){
            System.out.println("Monedas de 2 euros: "+moneda2);
        }
        if (moneda1>0){
            System.out.println("Monedas de 1 euro: "+moneda1);
        }
        
    }
    
}
