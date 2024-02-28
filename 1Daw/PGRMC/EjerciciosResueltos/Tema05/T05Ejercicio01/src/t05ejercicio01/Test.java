/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t05ejercicio01;

/**
 *
 * @author Walter
 */
public class Test {
    public static void main(String[] args) {
        Coche miCoche = new Coche();
        Coche cochePadre = new Coche();
        
        miCoche.setMarca("Mercedes");
        miCoche.setModelo("AMG");
        miCoche.setColor("Morado");
        
        cochePadre.setMarca("Seat");
        cochePadre.setModelo("Ibiza");
        cochePadre.setColor("Amarillo");
        
        miCoche.arrancarCoche();
        cochePadre.arrancarCoche();
        
        for (int i = 1; i <= 5; i++) {
            miCoche.acelerarCoche();
        }
        
        miCoche.frenarCoche();
        miCoche.frenarCoche();
        
        for (int i = 1; i <= 3; i++) {
            cochePadre.acelerarCoche();
        }
        
        cochePadre.apagarCoche();
        
        miCoche.getEstado();
        cochePadre.getEstado();
    }
}
