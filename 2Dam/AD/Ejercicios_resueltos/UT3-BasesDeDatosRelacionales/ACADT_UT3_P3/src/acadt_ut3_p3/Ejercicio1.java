package acadt_ut3_p3;

import java.sql.*;

public class Ejercicio1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //Cargo el driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Establecemos conexióncon la BD
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa","root","");
        //Creamos procedimiento para subir el salario de los empleados
        crearProcedimientoSalario(conexion);
        //Ejecutamos el procedimiento para subir el salario de los empleados
        subidaSalarioEmpleados(10,23,conexion);
        //Liberamos recursos
        conexion.close();
    }
    public static void crearProcedimientoSalario(Connection conexion) throws SQLException {
        //Creamos sentencia
        Statement sentencia = conexion.createStatement();

        //Creamos la query para borrar el procedimiento si existe
        String queryDrop = "DROP PROCEDURE IF EXISTS subida_sal";

        //Creamos la query para crear el procedimiento, NOTA: no se realiza el cambio de delimitador porque puede dar problemas
        String queryCreate = "CREATE PROCEDURE subida_sal (d INT, subida INT) ";
        queryCreate += "BEGIN ";
        queryCreate += "UPDATE empleados SET salario = salario + subida WHERE dept_no = d; ";
        queryCreate += "END ";

        //Ejecuto la queryDrop
        sentencia.execute(queryDrop);
        //Ejecuto la queryCreate
        sentencia.execute(queryCreate);
        System.out.println("¡PROCEDIMIENTO CREADO!");

        //Liberamos recursos
        sentencia.close();
    }

    public static void subidaSalarioEmpleados(int dep,double subida,Connection conexion) throws SQLException {
        //SQL para llamar al procedimiento
        String sql = "{CALL subida_sal(?,?)}";
        CallableStatement cst = conexion.prepareCall(sql);
        //Damos valor a los argumentos
        cst.setInt(1,dep);
        cst.setDouble(2,subida);
        //Ejecutamos el procedimiento
        cst.executeUpdate();
        System.out.println("¡SUBIDA REALIZADA!");
        //Liberamos recursos
        cst.close();
    }
}
