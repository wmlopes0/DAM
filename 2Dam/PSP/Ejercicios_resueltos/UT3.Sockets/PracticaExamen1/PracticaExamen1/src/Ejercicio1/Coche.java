package Ejercicio1;

import java.io.Serializable;
import java.time.LocalDate;

import static Ejercicio1.Utileria.seguir;

public class Coche implements Serializable {

    //Atributos
    private String matricula;
    private String marca;
    private String modelo;
    private int anoMatriculacion;
    private LocalDate fechaUltimaItv;

    //Constructores
    public Coche() {
        rellenarDatos();
    }

    public Coche(String matricula, String marca, String modelo, int anoMatriculacion, LocalDate fechaUltimaItv) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.anoMatriculacion = anoMatriculacion;
        this.fechaUltimaItv = fechaUltimaItv;
    }

    public Coche(String matricula, String marca, String modelo, int anoMatriculacion) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.anoMatriculacion = anoMatriculacion;
        this.fechaUltimaItv = null;
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

    public int getAnoMatriculacion() {
        return anoMatriculacion;
    }

    public void setAnoMatriculacion(int anoMatriculacion) {
        this.anoMatriculacion = anoMatriculacion;
    }

    public LocalDate getFechaUltimaItv() {
        return fechaUltimaItv;
    }

    public void setFechaUltimaItv(LocalDate fechaUltimaItv) {
        this.fechaUltimaItv = fechaUltimaItv;
    }

    //Métodos propios
    public void rellenarDatos() {
        System.out.println("***********************************");
        System.out.println("******* F O R M U L A R I O *******");
        System.out.println("***********************************");
        this.matricula = Utileria.pedirCadena("Introduce la matrícula:");
        this.marca = Utileria.pedirCadena("Introduce la marca:");
        this.modelo = Utileria.pedirCadena("Introduce el modelo:");
        this.anoMatriculacion = Utileria.pedirEntero("Introduce el año de matriculación:");
        if (seguir("¿Desea introducir la fecha de la última matriculación?")) {
            this.fechaUltimaItv = Utileria.pedirFecha();
        }
        System.out.println("\n********** FORMULARIO RELLENADO **********\n");
    }
}
