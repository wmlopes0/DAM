package com.mycompany.tareafinalinmobiliaria.logica;

import static com.mycompany.tareafinalinmobiliaria.logica.Constantes.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author Walter
 */
public class Utileria {

    //ESTABLECER CONEXIÓN
    public static Connection establecerConexion() {
        //Cargamos el driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(CLASS_NOT_FOUND_EXCEPTION);
        }
        //Establecemos la conexión
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:" + PUERTO + "/" + BD, USUARIO, "");
        } catch (SQLException e) {
            System.out.println(CONEXION_FALLIDA);
            return conexion;
        }
        return conexion;
    }

    //AÑADIR INMUEBLE A LA BD
    public static void addInmueble(Inmueble inmueble) {
        //Creamos conexión
        Connection conexion = establecerConexion();
        //Creamos sentencia insert
        try {
            PreparedStatement sentenciaPreparada = null;
            if (inmueble.getTelefono() == 0) {
                String sql = "INSERT INTO inmuebles (titulo,descripcion,foto,ventaAlquiler,precio) VALUES (?,?,?,?,?)";
                sentenciaPreparada = conexion.prepareStatement(sql);
                sentenciaPreparada.setString(1, inmueble.getTitulo());
                sentenciaPreparada.setString(2, inmueble.getDescripcion());
                sentenciaPreparada.setString(3, inmueble.getFoto());
                sentenciaPreparada.setString(4, inmueble.getTransaccion());
                sentenciaPreparada.setInt(5, inmueble.getPrecio());
                sentenciaPreparada.executeUpdate();
            } else {
                String sql = "INSERT INTO inmuebles (titulo,descripcion,foto,ventaAlquiler,precio,telefono) VALUES (?,?,?,?,?,?)";
                sentenciaPreparada = conexion.prepareStatement(sql);
                sentenciaPreparada.setString(1, inmueble.getTitulo());
                sentenciaPreparada.setString(2, inmueble.getDescripcion());
                sentenciaPreparada.setString(3, inmueble.getFoto());
                sentenciaPreparada.setString(4, inmueble.getTransaccion());
                sentenciaPreparada.setInt(5, inmueble.getPrecio());
                sentenciaPreparada.setInt(6, inmueble.getTelefono());
                sentenciaPreparada.executeUpdate();
            }
            //Liberamos recursos
            sentenciaPreparada.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println(SQLEXCEPTION);
        }
    }

    //ELIMINAR INMUEBLE DE LA BD
    public static void eliminarInmueble(Inmueble inmueble) {
        //Creamos conexión
        Connection conexion = establecerConexion();
        //Creamos sentencia delete
        String sql = "DELETE FROM inmuebles WHERE idInmueble = ?";
        PreparedStatement sentenciaPreparada = null;
        try {
            sentenciaPreparada = conexion.prepareStatement(sql);
            sentenciaPreparada.setInt(1, inmueble.getIdInmueble());
            sentenciaPreparada.executeUpdate();
            //Liberamos recursos
            sentenciaPreparada.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println(SQLEXCEPTION);
        }
    }

    //MODIFICAR INMUEBLE DE LA BD
    public static void modificarInmueble(Inmueble inmuebleOriginal, Inmueble inmuebleModificado) {
        //Creamos conexión
        Connection conexion = establecerConexion();
        //Creamos sentencia delete
        String sql = "UPDATE inmuebles SET titulo = ?, descripcion = ?, foto = ?, ventaAlquiler = ?, precio = ?, telefono = ? WHERE idInmueble = ?";
        PreparedStatement sentenciaPreparada = null;
        try {
            sentenciaPreparada = conexion.prepareStatement(sql);
            sentenciaPreparada.setString(1, inmuebleModificado.getTitulo());
            sentenciaPreparada.setString(2, inmuebleModificado.getDescripcion());
            sentenciaPreparada.setString(3, inmuebleModificado.getFoto());
            sentenciaPreparada.setString(4, inmuebleModificado.getTransaccion());
            sentenciaPreparada.setInt(5, inmuebleModificado.getPrecio());
            sentenciaPreparada.setInt(6, inmuebleModificado.getTelefono());
            sentenciaPreparada.setInt(7, inmuebleOriginal.getIdInmueble());
            sentenciaPreparada.executeUpdate();
            //Liberamos recursos
            sentenciaPreparada.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println(SQLEXCEPTION);
        }
    }
}
