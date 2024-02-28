/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t05ejercicio02;

/**
 *
 * @author Walter
 */
public class Curso {

    private String nombre;
    private int numeroHoras;
    private static int numeroDeCursos;

    public void setNombreyHoras(String nom, int h) {
        nombre = nom;
        numeroHoras = h;
    }

    public String getNombre() {
        return nombre;
    }

    public int getHoras() {
        return numeroHoras;
    }

    public static void sumarCursos() {
        numeroDeCursos++;
    }

    public static void verNumeroCursos() {
        System.out.println("EL NUMERO TOTAL DE CURSOS ES: "+numeroDeCursos);
    }
}
