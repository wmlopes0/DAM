/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio2;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Walter
 */
public class HiloTac extends Thread {

    @Override
    public void run() {
        while (true) {
            try {
                sleep(1000);
                System.out.println("TAC");
                sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloTic.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
