package gestionVehiculos;

import general.Utileria;

public class Vehiculo {

    //Atributos
    private String matricula;
    private String marca;
    private String color;
    private float precio;

    //Constructores

    public Vehiculo(String matricula, String marca, String color, float precio) {
        this.matricula = matricula;
        this.marca = marca;
        this.color = color;
        this.precio = precio;
    }

    public Vehiculo() {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    //Métodos propios
    public void rellenar() {
        System.out.println("\n----- RELLENANDO INFORMACIÓN VEHÍCULO -----");
        matricula = Utileria.pedirString("Matrícula: ");
        marca = Utileria.pedirString("Marca: ");
        color = Utileria.pedirString("Color: ");
        precio = Utileria.pedirFloat("Precio: ");
    }

    public void mostrar() {
        System.out.println("- " + marca + " color " + color + " con matrícula " + matricula + ". Precio: " + precio + "€");
    }
}
