/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t05ejercicio19;

/**
 *
 * @author Walter
 */
public class Empleado {

    private String nombre;
    private int horasTrabajadas;
    private int tarifaPorHora;

    public Empleado() {
        nombre = "";
        horasTrabajadas = 0;
        tarifaPorHora = 0;
    }

    public Empleado(String nombre, int horasTrabajadas, int tarifaPorHora) {
        this.nombre = nombre;
        this.horasTrabajadas = horasTrabajadas;
        this.tarifaPorHora = tarifaPorHora;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public void setTarifaPorHora(int tarifaPorHora) {
        this.tarifaPorHora = tarifaPorHora;
    }

    public String getNombre() {
        return nombre;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public int getTarifaPorHora() {
        return tarifaPorHora;
    }

}
