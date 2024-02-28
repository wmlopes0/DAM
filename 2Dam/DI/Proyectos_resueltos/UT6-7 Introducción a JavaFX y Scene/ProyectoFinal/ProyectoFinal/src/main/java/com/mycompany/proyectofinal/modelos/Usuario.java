package com.mycompany.proyectofinal.modelos;

import com.mycompany.proyectofinal.Utileria;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Walter
 */
public class Usuario {

    //Atributos
    private int id;
    private String nombre;
    private String contrasena;
    private String fotografia;
    private String datos;
    private int clasesPagadas;
    private String tipoUsuario;

    //Constructores
    public Usuario() {
    }

    public Usuario(int id, String nombre, String contrasena, String fotografia, String datos, int clasesPagadas, String tipoUsuario) {
        this.id = id;
        this.nombre = nombre;
        try {
            this.contrasena = Utileria.generarHash(contrasena);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error al generar el HASH");
        }
        this.fotografia = fotografia;
        this.datos = datos;
        this.clasesPagadas = clasesPagadas;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario(String nombre, String contrasena, String fotografia, String datos, int clasesPagadas, String tipoUsuario) {
        this.nombre = nombre;
        try {
            this.contrasena = Utileria.generarHash(contrasena);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error al generar el HASH");
        }
        this.fotografia = fotografia;
        this.datos = datos;
        this.clasesPagadas = clasesPagadas;
        this.tipoUsuario = tipoUsuario;
    }

    //Getter y Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public int getClasesPagadas() {
        return clasesPagadas;
    }

    public void setClasesPagadas(int clasesPagadas) {
        this.clasesPagadas = clasesPagadas;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
