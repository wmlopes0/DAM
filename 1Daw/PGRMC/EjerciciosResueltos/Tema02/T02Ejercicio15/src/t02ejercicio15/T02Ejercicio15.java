/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t02ejercicio15;

/**
 *
 * @author Walter Martín Lopes
 */
public class T02Ejercicio15 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int tiempo = 10000;
        int horas= tiempo/3600;
        int minutos = (tiempo%3600)/60;
        int segundos = tiempo%60;
        
        System.out.println("10.000 segundos hacen un total de: "+horas+" horas, "+minutos+" minutos y "+segundos+ " segundos.");
    }
    
}
