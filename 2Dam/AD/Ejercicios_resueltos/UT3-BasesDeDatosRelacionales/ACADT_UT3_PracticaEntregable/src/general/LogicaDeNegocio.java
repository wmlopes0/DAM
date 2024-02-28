package general;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static general.Constantes.SQLEXCEPTION;

public class LogicaDeNegocio {

    //    Crear estructura de tablas
    public static void crearEstructuraTablas(Connection conexion) {
        //Este método crea una estructura de tablas básica con la que trabajaremos, solo crea las tablas si no existen, por lo que si ya existen tablas con ese nombre es muy probable que de problemas
        try {
            //TABLA VEHÍCULOS
            Statement sentencia = null;
            sentencia = conexion.createStatement();
            //Creo la tabla
            String sql = "CREATE TABLE IF NOT EXISTS VEHICULOS( ";
            sql += "matricula VARCHAR(7) PRIMARY KEY, ";
            sql += "marca VARCHAR(23) NOT NULL, ";
            sql += "color VARCHAR(23) NOT NULL, ";
            sql += "precio FLOAT(8,2) NOT NULL);";
            sentencia.executeUpdate(sql);

            //TABLA CLIENTES
            //Creo la tabla
            sql = "CREATE TABLE IF NOT EXISTS CLIENTES( ";
            sql += "nif VARCHAR(9) PRIMARY KEY, ";
            sql += "nombre VARCHAR(23) NOT NULL, ";
            sql += "apellidos VARCHAR(23) NOT NULL); ";
            sentencia.executeUpdate(sql);

            //TABLA VENTAS
            //Creo la tabla
            sql = "CREATE TABLE IF NOT EXISTS VENTAS( ";
            sql += "id INT PRIMARY KEY, ";
            sql += "matricula VARCHAR(7) NOT NULL, ";
            sql += "nifCliente VARCHAR(9) NOT NULL, ";
            sql += "descuento FLOAT(6,2), ";
            sql += "motivoDescuento VARCHAR(50), ";
            sql += "fechaVenta DATE, ";
            sql += "CONSTRAINT fk_matricula FOREIGN KEY (matricula) REFERENCES VEHICULOS(matricula) ON DELETE CASCADE ON UPDATE CASCADE,";
            sql += "CONSTRAINT fk_nifCliente FOREIGN KEY (nifCliente) REFERENCES CLIENTES(nif) ON DELETE CASCADE ON UPDATE CASCADE);";
            sentencia.executeUpdate(sql);

            //LIBERAMOS RECURSOS
            sentencia.close();
            //CHECK
            System.out.println("\n¡ESTRUCTURA DE TABLAS CREADA CORRECTAMENTE!");
        } catch (SQLException e) {
            System.out.println(SQLEXCEPTION);
        }
    }

    //    Insertar datos de ejemplo
    public static void insertarDatosEjemplo(Connection conexion) {
        try {
            Statement sentencia = conexion.createStatement();
            String sql;
            //INSERT VEHÍCULOS
            sql = "INSERT INTO VEHICULOS (matricula, marca, color, precio) VALUES ";
            sql += "('ABC1234', 'Toyota', 'Rojo', 20000.50), ";
            sql += "('XYZ5678', 'Ford', 'Azul', 25000.75), ";
            sql += "('LMN9012', 'Honda', 'Blanco', 23000.00), ";
            sql += "('DEF3456', 'Chevrolet', 'Negro', 27000.30), ";
            sql += "('GHI7890', 'Nissan', 'Gris', 21000.20); ";
            sentencia.executeUpdate(sql);

            //INSERT CLIENTES
            sql = "INSERT INTO CLIENTES (nif, nombre, apellidos) VALUES ";
            sql += "('12345678A', 'Juan', 'Pérez García'), ";
            sql += "('23456789B', 'María', 'López Torres'), ";
            sql += "('34567890C', 'Carlos', 'González Ruiz'), ";
            sql += "('45678901D', 'Ana', 'Fernández Morales'), ";
            sql += "('56789012E', 'David', 'Martínez Jiménez');";
            sentencia.executeUpdate(sql);

            //INSERT VENTAS
            sql = "INSERT INTO VENTAS (id, matricula, nifCliente, descuento, motivoDescuento, fechaVenta) VALUES ";
            sql += "(1, 'ABC1234', '12345678A', 500.00, 'Promoción de verano', '2023-11-02'), ";
            sql += "(2, 'XYZ5678', '23456789B', 0.00, NULL, '2023-11-02'), ";
            sql += "(3, 'LMN9012', '34567890C', 300.00, 'Descuento por lealtad', '2023-11-02'), ";
            sql += "(4, 'DEF3456', '45678901D', 0.00, NULL, '2023-11-02'), ";
            sql += "(5, 'GHI7890', '56789012E', 200.00, 'Promoción de fin de año', '2023-11-02'); ";
            sentencia.executeUpdate(sql);

            //LIBERAMOS RECURSOS
            sentencia.close();
            //CHECK
            System.out.println("\n¡DATOS DE EJEMPLO INSERTADOS CORRECTAMENTE!");
        } catch (SQLException e) {
//            System.out.println(SQLEXCEPTION);
//            Ignoro esta excepción para evitar que me salgan errores de claves duplicadas, si estos datos ya están insertados en la tabla simplemente no se insertarán.
        }
    }
}
