package gestionVentas;

import general.Utileria;

import java.sql.*;

import static general.Constantes.OPCION_INVALIDA;
import static general.Constantes.SQLEXCEPTION;

public class GestionVentas {

    //MENÚ 'Gestión de Ventas'
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
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println(OPCION_INVALIDA);
                    break;
            }
        } while (opcion != 4);
    }

    //    Mostrar menú
    private static int mostrarMenu() {
        System.out.println("\n***********************************************");
        System.out.println("*********** M E N U   G E S T I Ó N ***********");
        System.out.println("***********           D E           ***********");
        System.out.println("***********       V E N T A S       ***********");
        System.out.println("***********************************************");
        System.out.println("1.- Realizar una venta.");
        System.out.println("2.- Listar ventas.");
        System.out.println("3.- Listar todas las ventas de un día concreto.");
        System.out.println("4.- Salir.");
        System.out.println("*********************************************");
        return Utileria.pedirEntero("Introduzca una opción: ");
    }

    //OPCIÓN 1 - INSERTAR VENTA
    private static void opcion1(Connection conexion) {
        boolean insertCorrecto = true;
        System.out.println("******************** REALIZAR VENTA ********************");
        //Creamos un objeto venta y rellenamos información
        Venta venta = new Venta();
        venta.rellenar();
        //Creamos sentencia insert
        String sql = "INSERT INTO VENTAS VALUES (?,?,?,?,?,?)";
        PreparedStatement sentenciaPreparada = null;
        try {
            sentenciaPreparada = conexion.prepareStatement(sql);
            sentenciaPreparada.setInt(1, (Utileria.numeroVentas(conexion) + 1));
            sentenciaPreparada.setString(2, venta.getMatricula());
            sentenciaPreparada.setString(3, venta.getNifCliente());
            sentenciaPreparada.setFloat(4, venta.getDescuento());
            sentenciaPreparada.setString(5, venta.getMotivoDesc());
            sentenciaPreparada.setDate(6, venta.getFechaVenta());
            sentenciaPreparada.executeUpdate();
            //Liberamos recursos
            sentenciaPreparada.close();
        } catch (SQLException e) {
            insertCorrecto = false;
        }
        //Check
        if (insertCorrecto) {
            System.out.println("\n¡VENTA INSERTADA CORRECTAMENTE!");
        } else {
            if (!Utileria.existeMatricula(conexion, venta.getMatricula())) {
                System.out.println("\nERROR: LA MATRÍCULA INTRODUCIDA NO PERTENECE A NINGÚN VEHÍCULO.");
            }
            if (!Utileria.existeCliente(conexion, venta.getNifCliente())) {
                System.out.println("\nERROR: EL NIF INTRODUCIDO NO PERTENECE A NINGÚN CLIENTE.");
            }
        }
    }

    //OPCIÓN 2 - LISTAR VENTAS
    private static void opcion2(Connection conexion) {
        System.out.println("******************** LISTAR VENTAS ********************");
        try {
            Statement sentencia = conexion.createStatement();
            String sql = "SELECT * FROM VENTAS";
            ResultSet resultado = sentencia.executeQuery(sql);
            while (resultado.next()) {
                Venta venta = new Venta(resultado.getInt("id"), resultado.getString("matricula"), resultado.getString("nifCliente"), resultado.getFloat("descuento"), resultado.getString("motivoDescuento"), resultado.getDate("fechaVenta"));
                venta.mostrar();
            }
            //Liberamos recursos
            sentencia.close();
            resultado.close();
        } catch (SQLException e) {
            System.out.println(SQLEXCEPTION);
        }
    }

    //OPCIÓN 3 - LISTAR VENTAS DÍA CONCRETO
    private static void opcion3(Connection conexion) {
        System.out.println("******************** LISTAR VENTAS DÍA CONCRETO********************");
        Date fechaBuscada = Utileria.pedirDate("Introduzca la fecha buscada en formato '1998-08-27': ");
        try {
            String sql = "SELECT * FROM VENTAS WHERE fechaVenta = ?";
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql);
            sentenciaPreparada.setDate(1, fechaBuscada);
            ResultSet resultado = sentenciaPreparada.executeQuery();
            if (!resultado.next()) {
                System.out.println("--- NO SE ENCONTRÓ NINGUNA VENTA REGISTRADA ESE DÍA ---");
                return;//Salgo del método
            }
            do {
                Venta venta = new Venta(resultado.getInt("id"), resultado.getString("matricula"), resultado.getString("nifCliente"), resultado.getFloat("descuento"), resultado.getString("motivoDescuento"), resultado.getDate("fechaVenta"));
                venta.mostrar();
            } while (resultado.next());
            //Liberamos recursos
            sentenciaPreparada.close();
            resultado.close();
        } catch (SQLException e) {
            System.out.println(SQLEXCEPTION);
        }
    }
}
