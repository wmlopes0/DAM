package com.mycompany.proyectofinal.modelos;

import com.mycompany.proyectofinal.Utileria;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Walter
 */
public class Clase {

    //Atributos
    private int clase_id;
    private int tipo_clase_id;
    private String tipoClase;
    private Date fecha;
    private Time hora;

    //Constructores
    public Clase() {
    }

    public Clase(int clase_id, int tipo_clase_id, Date fecha, Time hora) {
        this.clase_id = clase_id;
        this.tipo_clase_id = tipo_clase_id;
        this.tipoClase = Utileria.obtenerTipoClase(tipo_clase_id);
        this.fecha = fecha;
        this.hora = hora;
    }

    public Clase(int tipo_clase_id, Date fecha, Time hora) {
        this.tipo_clase_id = tipo_clase_id;
        this.tipoClase = Utileria.obtenerTipoClase(tipo_clase_id);
        this.fecha = fecha;
        this.hora = hora;
    }

    //Getter y Setter
    public int getClase_id() {
        return clase_id;
    }

    public void setClase_id(int clase_id) {
        this.clase_id = clase_id;
    }

    public int getTipo_clase_id() {
        return tipo_clase_id;
    }

    public void setTipo_clase_id(int tipo_clase_id) {
        this.tipo_clase_id = tipo_clase_id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getTipoClase() {
        return tipoClase;
    }

    public void setTipoClase(String tipoClase) {
        this.tipoClase = tipoClase;
    }

    @Override
    public String toString() {
        return "Clase{" + "clase_id=" + clase_id + ", tipo_clase_id=" + tipo_clase_id + ", tipoClase=" + tipoClase + ", fecha=" + fecha + ", hora=" + hora + '}';
    }

    
}
