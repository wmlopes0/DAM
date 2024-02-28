/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t02ejercicio28;
import java.util.Scanner;
/**
 *
 * @author Walter Martin Lopes
 */
public class T02Ejercicio28 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int tamanoArchivo,velocidad,tiempo,horas,minutos,segundos;
        
        System.out.print("Por favor, introduzca el tamaño de un archivo en MB: ");
        tamanoArchivo = entrada.nextInt();
        
        System.out.print("Introduzca la velocidad de tu ADSL en MB: ");
        velocidad = entrada.nextInt();
        
        tiempo=tamanoArchivo/velocidad;
        horas = tiempo/3600;
        minutos = (tiempo%3600)/60;
        segundos = tiempo%60;
        
        System.out.println("El tiempo de descarga es "+horas+":"+minutos+":"+segundos);
        
        
    }
    
}
