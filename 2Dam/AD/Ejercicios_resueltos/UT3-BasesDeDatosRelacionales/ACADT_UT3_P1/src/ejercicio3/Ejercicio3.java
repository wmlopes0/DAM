package ejercicio3;

import java.sql.*;

public class Ejercicio3{
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //Cargamos el driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Establecemos la conexión
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/empresa", "root", "");
        //Ejecutamos sentencias
        Statement sentencia = conexion.createStatement();
        String sql = "SELECT apellido,oficio,salario FROM EMPLEADOS WHERE dept_no=10";
        ResultSet resultado = sentencia.executeQuery(sql);//Resultado es como un iterador
        //Recorremos el resultado
        while (resultado.next()) {
            System.out.println("=== EMPLEADO ===");
            System.out.println("Apellido: "+resultado.getString(1));
            System.out.println("Oficio: "+resultado.getString(2));
            System.out.println("Salario: "+resultado.getFloat(3));
        }

        //Liberamos recursos
        conexion.close();
        sentencia.close();
        resultado.close();

    }
}
