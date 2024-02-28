/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t05ejercicio03;

/**
 *
 * @author Walter
 */
public class Coche {

    private String marca;
    private String modelo;
    private Rueda ruedas;

    public void setMarca(String mar) {
        marca = mar;
    }

    public void setModelo(String mod) {
        modelo = mod;
    }

    public void setRuedas(Rueda r) {
        ruedas = r;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }
    
    public Rueda getRuedas(){
        return ruedas;
    }
}
