package ejercicio4;

import java.sql.*;

public class Ejercicio4{
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //Cargamos el driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Establecemos la conexión
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/empresa", "root", "");
        //Ejecutamos sentencias
        Statement sentencia = conexion.createStatement();
        String sql = "SELECT apellido,salario FROM EMPLEADOS WHERE salario = (SELECT MAX(salario) FROM empleados)";
        ResultSet resultado = sentencia.executeQuery(sql);//Resultado es como un iterador
        //Recorremos el resultado
        while (resultado.next()) {
            System.out.println("=== EMPLEADO/S CON MÁXIMO SALARIO===");
            System.out.println("Apellido: "+resultado.getString(1));
            System.out.println("Salario: "+resultado.getFloat(2));
        }

        //Liberamos recursos
        conexion.close();
        sentencia.close();
        resultado.close();

    }
}
