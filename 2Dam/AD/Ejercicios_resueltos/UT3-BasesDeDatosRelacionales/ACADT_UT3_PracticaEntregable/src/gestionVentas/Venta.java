package gestionVentas;

import general.Utileria;

import java.sql.Date;

public class Venta {

    //Atributos
    private int id;
    private String matricula;
    private String nifCliente;
    private float descuento;
    private String motivoDesc;
    private Date fechaVenta;


    //Constructores
    public Venta(int id, String matricula, String nifCliente, float descuento, String motivoDesc, Date fechaVenta) {
        this.id = id;
        this.matricula = matricula;
        this.nifCliente = nifCliente;
        this.descuento = descuento;
        this.motivoDesc = motivoDesc;
        this.fechaVenta = fechaVenta;
    }

    public Venta() {
    }

    //Getter y Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNifCliente() {
        return nifCliente;
    }

    public void setNifCliente(String nifCliente) {
        this.nifCliente = nifCliente;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public String getMotivoDesc() {
        return motivoDesc;
    }

    public void setMotivoDesc(String motivoDesc) {
        this.motivoDesc = motivoDesc;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    //Métodos propios
    public void rellenar() {
        System.out.println("\n----- RELLENANDO INFORMACIÓN VENTA -----");
        matricula = Utileria.pedirString("Matrícula: ");
        nifCliente = Utileria.pedirString("NifCliente: ");
        if (Utileria.seguir("¿Desea introducir un descuento?")) {
            descuento = Utileria.pedirFloat("Descuento: ");
        }
        if (Utileria.seguir("¿Desea introducir un motivo para el descuento?")) {
            motivoDesc = Utileria.pedirString("Motivo del descuento: ");
        }
        fechaVenta = Utileria.pedirDate("Fecha de venta en formato '1998-08-27': ");
    }

    public void mostrar() {
        System.out.println("----- INFORMACIÓN DE VENTA -----");
        System.out.println("ID: " + id);
        System.out.println("Matrícula: " + matricula);
        System.out.println("NIF Cliente: " + nifCliente);
        if (descuento != 0) {
            System.out.println("Descuento: " + descuento);
        }
        if (motivoDesc != "") {
            System.out.println("Motivo descuento: " + motivoDesc);
        }
        System.out.println("Fecha de venta: " + fechaVenta);
    }
}
