package Ejercicio1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //Establecemos conexión
        Connection conexion = establecerConexion();
        //Creo la estructura de tablas
        crearEstructuraTablas(conexion);

        //Liberamos recursos
        conexion.close();
    }

    public static Connection establecerConexion() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conexion = getConnection("jdbc:mysql://localhost/practica_repaso", "root", "");
        return conexion;
    }

    public static void crearEstructuraTablas(Connection conexion) throws SQLException {
        Statement sentencia = conexion.createStatement();

        //Tabla productos
        String sql = "CREATE TABLE IF NOT EXISTS PRODUCTOS( " +
                "id INT PRIMARY KEY, " +
                "descripcion VARCHAR(50) NOT NULL, " +
                "stockactual INT, " +
                "stockminimo INT, " +
                "pvp INT);";
        sentencia.executeUpdate(sql);

        //Tabla clientes
        sql = "CREATE TABLE IF NOT EXISTS CLIENTES( " +
                "id INT PRIMARY KEY, " +
                "nombre VARCHAR(50) NOT NULL, " +
                "direccion VARCHAR(50), " +
                "poblacion VARCHAR(50), " +
                "telef VARCHAR(20), " +
                "nif VARCHAR(10));";
        sentencia.executeUpdate(sql);

        //Tabla VENTAS
        sql = "CREATE TABLE IF NOT EXISTS VENTAS( " +
                "idventa INT PRIMARY KEY, " +
                "fechaventa DATE NOT NULL, " +
                "idcliente INT, " +
                "idproducto INT, " +
                "cantidad INT," +
                "FOREIGN KEY idcliente_fk (idcliente) REFERENCES CLIENTES (id) ON UPDATE CASCADE ON DELETE CASCADE, " +
                "FOREIGN KEY idproducto_fk (idproducto) REFERENCES PRODUCTOS (id) ON UPDATE CASCADE ON DELETE CASCADE);";

        sentencia.executeUpdate(sql);
        //Liberamos recursos
        sentencia.close();
    }

    //PEDIR ENTERO
    public static int pedirEntero(String texto) {
        Scanner s = new Scanner(System.in);
        int entero;
        System.out.println(texto);
        try {
            entero = s.nextInt();
        } catch (InputMismatchException e) {
            entero = pedirEntero(texto);
        }
        return entero;
    }

    //PEDIR FLOAT
    public static float pedirFloat(String texto) {
        Scanner s = new Scanner(System.in);
        float flotante;
        System.out.println(texto);
        try {
            flotante = s.nextFloat();
        } catch (InputMismatchException e) {
            flotante = pedirFloat(texto);
        }
        return flotante;
    }

    //PEDIR STRING
    public static String pedirString(String texto) {
        Scanner s = new Scanner(System.in);
        System.out.println(texto);
        return s.nextLine();
    }

    //PEDIR DATE
    public static Date pedirDate(String texto) {
        LocalDate fecha;
        String fechaString = pedirString(texto);
        String[] fechaArray = fechaString.split("-");
        try {
            fecha = LocalDate.of(Integer.valueOf(fechaArray[0]), Integer.valueOf(fechaArray[1]), Integer.valueOf(fechaArray[2]));
        } catch (DateTimeException e) {
            return pedirDate(texto);
        }
        return Date.valueOf(fecha);
    }

}
