/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Walter
 */
public class HiloTic extends Thread {

    @Override
    public void run() {
        while (true) {
            try {
                sleep(500);
                System.out.println("TIC");
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloTic.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
