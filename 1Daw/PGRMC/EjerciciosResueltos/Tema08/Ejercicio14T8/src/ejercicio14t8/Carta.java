/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio14t8;

/**
 *
 * @author admin
 */
public class Carta {
    private String valor;
    private String palo;
    private int puntos;

    /* Constructor por defecto */
    Carta(){
        this.valor = "";
        this.palo = "";
        this.puntos = 0;
    }
    
    /* Constructor parametrizado */
    Carta(String valor, String palo, int puntos){
        this.valor = valor;
        this.palo = palo;
        this.puntos = puntos;
    }
    
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getPalo() {
        return palo;
    }

    public void setPalo(String palo) {
        this.palo = palo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    
    public void mostrarCarta(String cadena){
        System.out.println(cadena+this.valor+" de "+this.palo);
    }
}
