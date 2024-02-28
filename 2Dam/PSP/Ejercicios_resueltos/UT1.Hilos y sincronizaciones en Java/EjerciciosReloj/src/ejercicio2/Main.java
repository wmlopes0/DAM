/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio2;

import ejercicio2.HiloTic;
import ejercicio2.HiloTac;

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

        htic.start();
        htac.start();
    }

}
