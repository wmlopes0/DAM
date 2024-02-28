/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio06t15;

import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Coche implements Serializable {
    private String matricula;
    private String modelo;
    private Rueda[] vRuedas;

    public Coche() {
        this.matricula = "";
        this.modelo = "";
        this.vRuedas = new Rueda[4];
        for(int i = 0;i < vRuedas.length;i++){
            this.vRuedas[i] = new Rueda();
        }
    }
    
    public Coche(String matricula, String modelo) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.vRuedas = new Rueda[4];
        for(int i = 0;i < vRuedas.length;i++){
            this.vRuedas[i] = new Rueda();
        }
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    //Pide y retorna el material
    public static String pedirMaterial(){
        Scanner teclado = new Scanner(System.in);
        String material;
        System.out.print("Material: ");
        material = teclado.nextLine();
        return material;
    }
    
    //Pide y retorna las pulgadas
    public static int pedirPulgadas(){
        Scanner teclado = new Scanner(System.in);
        int pulgadas;
        System.out.print("Pulgadas: ");
        pulgadas = teclado.nextInt();
        return pulgadas;
    }
    
    //Actualiza el valor de todas las ruedas del vector
    public void rellenarRuedas(){
        String material;
        int pulgadas;
        for(int i = 0;i < vRuedas.length;i++){
            pulgadas = pedirPulgadas();
            this.vRuedas[i].setPulgadas(pulgadas);
            material = pedirMaterial();
            this.vRuedas[i].setMaterial(material);
        }
    }

    public void mostrarCoche() {
        System.out.println("Matrícula: " + matricula);
        System.out.println("Modelo: "+ modelo);
        for(int i = 0;i < vRuedas.length;i++){
            this.vRuedas[i].mostrarRueda();
        }
    }
    
    
    
}
