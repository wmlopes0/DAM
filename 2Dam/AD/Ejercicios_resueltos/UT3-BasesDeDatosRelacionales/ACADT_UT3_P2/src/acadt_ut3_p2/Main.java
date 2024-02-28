package acadt_ut3_p2;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import static acadt_ut3_p2.Utileria.*;

public class Main {
    //Variables globales
    final static int PUERTO = 3307;

    public static void main(String[] args) throws InterruptedException, SQLException, ClassNotFoundException {
        int opcion;
        Connection conexion;
//        Mensaje de espera ---------------------------------------
        System.out.println("CONECTANDO A LA BASE DE DATOS");
        simulacionEspera();
        conexion = establecerConexion();
        System.out.println("\n¡CONEXIÓN REALIZADA CORRECTAMENTE!");
        Thread.sleep(1000);
//        Fin de mensaje de espera ---------------------------------

        do {
            opcion = mostrarMenuGeneral();
            switch (opcion) {
                case 1:
                    gestionEmpleados(conexion);
                    break;
                case 2:
                    gestionDepartamentos(conexion);
                    break;
                case 3:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("Error:Introduzca una opción válida.");
                    break;
            }
        } while (opcion != 3);

        //Liberamos recursos
        conexion.close();
    }

    //MENÚ GENERAL
    public static int mostrarMenuGeneral() {
        Scanner s = new Scanner(System.in);
        int opcion;
        System.out.println("*********************************************");
        System.out.println("********** M E N U   G E N E R A L **********");
        System.out.println("*********************************************");
        System.out.println("1.- Gestión de empleados.");
        System.out.println("2.- Gestión de departamentos.");
        System.out.println("3.- Salir.");
        System.out.println("*********************************************");
        System.out.println("Por favor, introduzca una opción: ");
        try {
            opcion = s.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: No puedes introducir una letra.");
            opcion = mostrarMenuGeneral();
        }
        return opcion;
    }

    //MENÚ GESTIÓN DE EMPLEADOS
    public static int mostrarMenuGestionEmpleados() {
        Scanner s = new Scanner(System.in);
        int opcion;
        System.out.println("==== GESTIÓN DE EMPLEADOS ====");
        System.out.println("1.- Insertar empleado.");
        System.out.println("2.- Modificar empleado.");
        System.out.println("3.- Borrar empleado.");
        System.out.println("4.- Consultar todos los empleados.");
        System.out.println("5.- Consultar todos los empleados de un departamento.");
        System.out.println("6.- Consultar empleados por el número de empleado.");
        System.out.println("7.- Consultar empleados que tengan un salario superior al introducido.");
        System.out.println("8.- Consultar empleados que tengan un salario igual o inferior al introducido.");
        System.out.println("9.- Salir al menú general.");
        System.out.println("===============");
        System.out.println("Por favor, introduzca una opción: ");
        try {
            opcion = s.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: No puedes introducir una letra.");
            opcion = mostrarMenuGestionEmpleados();
        }
        return opcion;
    }

    public static void gestionEmpleados(Connection conexion) throws SQLException {
        int opcion;
        do {
            opcion = mostrarMenuGestionEmpleados();
            switch (opcion) {
                case 1:
                    opcion1GestionEmpleados(conexion);
                    break;
                case 2:
                    opcion2GestionEmpleados(conexion);
                    break;
                case 3:
                    opcion3GestionEmpleados(conexion);
                    break;
                case 4:
                    opcion4GestionEmpleados(conexion);
                    break;
                case 5:
                    opcion5GestionEmpleados(conexion);
                    break;
                case 6:
                    opcion6GestionEmpleados(conexion);
                    break;
                case 7:
                    opcion7GestionEmpleados(conexion);
                    break;
                case 8:
                    opcion8GestionEmpleados(conexion);
                    break;
                default:
                    System.out.println("Error:Introduzca una opción válida.");
                    break;
            }
        } while (opcion != 9);
    }

    //MENÚ GESTIÓN DE DEPARTAMENTOS
    public static int mostrarMenuGestionDepartamentos() {
        Scanner s = new Scanner(System.in);
        int opcion;
        System.out.println("==== GESTIÓN DE DEPARTAMENTOS ====");
        System.out.println("1.- Insertar departamento.");
        System.out.println("2.- Modificar departamento.");
        System.out.println("3.- Eliminar departamento.");
        System.out.println("4.- Consultar todos los departamentos.");
        System.out.println("5.- Consultar departamento por nombre.");
        System.out.println("6.- Consultar departamento por ID.");
        System.out.println("7.- Salir al menú general.");
        System.out.println("===============");
        System.out.println("Por favor, introduzca una opción: ");
        try {
            opcion = s.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: No puedes introducir una letra.");
            opcion = mostrarMenuGestionDepartamentos();
        }
        return opcion;
    }

    public static void gestionDepartamentos(Connection conexion) throws SQLException {
        int opcion;
        do {
            opcion = mostrarMenuGestionDepartamentos();
            switch (opcion) {
                case 1:
                    opcion1GestionDepartamentos(conexion);
                    break;
                case 2:
                    opcion2GestionDepartamentos(conexion);
                    break;
                case 3:
                    opcion3GestionDepartamentos(conexion);
                    break;
                case 4:
                    opcion4GestionDepartamentos(conexion);
                    break;
                case 5:
                    opcion5GestionDepartamentos(conexion);
                    break;
                case 6:
                    opcion6GestionDepartamentos(conexion);
                    break;
                default:
                    System.out.println("Error:Introduzca una opción válida.");
                    break;
            }
        } while (opcion != 7);
    }

    //GESTIÓN DE EMPLEADOS ==================================================
    public static void opcion1GestionEmpleados(Connection conexion) throws SQLException {
//        ENUNCIADO: Insertar Empleado
//        Ejecutamos sentencias
        Statement sentencia = conexion.createStatement();
        String sql = "INSERT INTO EMPLEADOS VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql);
//        Creamos empleado
        Empleado empleado = new Empleado();
        empleado.rellenarInfo();
//        Rellenamos variables de la sentencia preparada
        sentenciaPreparada.setInt(1, empleado.getEmp_no());
        sentenciaPreparada.setString(2, empleado.getApellido());
        sentenciaPreparada.setString(3, empleado.getOficio());
        sentenciaPreparada.setInt(4, empleado.getDir());
        sentenciaPreparada.setDate(5, empleado.getFecha_alt());
        sentenciaPreparada.setFloat(6, empleado.getSalario());
        sentenciaPreparada.setFloat(7, empleado.getComision());
        sentenciaPreparada.setInt(8, empleado.getDep_no());
//       Ejecutamos la sentencia
        System.out.println("Se han afectado " + sentenciaPreparada.executeUpdate() + " filas.");
        System.out.println("¡EMPLEADO INSERTADO CORRECTAMENTE!");
//        LIBERAMOS RECURSOS
        sentenciaPreparada.close();
        sentencia.close();
    }

    public static void opcion2GestionEmpleados(Connection conexion) throws SQLException {
//        ENUNCIADO: Modificar empleado
        Empleado empleadoModificado = new Empleado();
//        En primer lugar recuperamos el emp_no del empleado que queremos modificar
        int emp_no_user = pedirEntero("Introduzca el 'emp_no' del empleado que quieres modificar:");
//        Sentencia de búsqueda
        String sql = "SELECT * FROM EMPLEADOS WHERE emp_no = ?";
        PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql);
        sentenciaPreparada.setInt(1, emp_no_user);
        ResultSet resultado = sentenciaPreparada.executeQuery();
        resultado.next();//Sacamos el primer y único resultado teórico.
        //Recorremos el resultado mientras preguntamos las modificaciones
        System.out.println("===== MODIFICANDO EMPLEADO =====");
        //Apellido
        if (seguir("¿Desea modificar el apellido?")) {
            System.out.println("El apellido actual es " + resultado.getString("apellido"));
            empleadoModificado.setApellido(pedirString("Introduzca el apellido modificado:"));
        }
        //Oficio
        if (seguir("¿Desea modificar el oficio?")) {
            System.out.println("El oficio actual es " + resultado.getString("oficio"));
            empleadoModificado.setOficio(pedirString("Introduzca el oficio modificado:"));
        }
        //Dir
        if (seguir("¿Desea modificar el dir?")) {
            System.out.println("El dir actual es " + resultado.getInt("dir"));
            empleadoModificado.setDir(pedirEntero("Introduzca el dir modificado:"));
        }
        //Fecha_alt
        if (seguir("¿Desea modificar la fecha_alt?")) {
            System.out.println("La fecha_alt actual es " + resultado.getDate("fecha_alt"));
            empleadoModificado.setFecha_alt(pedirDate());
        }
        //Salario
        if (seguir("¿Desea modificar el salario?")) {
            System.out.println("El salario actual es " + resultado.getFloat("salario"));
            empleadoModificado.setSalario(pedirFloat("Introduzca el salario modificado:"));
        }
        //Comision
        if (seguir("¿Desea modificar la comisión?")) {
            System.out.println("La comisión actual es " + resultado.getFloat("comision"));
            empleadoModificado.setComision(pedirFloat("Introduzca la comisión modificada:"));
        }
        //Dept_no
        if (seguir("¿Desea modificar el dept_no?")) {
            System.out.println("El dept_no actual es " + resultado.getInt("dept_no"));
            empleadoModificado.setDep_no(pedirEntero("Introduzca el dept_no modificado:"));
        }

        //SENTENCIA DE ACTUALIZACIÓN
        sql = "UPDATE EMPLEADOS SET" +
                " apellido = ?," +
                " oficio = ?," +
                " dir = ?," +
                " fecha_alt = ?," +
                " salario = ?," +
                " comision = ?," +
                " dept_no = ?" +
                " WHERE emp_no = ?";
        sentenciaPreparada = conexion.prepareStatement(sql);
        //Apellido
        if (empleadoModificado.getApellido() != null) {
            sentenciaPreparada.setString(1, empleadoModificado.getApellido());
        } else {
            sentenciaPreparada.setString(1, resultado.getString("apellido"));
        }
        //oficio
        if (empleadoModificado.getOficio() != null) {
            sentenciaPreparada.setString(2, empleadoModificado.getOficio());
        } else {
            sentenciaPreparada.setString(2, resultado.getString("oficio"));
        }
        //dir
        if (empleadoModificado.getDir() != 0) {
            sentenciaPreparada.setInt(3, empleadoModificado.getDir());
        } else {
            sentenciaPreparada.setInt(3, resultado.getInt("dir"));
        }
        //fecha_alt
        if (empleadoModificado.getFecha_alt() != null) {
            sentenciaPreparada.setDate(4, empleadoModificado.getFecha_alt());
        } else {
            sentenciaPreparada.setDate(4, resultado.getDate("fecha_alt"));
        }
        //salario
        if (empleadoModificado.getSalario() != 0) {
            sentenciaPreparada.setFloat(5, empleadoModificado.getSalario());
        } else {
            sentenciaPreparada.setFloat(5, resultado.getFloat("salario"));
        }
        //comision
        if (empleadoModificado.getComision() != 0) {
            sentenciaPreparada.setFloat(6, empleadoModificado.getComision());
        } else {
            sentenciaPreparada.setFloat(6, resultado.getFloat("comision"));
        }
        //dept_no
        if (empleadoModificado.getDep_no() != 0) {
            sentenciaPreparada.setInt(7, empleadoModificado.getDep_no());
        } else {
            sentenciaPreparada.setInt(7, resultado.getInt("dept_no"));
        }
        // Emp_no
        sentenciaPreparada.setInt(8, emp_no_user);

        System.out.println("Se han actualizado " + sentenciaPreparada.executeUpdate() + " filas.");
//      LIBERAMOS RECURSOS
        resultado.close();
        sentenciaPreparada.close();

        System.out.println("¡EMPLEADO ACTUALIZADO CORRECTAMENTE!");
    }

    public static void opcion3GestionEmpleados(Connection conexion) throws SQLException {
        //        ENUNCIADO: Borrar empleado
        //En primer lugar pedimos al usuario el emp_no del empleado que queremos eliminar de la base de datos
        int emp_no_user = pedirEntero("Introduzca el 'emp_no' del empleado que quieres eliminar:");
        //Elaboramos la sentencia
        String sql = "DELETE FROM EMPLEADOS WHERE emp_no = ?";
        PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql);
        sentenciaPreparada.setInt(1, emp_no_user);
        System.out.println("Se han actualizado " + sentenciaPreparada.executeUpdate() + " filas.");
        //Liberamos recursos
        sentenciaPreparada.close();
        System.out.println("¡EMPLEADO ELIMINADO CORRECTAMENTE!");
    }

    public static void opcion4GestionEmpleados(Connection conexion) throws SQLException {
//       ENUNCIADO: Consultar todos los empleados
        //Ejecutamos sentencias
        Statement sentencia = conexion.createStatement();
        String sql = "SELECT emp_no,apellido,oficio,fecha_alt,salario,dept_no FROM EMPLEADOS";
        ResultSet resultado = sentencia.executeQuery(sql);//Resultado es como un iterador
        //Recorremos el resultado
        System.out.println("=== LISTADO DE EMPLEADOS===");
        while (resultado.next()) {
            System.out.println("------------------------");
            System.out.println("Número de empleado: " + resultado.getInt(1));
            System.out.println("Apellido: " + resultado.getString(2));
            System.out.println("Oficio: " + resultado.getString(3));
            System.out.println("Fecha de alta: " + resultado.getDate(4));
            System.out.println("Salario: " + resultado.getFloat(5));
            System.out.println("Departamento: " + resultado.getInt(6));
        }
        System.out.println("=== FIN DEL LISTADO DE EMPLEADOS===");

//        Liberamos recursos
        sentencia.close();
        resultado.close();
    }

    public static void opcion5GestionEmpleados(Connection conexion) throws SQLException {
//       ENUNCIADO: Consultar todos los empleados de un departamento
        //Pedimos al usuario el número de departamento
        int dept_no_user = pedirEntero("Introduzca el 'dept_no' para consultar los empleados:");
        //Ejecutamos sentencias
        String sql = "SELECT emp_no,apellido,oficio,fecha_alt,salario,dept_no FROM EMPLEADOS WHERE dept_no = ?";
        PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql);
        sentenciaPreparada.setInt(1, dept_no_user);
        ResultSet resultado = sentenciaPreparada.executeQuery();//Resultado es como un iterador
        //Recorremos el resultado
        System.out.println("=== LISTADO DE EMPLEADOS===");
        while (resultado.next()) {
            System.out.println("------------------------");
            System.out.println("Número de empleado: " + resultado.getInt(1));
            System.out.println("Apellido: " + resultado.getString(2));
            System.out.println("Oficio: " + resultado.getString(3));
            System.out.println("Fecha de alta: " + resultado.getDate(4));
            System.out.println("Salario: " + resultado.getFloat(5));
            System.out.println("Departamento: " + resultado.getInt(6));
        }
        System.out.println("=== FIN DEL LISTADO DE EMPLEADOS===");

//        Liberamos recursos
        sentenciaPreparada.close();
        resultado.close();
    }

    public static void opcion6GestionEmpleados(Connection conexion) throws SQLException {
//       ENUNCIADO:  Consultar empleados por el campo “emp_no”.
        System.out.println("========= EMPLEADOS =========");
        int emp_no = pedirEntero("Introduzca el número de empleado: ");
        //Ejecutamos sentencias
        String sql = "SELECT emp_no,apellido,oficio,fecha_alt,salario,dept_no FROM EMPLEADOS WHERE emp_no = ?";
        PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql);
        sentenciaPreparada.setInt(1, emp_no);
        ResultSet resultado = sentenciaPreparada.executeQuery();//Resultado es como un iterador
        //Recorremos el resultado
        while (resultado.next()) {
            System.out.println("------------------------");
            System.out.println("Número de empleado: " + resultado.getInt(1));
            System.out.println("Apellido: " + resultado.getString(2));
            System.out.println("Oficio: " + resultado.getString(3));
            System.out.println("Fecha de alta: " + resultado.getDate(4));
            System.out.println("Salario: " + resultado.getFloat(5));
            System.out.println("Departamento: " + resultado.getInt(6));
        }
        System.out.println("========= FIN DE EMPLEADOS =========");
//        Liberamos recursos
        sentenciaPreparada.close();
        resultado.close();
    }

    public static void opcion7GestionEmpleados(Connection conexion) throws SQLException {
//       ENUNCIADO:   Consultar empleados que tengan un salario superior al introducido por el usuario.
        System.out.println("========= EMPLEADOS =========");
        float salario = pedirFloat("Introduzca un salario: ");
        //Ejecutamos sentencias
        String sql = "SELECT emp_no,apellido,oficio,fecha_alt,salario,dept_no FROM EMPLEADOS WHERE salario > ?";
        PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql);
        sentenciaPreparada.setFloat(1, salario);
        ResultSet resultado = sentenciaPreparada.executeQuery();//Resultado es como un iterador
        //Recorremos el resultado
        while (resultado.next()) {
            System.out.println("------------------------");
            System.out.println("Número de empleado: " + resultado.getInt(1));
            System.out.println("Apellido: " + resultado.getString(2));
            System.out.println("Oficio: " + resultado.getString(3));
            System.out.println("Fecha de alta: " + resultado.getDate(4));
            System.out.println("Salario: " + resultado.getFloat(5));
            System.out.println("Departamento: " + resultado.getInt(6));
        }

        System.out.println("========= FIN DE EMPLEADOS =========");
//        Liberamos recursos
        sentenciaPreparada.close();
        resultado.close();
    }

    public static void opcion8GestionEmpleados(Connection conexion) throws SQLException {
//       ENUNCIADO:   Consultar empleados que tengan un salario igual o inferior al introducido por el usuario.
        System.out.println("========= EMPLEADOS =========");
        float salario = pedirFloat("Introduzca un salario: ");
        //Ejecutamos sentencias
        String sql = "SELECT emp_no,apellido,oficio,fecha_alt,salario,dept_no FROM EMPLEADOS WHERE salario <= ?";
        PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql);
        sentenciaPreparada.setFloat(1, salario);
        ResultSet resultado = sentenciaPreparada.executeQuery();//Resultado es como un iterador
        //Recorremos el resultado
        while (resultado.next()) {
            System.out.println("------------------------");
            System.out.println("Número de empleado: " + resultado.getInt(1));
            System.out.println("Apellido: " + resultado.getString(2));
            System.out.println("Oficio: " + resultado.getString(3));
            System.out.println("Fecha de alta: " + resultado.getDate(4));
            System.out.println("Salario: " + resultado.getFloat(5));
            System.out.println("Departamento: " + resultado.getInt(6));
        }
        System.out.println("========= FIN DE EMPLEADOS =========");
//        Liberamos recursos
        sentenciaPreparada.close();
        resultado.close();
    }

    //=======================================================================
    //GESTIÓN DE DEPARTAMENTOS ==============================================
    public static void opcion1GestionDepartamentos(Connection conexion) throws SQLException {
//        ENUNCIADO: Insertar Departamento
        Departamento departamento = new Departamento();
        departamento.rellenarInfo();
//        Ejecutamos sentencia
        String sql = "INSERT INTO DEPARTAMENTOS VALUES(?,?,?)";
        PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql);
        sentenciaPreparada.setInt(1, departamento.getDep_no());
        sentenciaPreparada.setString(2, departamento.getDnombre());
        sentenciaPreparada.setString(3, departamento.getLoc());
        System.out.println("Se han actualizado " + sentenciaPreparada.executeUpdate() + " filas.");

//      LIBERAMOS RECURSOS
        sentenciaPreparada.close();

        System.out.println("¡DEPARTAMENTO INSERTADO CORRECTAMENTE!");
    }

    public static void opcion2GestionDepartamentos(Connection conexion) throws SQLException {
//        ENUNCIADO: Modificar un departamento
        Departamento departamentoModificado = new Departamento();
        //Pedimos departamento al usuario
        int dept_no_user = pedirEntero("Introduzca el 'dept_no' del departamento que quieres modificar:");
        //SENTENCIA DE BÚSQUEDA
        String sql = "SELECT * FROM DEPARTAMENTOS WHERE dept_no = ?";
        PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql);
        sentenciaPreparada.setInt(1, dept_no_user);
        ResultSet resultado = sentenciaPreparada.executeQuery();
        resultado.next();//Sacamos el primer y único resultado teórico.
        //Recorremos el resultado mientras preguntamos las modificaciones
        System.out.println("===== MODIFICANDO DEPARTAMENTO =====");
        //dnombre
        if (seguir("¿Desea modificar el nombre del departamento?")) {
            System.out.println("El nombre actual es " + resultado.getString("dnombre"));
            departamentoModificado.setDnombre(pedirString("Introduzca el nombre modificado:"));
        }
        //loc
        if (seguir("¿Desea modificar la localización del departamento?")) {
            System.out.println("La localización actual es " + resultado.getString("loc"));
            departamentoModificado.setLoc(pedirString("Introduzca la localización modificada:"));
        }
        //SENTENCIA DE ACTUALIZACIÓN
        sql = "UPDATE DEPARTAMENTOS SET dnombre = ?,loc = ? WHERE dept_no = ?";
        sentenciaPreparada = conexion.prepareStatement(sql);
        //dnombre
        if (departamentoModificado.getDnombre() != null) {
            sentenciaPreparada.setString(1, departamentoModificado.getDnombre());
        } else {
            sentenciaPreparada.setString(1, resultado.getString("dnombre"));
        }
        //loc
        if (departamentoModificado.getLoc() != null) {
            sentenciaPreparada.setString(2, departamentoModificado.getLoc());
        } else {
            sentenciaPreparada.setString(2, resultado.getString("loc"));
        }
        //dept_no
        sentenciaPreparada.setInt(3, dept_no_user);

        System.out.println("Se han actualizado " + sentenciaPreparada.executeUpdate() + " filas.");
//      LIBERAMOS RECURSOS
        resultado.close();
        sentenciaPreparada.close();

        System.out.println("¡DEPARTAMENTO ACTUALIZADO CORRECTAMENTE!");
    }

    public static void opcion3GestionDepartamentos(Connection conexion) throws SQLException {
//        ENUNCIADO: Eliminar departamento
        //Pedimos el dept_no al usuario
        int dept_no_user = pedirEntero("Introduzca el 'dept_no' del departamento que quieres eliminar:");
        //SENTENCIA PARA ELIMINAR
        String sql = "DELETE FROM DEPARTAMENTOS WHERE dept_no = ?";
        PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql);
        sentenciaPreparada.setInt(1, dept_no_user);
        System.out.println("Se han actualizado " + sentenciaPreparada.executeUpdate() + " filas.");
//      LIBERAMOS RECURSOS
        sentenciaPreparada.close();

        System.out.println("¡DEPARTAMENTO ELIMINADO CORRECTAMENTE!");
    }

    public static void opcion4GestionDepartamentos(Connection conexion) throws SQLException {
//        ENUNCIADO: Consultar todos los departamentos
        //SENTENCIA DE BÚSQUEDA
        Statement sentencia = conexion.createStatement();
        String sql = "SELECT * FROM DEPARTAMENTOS";
        ResultSet resultado = sentencia.executeQuery(sql);
        System.out.println("===== DEPARTAMENTOS =====");
        while (resultado.next()) {
            System.out.println("---------------------");
            System.out.println("dept_no: " + resultado.getInt("dept_no"));
            System.out.println("dnombre: " + resultado.getString("dnombre"));
            System.out.println("loc: " + resultado.getString("loc"));
        }
        System.out.println("===== FIN DE DEPARTAMENTOS =====");

//        LIBERAMOS RECURSOS
        resultado.close();
        sentencia.close();
    }

    public static void opcion5GestionDepartamentos(Connection conexion) throws SQLException {
//        ENUNCIADO: Ver información de un único departamento por nombre
        System.out.println("===== BÚSQUEDA DE DEPARTAMENTO POR NOMBRE =====");
        //Pedimos nombre al usuario
        String dnombre_user = pedirString("Introduce el nombre del departamento:");
        //CONSULTA DE BÚSQUEDA
        String sql = "SELECT * FROM DEPARTAMENTOS WHERE dnombre = ?";
        PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql);
        sentenciaPreparada.setString(1, dnombre_user);
        ResultSet resultado = sentenciaPreparada.executeQuery();
        resultado.next();
//        Muestro el departamento
        System.out.println("dept_no: " + resultado.getInt("dept_no"));
        System.out.println("dnombre: " + resultado.getString("dnombre"));
        System.out.println("loc: " + resultado.getString("loc"));
        System.out.println("===============================================");

//        LIBERO RECURSOS
        resultado.close();
        sentenciaPreparada.close();
    }

    public static void opcion6GestionDepartamentos(Connection conexion) throws SQLException {
//        ENUNCIADO: Ver información de un único departamento por nombre
        System.out.println("===== BÚSQUEDA DE DEPARTAMENTO POR ID =====");
        //Pedimos nombre al usuario
        int dept_no_user = pedirEntero("Introduce el 'dept_no' del departamento:");
        //CONSULTA DE BÚSQUEDA
        String sql = "SELECT * FROM DEPARTAMENTOS WHERE dept_no = ?";
        PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql);
        sentenciaPreparada.setInt(1, dept_no_user);
        ResultSet resultado = sentenciaPreparada.executeQuery();
        resultado.next();
//        Muestro el departamento
        System.out.println("dept_no: " + resultado.getInt("dept_no"));
        System.out.println("dnombre: " + resultado.getString("dnombre"));
        System.out.println("loc: " + resultado.getString("loc"));
        System.out.println("============================================");

//        LIBERO RECURSOS
        resultado.close();
        sentenciaPreparada.close();
    }
    //=======================================================================

}
