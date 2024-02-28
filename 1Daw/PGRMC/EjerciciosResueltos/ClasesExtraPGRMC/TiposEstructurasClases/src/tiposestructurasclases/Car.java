/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tiposestructurasclases;

/**
 *
 * @author Walter
 */
class Car {
    
    public static int numeroDeRuedas = 4;
    
    private String color; 
    
    
     public Car() {
    }
    
    public Car(String color) {
        this.color = color;
    }
   
    
    public void setColor(final String color) {  
        this.color = color;
    } 
    
    public static String getColor() {
        return "Rojo";
    }
    
}



