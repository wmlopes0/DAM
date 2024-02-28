package acadt_ut3_p3;

import java.sql.*;

import static acadt_ut3_p3.Utileria.*;

public class Ejercicio2 {
    /* 2. Utiliza procedimientos almacenados para realizar las siguientes operaciones:
         a. Muestra todos aquellos empleados que trabajan en un determinado
         departamento. El nombre del departamento se solicitará por teclado al
         usuario.
         b. Dar de alta empleados en un determinado departamento. Los datos tanto del
         empleado como del departamento se solicitarán por teclado al usuario. Antes
         de insertar el empleado comprueba que el departamento exista.
         c. Mostrar aquellos empleados que tengan una antigüedad en la empresa
         superior a 2 años.*/
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //Cargo el driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Establecemos conexión con la BD
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa", "root", "");
        //Procedimiento mostrar aquellos empleados que trabajan en un determinado departamento
        crearProcedimientoMostrarEmpleados(conexion);
        mostrarEmpleadosDepartamento(10, conexion);
        //Procedimiento para insertar un empleado,comprueba que el departamento exista
        System.out.println("***********************************");
        System.out.println("******** INSERTAR EMPLEADO ********");
        System.out.println("***********************************");
        crearProcedimientoInsertarEmpleado(conexion);
        insertarEmpleado(pedirEntero("Introduzca el 'dept_no': "), conexion);
        //Mostrar aquellos empleados que tengan antiguedad superior a 2 años
        System.out.println("\nPROCEDIMIENTO ANTIGUEDAD: ");
        crearProcedimientoMostrarEmpleadosAntiguedad(conexion);
        mostrarEmpleadosAntiguedad(conexion);
        //Liberamos recursos
        conexion.close();
    }

    public static void crearProcedimientoMostrarEmpleados(Connection conexion) throws SQLException {
        //Sentencia para borrar el procedimiento si existe
        Statement sentencia = conexion.createStatement();
        String queryDrop = "DROP PROCEDURE IF EXISTS mostrarEmpleadosDepartamento";
        sentencia.execute(queryDrop);
        //Sentencia para crear el procedimiento
        String queryCreate = "CREATE PROCEDURE mostrarEmpleadosDepartamento (d INT) ";
        queryCreate += "BEGIN ";
        queryCreate += "SELECT * FROM EMPLEADOS WHERE dept_no = d; ";
        queryCreate += "END ";
        sentencia.execute(queryCreate);

        //Liberamos recursos
        sentencia.close();
    }

    public static void mostrarEmpleadosDepartamento(int d, Connection conexion) throws SQLException {
        //Comprobar si existe el departamento
        String comprobarDepartamento = "SELECT * FROM departamentos WHERE dept_no = ?";
        PreparedStatement sentenciaPreparada = conexion.prepareStatement(comprobarDepartamento);
        sentenciaPreparada.setInt(1, d);
        ResultSet resultado = sentenciaPreparada.executeQuery();
        //Si resultado es null significa que no existe ese departamento por lo que salgo del método
        if (!resultado.next()) {
            System.out.println("ERROR: El departamento indicado no existe.");
        } else {
            //Hago call al procedimiento
            String callProcedure = "{CALL mostrarEmpleadosDepartamento (?)}";
            CallableStatement cst = conexion.prepareCall(callProcedure);
            cst.setInt(1, d);
            resultado = cst.executeQuery();
            while (resultado.next()) {
                System.out.println("----- EMPLEADO -----");
                System.out.println("emp_no: " + resultado.getInt("emp_no"));
                System.out.println("apellido: " + resultado.getString("apellido"));
                System.out.println("oficio: " + resultado.getString("oficio"));
                System.out.println("salario: " + resultado.getFloat("salario"));
            }

            //Liberamos recursos
            sentenciaPreparada.close();
            cst.close();
            resultado.close();
        }
    }

    public static void crearProcedimientoMostrarEmpleadosAntiguedad(Connection conexion) throws SQLException {
        //Sentencia para borrar el procedimiento si existe
        Statement sentencia = conexion.createStatement();
        String queryDrop = "DROP PROCEDURE IF EXISTS mostrarEmpleadosAntiguedad2";
        sentencia.execute(queryDrop);
        //Sentencia para crear el procedimiento
        String queryCreate = "CREATE PROCEDURE mostrarEmpleadosAntiguedad2 () ";
        queryCreate += "BEGIN ";
        queryCreate += "SELECT * FROM EMPLEADOS WHERE (current_date() - fecha_alt) > 730;";
        queryCreate += "END ";
        sentencia.execute(queryCreate);

        //Liberamos recursos
        sentencia.close();
    }

    public static void mostrarEmpleadosAntiguedad(Connection conexion) throws SQLException {
        //Hago call al procedimiento
        String callProcedure = "{CALL mostrarEmpleadosAntiguedad2 ()}";
        CallableStatement cst = conexion.prepareCall(callProcedure);
        ResultSet resultado = cst.executeQuery();
        if (resultado.next()) {
            do {
                System.out.println("----- EMPLEADO -----");
                System.out.println("emp_no: " + resultado.getInt("emp_no"));
                System.out.println("apellido: " + resultado.getString("apellido"));
                System.out.println("oficio: " + resultado.getString("oficio"));
                System.out.println("salario: " + resultado.getFloat("salario"));
            } while (resultado.next());
        } else {
            System.out.println("No existe ningun empleado con más de 2 años de antiguedad.");
        }

        //Liberamos recursos
        cst.close();
        resultado.close();
    }

    public static void crearProcedimientoInsertarEmpleado(Connection conexion) throws SQLException {
        //Sentencia para borrar el procedimiento si existe
        Statement sentencia = conexion.createStatement();
        String queryDrop = "DROP PROCEDURE IF EXISTS insertarEmpleadoDepartamento";
        sentencia.execute(queryDrop);
        //Sentencia para crear el procedimiento
        String queryCreate = "CREATE PROCEDURE insertarEmpleadoDepartamento (pEmp_no int,pApellido varchar(20),pOficio varchar(50),pDir int,pFecha_alt date,pSalario float,pComision float,pDept_no int) ";
        queryCreate += "BEGIN ";
        queryCreate += "INSERT INTO EMPLEADOS VALUES(pEmp_no,pApellido,pOficio,pDir,pFecha_alt,pSalario,pComision,pDept_no); ";
        queryCreate += "END ";
        sentencia.execute(queryCreate);
        //Liberamos recursos
        sentencia.close();
    }

    public static void insertarEmpleado(int d, Connection conexion) throws SQLException {
        //Comprobar si existe el departamento
        String comprobarDepartamento = "SELECT * FROM departamentos WHERE dept_no = ?";
        PreparedStatement sentenciaPreparada = conexion.prepareStatement(comprobarDepartamento);
        sentenciaPreparada.setInt(1, d);
        ResultSet resultado = sentenciaPreparada.executeQuery();
        //Si resultado es null significa que no existe ese departamento por lo que salgo del método
        if (!resultado.next()) {
            System.out.println("ERROR: El departamento indicado no existe.");
        } else {
            //Creo empleado, relleno la info y hago call al procedimiento
            Empleado empleado = new Empleado();
            empleado.rellenarInfo();
            String callProcedure = "{CALL insertarEmpleadoDepartamento (?,?,?,?,?,?,?,?)}";
            CallableStatement cst = conexion.prepareCall(callProcedure);
            cst.setInt(1, empleado.getEmp_no());
            cst.setString(2, empleado.getApellido());
            cst.setString(3, empleado.getOficio());
            cst.setInt(4, empleado.getDir());
            cst.setDate(5, empleado.getFecha_alt());
            cst.setFloat(6, empleado.getSalario());
            cst.setFloat(7, empleado.getComision());
            cst.setInt(8, d);
            int registrosAfectados = cst.executeUpdate();
            System.out.println("¡Empleado insertado con éxito! Registros afectados: " + registrosAfectados);
            //Liberamos recursos
            cst.close();
        }
        //Liberamos recursos
        sentenciaPreparada.close();
        resultado.close();
    }
}
