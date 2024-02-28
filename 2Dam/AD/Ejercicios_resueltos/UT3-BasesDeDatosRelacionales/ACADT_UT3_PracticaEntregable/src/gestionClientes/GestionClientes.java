package gestionClientes;

import general.Utileria;

import static general.Constantes.*;

import java.sql.*;

public class GestionClientes {

    //MENÚ 'Gestión de Clientes'
    public static void menu(Connection conexion) {
        int opcion;
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
                    System.out.println(OPCION_INVALIDA);
                    break;
            }
        } while (opcion != 5);
    }
    //    Mostrar menú
    private static int mostrarMenu() {
        System.out.println("\n***********************************************");
        System.out.println("*********** M E N U   G E S T I Ó N ***********");
        System.out.println("***********           D E           ***********");
        System.out.println("***********     C L I E N T E S     ***********");
        System.out.println("***********************************************");
        System.out.println("1.- Insertar cliente.");
        System.out.println("2.- Modificar cliente.");
        System.out.println("3.- Listar clientes.");
        System.out.println("4.- Consultar un cliente por NIF.");
        System.out.println("5.- Salir.");
        System.out.println("*********************************************");
        return Utileria.pedirEntero("Introduzca una opción: ");
    }


    //OPCIÓN 1 - INSERTAR CLIENTE
    private static void opcion1(Connection conexion) {
        System.out.println("******************** INSERTAR CLIENTE ********************");
        //Creamos un objeto cliente y rellenamos información
        Cliente cliente = new Cliente();
        cliente.rellenar();
        //Creamos sentencia insert
        String sql = "INSERT INTO CLIENTES VALUES (?,?,?)";
        PreparedStatement sentenciaPreparada = null;
        try {
            sentenciaPreparada = conexion.prepareStatement(sql);
            sentenciaPreparada.setString(1, cliente.getNif());
            sentenciaPreparada.setString(2, cliente.getNombre());
            sentenciaPreparada.setString(3, cliente.getApellidos());
            sentenciaPreparada.executeUpdate();
            //Liberamos recursos
            sentenciaPreparada.close();
        } catch (SQLException e) {
            System.out.println(SQLEXCEPTION);
        }
        //Check
        System.out.println("\n¡CLIENTE INSERTADO CORRECTAMENTE!");
    }

    //OPCIÓN 2 - MODIFICAR CLIENTE
    private static void opcion2(Connection conexion) {
        System.out.println("******************** MODIFICAR CLIENTE ********************");
        String nifBuscado = Utileria.pedirString("Por favor, introduzca el NIF del cliente que desea modificar: ");
        Cliente clienteBuscado = Utileria.buscarClienteNif(conexion, nifBuscado);

        //Si no existe ningún cliente con ese nif salgo del método
        if (clienteBuscado == null) {
            System.out.println("--- NO SE ENCONTRÓ NINGÚN CLIENTE CON ESE NIF ---");
            return;//Salgo del método
        }

        //SENTENCIA DE ACTUALIZACIÓN
        if (Utileria.seguir("Su nombre es: " + clienteBuscado.getNombre() + ". ¿Desea modificar el campo nombre?")) {
            clienteBuscado.setNombre(Utileria.pedirString("Introduzca el nombre modificado: "));
        }
        if (Utileria.seguir("Sus apellidos son: " + clienteBuscado.getApellidos() + ". ¿Desea modificar el campo apellidos?")) {
            clienteBuscado.setApellidos(Utileria.pedirString("Introduzca los apellidos modificados: "));
        }
        //Sql
        String sql = "UPDATE CLIENTES SET NOMBRE = ?,APELLIDOS = ? WHERE NIF = ?";
        try {
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql);
            sentenciaPreparada.setString(1, clienteBuscado.getNombre());
            sentenciaPreparada.setString(2, clienteBuscado.getApellidos());
            sentenciaPreparada.setString(3, nifBuscado);
            sentenciaPreparada.executeUpdate();
            //Liberamos recusos
            sentenciaPreparada.close();
        } catch (SQLException e) {
            System.out.println(SQLEXCEPTION);
        }
        //Check
        System.out.println("¡CLIENTE MODIFICADO CON ÉXITO!");
    }

    //OPCIÓN 3 - LISTAR CLIENTES
    private static void opcion3(Connection conexion) {
        System.out.println("******************** LISTAR CLIENTES ********************");
        try {
            Statement sentencia = conexion.createStatement();
            String sql = "SELECT * FROM CLIENTES";
            ResultSet resultado = sentencia.executeQuery(sql);
            while (resultado.next()) {
                Cliente cliente = new Cliente(resultado.getString("nif"), resultado.getString("nombre"), resultado.getString("apellidos"));
                cliente.mostrar();
            }
            //Liberamos recursos
            sentencia.close();
            resultado.close();
        } catch (SQLException e) {
            System.out.println(SQLEXCEPTION);
        }
    }

    //OPCIÓN 4 - CONSULTAR CLIENTE POR NIF
    private static void opcion4(Connection conexion) {
        System.out.println("******************** CONSULTAR CLIENTE POR NIF ********************");
        String nifBuscado = Utileria.pedirString("Por favor, introduzca el NIF del cliente que desea buscar: ");
        Cliente cliente = Utileria.buscarClienteNif(conexion, nifBuscado);
        if (cliente != null) {
            cliente.mostrar();
        } else {
            System.out.println("--- NO SE ENCONTRÓ NINGÚN CLIENTE CON ESE NIF ---");
        }
    }
}
