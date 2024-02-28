package ejercicio5;

import com.mysql.cj.xdevapi.FindStatementImpl;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio5 {

    //Variables globales
    final static int PUERTO = 3306;

    public static void main(String[] args) throws InterruptedException, SQLException, ClassNotFoundException {
        int opcion;
        Connection conexion;
//        Mensaje de espera
        System.out.println("CONECTANDO A LA BASE DE DATOS");
        simulacionEspera();
        conexion = establecerConexion();
        System.out.println("\n¡CONEXIÓN REALIZADA CORRECTAMENTE!");
        Thread.sleep(1000);
//        Fin de mensaje de espera

        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    opcion1(conexion);
                    break;
                case 2:
                    opcion2(conexion);
                    break;
                case 3:
                    opcion3(conexion);
                    break;
                case 4:
                    opcion4(conexion);
                    break;
                case 5:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("Error:Introduzca una opción válida.");
                    break;

            }
        } while (opcion != 5);

        //Liberamos recursos
        conexion.close();
    }

    private static void simulacionEspera() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            System.out.print(" .");
            Thread.sleep(1000);
        }
    }

    public static int mostrarMenu() {
        Scanner s = new Scanner(System.in);
        int opcion;
        System.out.println("==== MENU ====");
        System.out.println("1.- Consultar todos los empleados.");
        System.out.println("2.- Consultar empleados por el campo 'emp_no'");
        System.out.println("3.- Consultar empleados que tengan un salario superior al introducido.");
        System.out.println("4.- Consultar empleados que tengan un salario igual o inferior al introducido.");
        System.out.println("5.- Salir.");
        System.out.println("===============");
        System.out.println("Por favor, introduzca una opción: ");
        try {
            opcion = s.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: No puedes introducir una letra.");
            opcion = mostrarMenu();
        }
        return opcion;
    }

    public static Connection establecerConexion() throws ClassNotFoundException, SQLException {
        //Cargamos el driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Establecemos la conexión
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:" + PUERTO + "/empresa", "root", "");
        return conexion;
    }

    public static void opcion1(Connection conexion) throws SQLException {
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

//        Liberamos recursos
        sentencia.close();
        resultado.close();
    }

    public static void opcion2(Connection conexion) throws SQLException {
//       ENUNCIADO:  Consultar empleados por el campo “emp_no”.
        System.out.println("========= EMPLEADOS =========");
        int emp_no = pedirEntero("Introduzca el número de empleado: ");
        //Ejecutamos sentencias
        Statement sentencia = conexion.createStatement();
        String sql = "SELECT emp_no,apellido,oficio,fecha_alt,salario,dept_no FROM EMPLEADOS WHERE emp_no = " + String.valueOf(emp_no);
        ResultSet resultado = sentencia.executeQuery(sql);//Resultado es como un iterador
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

//        Liberamos recursos
        sentencia.close();
        resultado.close();
    }

    public static void opcion3(Connection conexion) throws SQLException {
//       ENUNCIADO:   Consultar empleados que tengan un salario superior al introducido por el usuario.
        System.out.println("========= EMPLEADOS =========");
        float salario = pedirFloat("Introduzca un salario: ");
        //Ejecutamos sentencias
        Statement sentencia = conexion.createStatement();
        String sql = "SELECT emp_no,apellido,oficio,fecha_alt,salario,dept_no FROM EMPLEADOS WHERE salario > " + String.valueOf(salario);
        ResultSet resultado = sentencia.executeQuery(sql);//Resultado es como un iterador
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

//        Liberamos recursos
        sentencia.close();
        resultado.close();
    }

    public static void opcion4(Connection conexion) throws SQLException {
//       ENUNCIADO:   Consultar empleados que tengan un salario superior al introducido por el usuario.
        System.out.println("========= EMPLEADOS =========");
        float salario = pedirFloat("Introduzca un salario: ");
        //Ejecutamos sentencias
        Statement sentencia = conexion.createStatement();
        String sql = "SELECT emp_no,apellido,oficio,fecha_alt,salario,dept_no FROM EMPLEADOS WHERE salario <= " + String.valueOf(salario);
        ResultSet resultado = sentencia.executeQuery(sql);//Resultado es como un iterador
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

//        Liberamos recursos
        sentencia.close();
        resultado.close();
    }

    public static int pedirEntero(String texto) {
        Scanner s = new Scanner(System.in);
        int entero;
        System.out.println(texto);
        try {
            entero = s.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ERROR:No puedes introducir una letra.");
            entero = pedirEntero(texto);
        }
        return entero;
    }

    public static float pedirFloat(String texto) {
        Scanner s = new Scanner(System.in);
        float entero;
        System.out.println(texto);
        try {
            entero = s.nextFloat();
        } catch (InputMismatchException e) {
            System.out.println("ERROR:No puedes introducir una letra.");
            entero = pedirEntero(texto);
        }
        return entero;
    }
}
