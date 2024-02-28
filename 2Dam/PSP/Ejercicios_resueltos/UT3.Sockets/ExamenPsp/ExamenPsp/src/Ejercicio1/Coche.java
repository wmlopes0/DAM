package Ejercicio1;

import java.io.Serializable;

public class Coche implements Serializable {

    //Atributos
    private String matricula;
    private String marca;
    private String modelo;
    private int anioMatriculacion;
    private int anioUltimaItv;

    //Constructor
    public Coche() {
        rellenarInformacion();//Relleno los datos pidiéndoselos al usuario
    }

    //Getter y Setter
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnioMatriculacion() {
        return anioMatriculacion;
    }

    public void setAnioMatriculacion(int anioMatriculacion) {
        this.anioMatriculacion = anioMatriculacion;
    }

    public int getAnioUltimaItv() {
        return anioUltimaItv;
    }

    public void setAnioUltimaItv(int anioUltimaItv) {
        this.anioUltimaItv = anioUltimaItv;
    }

    //Métodos propios
    private void rellenarInformacion() {
        System.out.println("*************************************************");
        System.out.println("** Sistema de comprobación de ITV de vehículos **");
        System.out.println("*************************************************");
        System.out.println("\n***** F O R M U L A R I O *****");
        this.matricula = Utileria.pedirMatricula("Introduce la matrícula: ");
        this.marca = Utileria.pedirCadena("Introduce la marca: ");
        this.modelo = Utileria.pedirCadena("Introduce el modelo: ");
        this.anioMatriculacion = Utileria.pedirAnio("Introduce el año de matriculación: ");
        if (Utileria.seguir("¿Desea introducir el año de la última ITV pasada? (Si/No)")) {
            this.anioUltimaItv = Utileria.pedirAnio("Introduce el año de la última ITV: ");
        }
    }

    @Override
    public String toString() {
        return "Coche[" +
                "matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", anioMatriculacion=" + anioMatriculacion +
                ", anioUltimaItv=" + anioUltimaItv +
                ']';
    }
}
