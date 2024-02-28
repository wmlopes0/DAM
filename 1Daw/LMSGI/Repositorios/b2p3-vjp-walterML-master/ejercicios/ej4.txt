/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t02ejercicio23;
import java.util.Scanner;
/**
 *
 * @author Walter Martín Lopes
 */
public class T02Ejercicio23 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner entrada = new Scanner (System.in);
        
        float precio;
        int unidades;
        float resultado;
        
        System.out.println("Por favor, introduza el precio del modelo de ordenador que desea comprar: ");
        precio = entrada.nextFloat();
        
        System.out.println("¿Cuántas unidades quiere llevarse?");
        unidades = entrada.nextInt();
        
        resultado = precio*unidades;
        System.out.println("El precio total de su compra es de: "+resultado+" euros.");
    }   
    
}
