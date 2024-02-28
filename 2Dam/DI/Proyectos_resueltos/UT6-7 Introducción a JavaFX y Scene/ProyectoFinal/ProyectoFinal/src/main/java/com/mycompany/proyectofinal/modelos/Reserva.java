package com.mycompany.proyectofinal.modelos;

/**
 *
 * @author Walter
 */
public class Reserva {

    //Atributos
    private int reserva_id;
    private int usuario_id;
    private int clase_id;
    private String estado;

    //Constructores
    public Reserva() {
    }

    public Reserva(int reserva_id, int usuario_id, int clase_id, String estado) {
        this.reserva_id = reserva_id;
        this.usuario_id = usuario_id;
        this.clase_id = clase_id;
        this.estado = estado;
    }

    //Getter y Setter
    public int getReserva_id() {
        return reserva_id;
    }

    public void setReserva_id(int reserva_id) {
        this.reserva_id = reserva_id;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getClase_id() {
        return clase_id;
    }

    public void setClase_id(int clase_id) {
        this.clase_id = clase_id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
