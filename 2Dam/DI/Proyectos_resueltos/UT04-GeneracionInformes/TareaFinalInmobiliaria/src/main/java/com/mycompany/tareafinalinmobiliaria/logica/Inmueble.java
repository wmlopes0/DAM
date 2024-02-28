package com.mycompany.tareafinalinmobiliaria.logica;

/**
 *
 * @author Walter
 */
public class Inmueble {

    //Atributos
    private int idInmueble;
    private String titulo;
    private String descripcion;
    private String foto;
    private String transaccion;
    private int precio;
    private int telefono;

    //Constructores
    public Inmueble() {
        foto = "https://static.vecteezy.com/system/resources/previews/000/355/795/non_2x/house-vector-icon.jpg";
    }

    public Inmueble(int idInmueble, String titulo, String descripcion, String foto, String transaccion, int precio, int telefono) {
        this.idInmueble = idInmueble;
        this.titulo = titulo;
        this.descripcion = descripcion;
        if (foto.equalsIgnoreCase("")) {
            this.foto = "https://static.vecteezy.com/system/resources/previews/000/355/795/non_2x/house-vector-icon.jpg";
        } else {
            this.foto = foto;
        }
        this.transaccion = transaccion;
        this.precio = precio;
        this.telefono = telefono;
    }

    public Inmueble(String titulo, String descripcion, String foto, String transaccion, int precio, int telefono) {
        this.idInmueble = idInmueble;
        this.titulo = titulo;
        this.descripcion = descripcion;
        if (foto.equalsIgnoreCase("")) {
            this.foto = "https://static.vecteezy.com/system/resources/previews/000/355/795/non_2x/house-vector-icon.jpg";
        } else {
            this.foto = foto;
        }
        this.transaccion = transaccion;
        this.precio = precio;
        this.telefono = telefono;
    }

    //Getter y Setter
    public int getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(String transaccion) {
        this.transaccion = transaccion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

}
