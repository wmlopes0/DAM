/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio05t15;

import java.io.Serializable;

/**
 *
 * @author Quique Pineda
 */
public class Fecha implements Serializable {
    private int dia;
    private int mes;
    private int anio;
    
    public Fecha(){
        this.dia = 1;
        this.mes = 1;
        this.anio = 1901;
    }

    public Fecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    @Override
    public String toString() {
        return "   Fecha Matricula: " + dia + "/" + mes + "/" + anio;
    }

    
    
}
