/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t11ejercicio11;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Alumno implements Comparable<Alumno> {

    private String dni;
    private int expediente;
    private float notaMedia;

    public Alumno() {
        dni = "";
        expediente = 0;
        notaMedia = 0;
    }

    public Alumno(String dni, int expediente, float notaMedia) {
        this.dni = dni;
        this.expediente = expediente;
        this.notaMedia = notaMedia;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setExpediente(int expediente) {
        this.expediente = expediente;
    }

    public void setNotaMedia(float notaMedia) {
        this.notaMedia = notaMedia;
    }

    public String getDni() {
        return dni;
    }

    public int getExpediente() {
        return expediente;
    }

    public float getNotaMedia() {
        return notaMedia;
    }

    //METODOS PROPIOS
    //Rellenar
    public void rellenar() {
        System.out.println("-----ALUMNO-----");
        dni = pedirDni();
        expediente = pedirExpediente();
        notaMedia = pedirNotaMedia();
    }

    private String pedirDni() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce el DNI: ");
        return entrada.nextLine();
    }

    private int pedirExpediente() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce el expediente: ");
        return entrada.nextInt();
    }

    private float pedirNotaMedia() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce la nota media: ");
        return entrada.nextFloat();
    }

    //Mostrar
    public void mostrar() {
        System.out.println("=====ALUMNO=====");
        System.out.println("DNI: " + dni);
        System.out.println("Expediente: " + expediente);
        System.out.println("Nota media: " + notaMedia);
    }

    @Override
    public int compareTo(Alumno t) {
        if (this.expediente > t.expediente) {
            return 1;
        } else if (this.expediente < t.expediente) {
            return -1;
        } else {
            return 0;
        }
    }
}
