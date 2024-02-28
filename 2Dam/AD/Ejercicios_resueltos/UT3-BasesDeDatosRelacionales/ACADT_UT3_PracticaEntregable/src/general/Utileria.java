package general;

import gestionClientes.Cliente;
import gestionVehiculos.Vehiculo;

import java.sql.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import static general.Constantes.*;

public class Utileria {

    //ESTABLECER CONEXIÓN
    public static Connection establecerConexion(int puerto, String bd, String usuario, String psw) {
        //Cargamos el driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(CLASS_NOT_FOUND_EXCEPTION);
        }
        //Establecemos la conexión
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:" + puerto + "/" + bd, usuario, psw);
        } catch (SQLException e) {
            System.out.println(CONEXION_FALLIDA);
            return conexion;
        }
        System.out.println("\n ¡CONEXIÓN REALIZADA CORRECTAMENTE!");
        return conexion;
    }

    //PEDIR ENTERO
    public static int pedirEntero(String texto) {
        Scanner s = new Scanner(System.in);
        int entero;
        System.out.println(texto);
        try {
            entero = s.nextInt();
        } catch (InputMismatchException e) {
            System.out.println(INPUT_MISMATCH_EXCEPTION);
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
            System.out.println(INPUT_MISMATCH_EXCEPTION);
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
            System.out.println(FECHA_INVALIDA);
            return pedirDate(texto);
        }
        return Date.valueOf(fecha);
    }

    //SIMULACIÓN ESPERA
    public static void simulacionEspera(String texto) {
        System.out.print("\n" + texto);
        try {
            for (int i = 0; i < 3; i++) {
                System.out.print(" .");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println(INTERRUPTED_EXCEPTION);
        }
        System.out.println();//Salto de línea
    }

    //SI O NO
    public static boolean seguir(String texto) {
        Scanner s = new Scanner(System.in);
        System.out.println(texto);
        return s.nextLine().equalsIgnoreCase("si");
    }

    //BUSCAR CLIENTE POR NIF
    public static Cliente buscarClienteNif(Connection conexion, String nif) {
        //Este método retorna un objeto cliente si lo encuentra en la base de datos, en caso contrario devuelve null
        Cliente cliente = null;
        String sql = "SELECT * FROM CLIENTES WHERE NIF = ?";
        try {
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql);
            sentenciaPreparada.setString(1, nif);
            ResultSet resultado = sentenciaPreparada.executeQuery();
            if (resultado.next()) {
                cliente = new Cliente(resultado.getString("nif"), resultado.getString("nombre"), resultado.getString("apellidos"));
            }
            //Liberamos recursos
            sentenciaPreparada.close();
            resultado.close();

        } catch (SQLException e) {
            System.out.println(SQLEXCEPTION);
        }
        return cliente;//RETORNO CLIENTE
    }

    //BUSCAR VEHÍCULO POR MATRÍCULA
    public static Vehiculo buscarVehiculoMatricula(Connection conexion, String matricula) {
        //Este método retorna un objeto vehículo si lo encuentra en la base de datos, en caso contrario devuelve null
        Vehiculo vehiculo = null;
        String sql = "SELECT * FROM VEHICULOS WHERE MATRICULA = ?";
        try {
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql);
            sentenciaPreparada.setString(1, matricula);
            ResultSet resultado = sentenciaPreparada.executeQuery();
            if (resultado.next()) {
                vehiculo = new Vehiculo(resultado.getString("matricula"), resultado.getString("marca"), resultado.getString("color"), resultado.getFloat("precio"));
            }
            //Liberamos recursos
            sentenciaPreparada.close();
            resultado.close();

        } catch (SQLException e) {
            System.out.println(SQLEXCEPTION);
        }
        return vehiculo;//RETORNO VEHÍCULO
    }

    //RETORNAR NUMERO DE VENTAS
    public static int numeroVentas(Connection conexion) {
        int numVentas = 0;
        String sql = "SELECT COUNT(*) FROM VENTAS";
        Statement sentencia = null;
        try {
            sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);
            if (resultado.next()) {
                numVentas = resultado.getInt(1);
            }
            //Liberamos recursos
            sentencia.close();
            resultado.close();

        } catch (SQLException e) {
            System.out.println(SQLEXCEPTION);
        }
        return numVentas;
    }

    //COMPROBAR SI EXISTE MATRICULA
    public static boolean existeMatricula(Connection conexion, String matriculaBuscada) {
        boolean existe = false;
        String sql = "SELECT * FROM VEHICULOS WHERE MATRICULA = ?";
        try {
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql);
            sentenciaPreparada.setString(1, matriculaBuscada);
            ResultSet resultado = sentenciaPreparada.executeQuery();
            //Si devuelve algun registro significa que existe
            if (resultado.next()) {
                existe = true;
            }
            //Liberamos recursos
            sentenciaPreparada.close();
            resultado.close();

        } catch (SQLException e) {
            System.out.println(SQLEXCEPTION);
        }
        return existe;
    }

    //COMPROBAR SI EXISTE CLIENTE
    public static boolean existeCliente(Connection conexion, String nifClienteBuscado) {
        boolean existe = false;
        String sql = "SELECT * FROM CLIENTES WHERE nif = ?";
        try {
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql);
            sentenciaPreparada.setString(1, nifClienteBuscado);
            ResultSet resultado = sentenciaPreparada.executeQuery();
            //Si devuelve algun registro significa que existe
            if (resultado.next()) {
                existe = true;
            }
            //Liberamos recursos
            sentenciaPreparada.close();
            resultado.close();

        } catch (SQLException e) {
            System.out.println(SQLEXCEPTION);
        }
        return existe;
    }
}
