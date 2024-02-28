package com.mycompany.aplicacioncine;

import static com.mycompany.aplicacioncine.Constantes.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
}
