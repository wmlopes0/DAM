/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio4;

/**
 *
 * @author wmartinl01
 */
public class Main {

    public static void main(String[] args) {
//Crea dos clases (hilos) Java que implementen la interfaz Runnable. Uno de los hilos debe
//visualizar en pantalla 100 veces la palabra HOLA y el otro hilo la palabra ADIÓS. ¿Intuyes el resultado
//que se visualizará?      
        Hola hola = new Hola();
        Adios adios = new Adios();

        Thread hilo1 = new Thread(hola);
        Thread hilo2 = new Thread(adios);

        hilo1.start();
        hilo2.start();

    }
}
