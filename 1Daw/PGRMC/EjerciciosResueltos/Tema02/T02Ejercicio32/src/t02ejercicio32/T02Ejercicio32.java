/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t02ejercicio32;
import java.util.Scanner;
/**
 *
 * @author Walter Martín Lopes
 */
public class T02Ejercicio32 {

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
        
        System.out.println(+cantidad+" Euros se descomponen en "+billete50+" billetes de 50, "+billete20+" billetes de 20, "+billete10+" billetes de 10, "+billete5+" billetes de 5, "+moneda2+" monedas de 2 euros y "+moneda1+" monedas de 1 euro.");
       
    }
    
}
