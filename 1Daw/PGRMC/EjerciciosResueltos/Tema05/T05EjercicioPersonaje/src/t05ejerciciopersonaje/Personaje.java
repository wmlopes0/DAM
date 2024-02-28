/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t05ejerciciopersonaje;

/**
 *
 * @author Walter
 */
public class Personaje {

    private String nombre;
    private int fuerza;
    private int nivel;

    public void setNombre(String nom) {
        nombre = nom;
    }

    public void setFuerza(int f) {
        fuerza = f;
    }

    public void setNivel(int n) {
        nivel = n;
    }

    public String getNombre() {
        return nombre;
    }

    public int getFuerza() {
        return fuerza;
    }

    public int getNivel() {
        return nivel;
    }
}
