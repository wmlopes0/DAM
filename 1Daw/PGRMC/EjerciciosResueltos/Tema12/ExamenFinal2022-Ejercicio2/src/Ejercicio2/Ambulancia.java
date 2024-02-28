/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Ambulancia {

    private String matricula;
    private Map<String, Llamada> llamadas;

    //CONSTRUCTORES
    public Ambulancia() {
        matricula = "";
        llamadas = new HashMap<>();
    }

    public Ambulancia(String matricula) {
        this.matricula = matricula;
        this.llamadas = new HashMap<>();
    }

    //SETTER Y GETTER
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setLlamadas(Map<String, Llamada> llamadas) {
        this.llamadas = llamadas;
    }

    public String getMatricula() {
        return matricula;
    }

    public Map<String, Llamada> getLlamadas() {
        return llamadas;
    }

    //METODOS PROPIOS
    public void rellenarInfo() {
        System.out.println("=======AMBULANCIA=======");
        matricula = pedirCadena("la matricula");
    }

    private String pedirCadena(String texto) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce " + texto + ": ");
        return entrada.nextLine();
    }
    
    public void insertarLlamada(String nombre){
        String clave = nombre.substring(0,3);
        Llamada llamada = new Llamada();
        llamada.setNombrePaciente(nombre);
        llamadas.put(clave, llamada);
    }
}
