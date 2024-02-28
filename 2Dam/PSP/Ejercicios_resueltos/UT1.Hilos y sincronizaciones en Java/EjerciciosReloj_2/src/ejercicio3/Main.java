/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio3;

import ejercicio3.HiloTic;
import ejercicio3.HiloTac;

/**
 *
 * @author Walter
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HiloTic htic = new HiloTic();
        HiloTac htac = new HiloTac();

        Thread hilo1 = new Thread(htic);
        Thread hilo2 = new Thread(htac);
        
        hilo1.start();
        hilo2.start();
    }

}
