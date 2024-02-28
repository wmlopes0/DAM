/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio1;

import java.time.LocalDateTime;

/**
 *
 * @author Walter
 */
public class Hora extends Thread {

    @Override
    public void run() {
        /* código concurrente */
        for (int i = 0; i < 10; i++) {
            System.out.println(LocalDateTime.now().toString());
            try {
                sleep(1000);/* esperar 1000 ms */
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
