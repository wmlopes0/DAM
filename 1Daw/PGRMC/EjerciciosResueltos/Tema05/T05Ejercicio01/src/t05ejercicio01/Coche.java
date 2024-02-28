/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t05ejercicio01;

/**
 *
 * @author Walter
 */
public class Coche {

    private String marca;
    private String modelo;
    private String color;
    private int velocidad;
    private boolean motorEncendido;

    public void setMarca(String mar) {
        marca = mar;
    }

    public void setModelo(String mod) {
        modelo = mod;
    }

    public void setColor(String col) {
        color = col;
    }
    
    public void setMotorEncendido() {
        motorEncendido = false;
    }

    public void arrancarCoche() {
        motorEncendido = true;
        velocidad = 10;
    }

    public void apagarCoche() {
        motorEncendido = false;
        velocidad = 0;
    }
    
    public void acelerarCoche(){
        velocidad += 20;
    }
    
    public void frenarCoche(){
        velocidad -= 6;
    }
    
    public void getEstado(){
        System.out.println("----------ESTADO----------");
        System.out.println("Marca: "+marca);
        System.out.println("Modelo: "+modelo);
        System.out.println("Color: "+color);
        System.out.println("Velocidad: "+velocidad);
        System.out.println("MotorEncendido: "+motorEncendido);
    }
}
