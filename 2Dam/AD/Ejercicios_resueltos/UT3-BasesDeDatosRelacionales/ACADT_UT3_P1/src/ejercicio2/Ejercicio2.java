package ejercicio2;

import java.sql.*;

public class Ejercicio2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //Cargamos el driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Establecemos la conexión
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/empresa", "root", "");
        //Ejecutamos sentencias
        Statement sentencia = conexion.createStatement();
        String sql = "SELECT * FROM DEPARTAMENTOS";
        ResultSet resultado = sentencia.executeQuery(sql);//Resultado es como un iterador
        //Recorremos el resultado
        while (resultado.next()) {
            System.out.println(resultado.getInt(1)
                    + " " + resultado.getString(2)
                    + " " + resultado.getString(3));
        }

        //Liberamos recursos
        conexion.close();
        sentencia.close();
        resultado.close();

    }
}
