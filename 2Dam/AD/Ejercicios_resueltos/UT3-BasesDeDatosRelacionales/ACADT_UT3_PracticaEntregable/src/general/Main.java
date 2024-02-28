package general;

import gestionClientes.GestionClientes;
import gestionVehiculos.GestionVehiculos;
import gestionVentas.GestionVentas;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static general.Constantes.*;

public class Main {

    public static void main(String[] args) {
//        Muestro bienvenida
        mostrarBienvenida();
        Utileria.simulacionEspera("ESTABLECIENDO CONEXIÓN CON LA BASE DE DATOS");
//        Establezco la conexión
        Connection conexion = Utileria.establecerConexion(PUERTO, BD, USUARIO, PSW);
//        Fuerzo la finalización del programa si la conexión ha sido fallida.
        if (conexion == null) return;
//        Creo estructura de tablas automaticamente e introduzco datos de ejemplo
        LogicaDeNegocio.crearEstructuraTablas(conexion);
        LogicaDeNegocio.insertarDatosEjemplo(conexion);
//        MENÚ GENERAL================================================================
        int opcion;
        do {
            opcion = mostrarMenuGeneral();
            switch (opcion) {
                case 1:
                    GestionClientes.menu(conexion);
                    break;
                case 2:
                    GestionVehiculos.menu(conexion);
                    break;
                case 3:
                    GestionVentas.menu(conexion);
                    break;
                case 4:
                    System.out.println("\n*********************************************************");
                    System.out.println("*********** G R A C I A S   P O R   U S A R *************");
                    System.out.println("***********          N U E S T R O          *************");
                    System.out.println("***********         S O F T W A R E         *************");
                    System.out.println("*********************************************************");
                    break;
                default:
                    System.out.println(OPCION_INVALIDA);
                    break;
            }
        } while (opcion != 4);

        //Liberamos recursos
        try {
            conexion.close();
        } catch (SQLException e) {
            System.out.println(SQLEXCEPTION);
        }
    }

    //    Bienvenida
    public static void mostrarBienvenida() {
        System.out.println("\n*********************************************************");
        System.out.println("***************** Verstappen Inc. S.A. ******************");
        System.out.println("*********************************************************");
        System.out.println("*                                                       *");
        System.out.println("* Bienvenido a la aplicación de gestión de nuestra      *");
        System.out.println("* empresa, con esta herramienta podrás gestionar        *");
        System.out.println("* todo lo relacionado con la compra-venta de vehículos. *");
        System.out.println("*                                                       *");
        System.out.println("--------- [APLICACIÓN DESARROLLADA POR WALTER] ----------");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println();
        }
    }

    //    Menú general
    public static int mostrarMenuGeneral() {
        System.out.println("\n*********************************************");
        System.out.println("********** M E N U   G E N E R A L **********");
        System.out.println("*********************************************");
        System.out.println("1.- Gestión de clientes.");
        System.out.println("2.- Gestión de vehículos.");
        System.out.println("3.- Gestión de ventas.");
        System.out.println("4.- Salir.");
        System.out.println("*********************************************");
        return Utileria.pedirEntero("Introduzca una opción: ");
    }

}
