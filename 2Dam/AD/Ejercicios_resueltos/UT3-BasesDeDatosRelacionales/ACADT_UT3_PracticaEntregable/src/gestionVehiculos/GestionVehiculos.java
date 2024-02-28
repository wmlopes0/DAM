package gestionVehiculos;

import general.Utileria;

import java.sql.*;

import static general.Constantes.OPCION_INVALIDA;
import static general.Constantes.SQLEXCEPTION;

public class GestionVehiculos {

    //MENÚ 'Gestión de Vehículos'
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
                    opcion5(conexion);
                    break;
                case 6:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println(OPCION_INVALIDA);
                    break;
            }
        } while (opcion != 6);
    }

    //    Mostrar menú
    private static int mostrarMenu() {
        System.out.println("\n*************************************************");
        System.out.println("************ M E N U   G E S T I Ó N ************");
        System.out.println("************           D E           ************");
        System.out.println("************    V E H Í C U L O S    ************");
        System.out.println("*************************************************");
        System.out.println("1.- Insertar vehículo.");
        System.out.println("2.- Modificar vehículo.");
        System.out.println("3.- Listar vehículos.");
        System.out.println("4.- Consultar un vehículo por matrícula.");
        System.out.println("5.- Ver todos los vehículos de una marca.");
        System.out.println("6.- Salir.");
        System.out.println("*********************************************");
        return Utileria.pedirEntero("Introduzca una opción: ");
    }


    //OPCIÓN 1 - INSERTAR VEHÍCULO
    private static void opcion1(Connection conexion) {
        System.out.println("******************** INSERTAR VEHÍCULO ********************");
        //Creamos un objeto vehículo y rellenamos información
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.rellenar();
        //Creamos sentencia insert
        String sql = "INSERT INTO VEHICULOS VALUES (?,?,?,?)";
        PreparedStatement sentenciaPreparada = null;
        try {
            sentenciaPreparada = conexion.prepareStatement(sql);
            sentenciaPreparada.setString(1, vehiculo.getMatricula());
            sentenciaPreparada.setString(2, vehiculo.getMarca());
            sentenciaPreparada.setString(3, vehiculo.getColor());
            sentenciaPreparada.setFloat(4, vehiculo.getPrecio());
            sentenciaPreparada.executeUpdate();
            //Liberamos recursos
            sentenciaPreparada.close();
        } catch (SQLException e) {
            System.out.println(SQLEXCEPTION);
        }
        //Check
        System.out.println("\n¡VEHÍCULO INSERTADO CORRECTAMENTE!");
    }

    //OPCIÓN 2 - MODIFICAR VEHÍCULO
    private static void opcion2(Connection conexion) {
        System.out.println("******************** MODIFICAR VEHÍCULO ********************");
        String matriculaBuscada = Utileria.pedirString("Por favor, introduzca la matrícula del vehículo que desea modificar: ");
        Vehiculo vehiculoBuscado = Utileria.buscarVehiculoMatricula(conexion, matriculaBuscada);

        //Si no existe ningún vehículo con esa matrícula salgo del método
        if (vehiculoBuscado == null) {
            System.out.println("--- NO SE ENCONTRÓ NINGÚN VEHÍCULO CON ESA MATRÍCULA ---");
            return;//Salgo del método
        }

        //SENTENCIA DE ACTUALIZACIÓN
        if (Utileria.seguir("Su marca es: " + vehiculoBuscado.getMarca() + ". ¿Desea modificar el campo marca?")) {
            vehiculoBuscado.setMarca(Utileria.pedirString("Introduzca la marca modificada: "));
        }
        if (Utileria.seguir("Su color es: " + vehiculoBuscado.getColor() + ". ¿Desea modificar el campo color?")) {
            vehiculoBuscado.setColor(Utileria.pedirString("Introduzca el color modificado: "));
        }
        if (Utileria.seguir("Su precio es: " + vehiculoBuscado.getPrecio() + ". ¿Desea modificar el campo precio?")) {
            vehiculoBuscado.setPrecio(Utileria.pedirFloat("Introduzca el precio modificado: "));
        }
        //Sql
        String sql = "UPDATE VEHICULOS SET MARCA = ?,COLOR = ?,PRECIO = ? WHERE MATRICULA = ?";
        try {
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql);
            sentenciaPreparada.setString(1, vehiculoBuscado.getMarca());
            sentenciaPreparada.setString(2, vehiculoBuscado.getColor());
            sentenciaPreparada.setFloat(3, vehiculoBuscado.getPrecio());
            sentenciaPreparada.setString(4, matriculaBuscada);
            sentenciaPreparada.executeUpdate();
            //Liberamos recusos
            sentenciaPreparada.close();
        } catch (SQLException e) {
            System.out.println(SQLEXCEPTION);
        }
        //Check
        System.out.println("¡VEHÍCULO MODIFICADO CON ÉXITO!");
    }

    //OPCIÓN 3 - LISTAR VEHÍCULOS
    private static void opcion3(Connection conexion) {
        System.out.println("******************** LISTAR VEHÍCULOS ********************");
        try {
            Statement sentencia = conexion.createStatement();
            String sql = "SELECT * FROM VEHICULOS";
            ResultSet resultado = sentencia.executeQuery(sql);
            while (resultado.next()) {
                Vehiculo vehiculo = new Vehiculo(resultado.getString("matricula"), resultado.getString("marca"), resultado.getString("color"), resultado.getFloat("precio"));
                vehiculo.mostrar();
            }
            //Liberamos recursos
            sentencia.close();
            resultado.close();
        } catch (SQLException e) {
            System.out.println(SQLEXCEPTION);
        }
    }

    //OPCIÓN 4 - CONSULTAR VEHÍCULO POR MATRÍCULA
    private static void opcion4(Connection conexion) {
        System.out.println("******************** CONSULTAR VEHÍCULO POR MATRÍCULA ********************");
        String matriculaBuscada = Utileria.pedirString("Por favor, introduzca la matrícula del vehículo que desea buscar: ");
        Vehiculo vehiculo = Utileria.buscarVehiculoMatricula(conexion, matriculaBuscada);
        if (vehiculo != null) {
            vehiculo.mostrar();
        } else {
            System.out.println("--- NO SE ENCONTRÓ NINGÚN VEHÍCULO CON ESA MATRÍCULA ---");
        }
    }

    //OPCIÓN 5 - VER TODOS LOS VEHÍCULOS DE UNA MARCA
    private static void opcion5(Connection conexion) {
        System.out.println("******************** LISTAR VEHÍCULOS POR MARCA ********************");
        String marcaBuscada = Utileria.pedirString("Por favor, introduzca la marca que desea buscar: ");

        //Sentencia de búsqueda
        String sql = "SELECT * FROM VEHICULOS WHERE MARCA = ?";
        try {
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql);
            sentenciaPreparada.setString(1, marcaBuscada);
            ResultSet resultado = sentenciaPreparada.executeQuery();
            if (!resultado.next()) {
                System.out.println("--- NO SE ENCONTRÓ NINGÚN VEHÍCULO DE ESA MARCA ---");
            } else {
                do {
                    Vehiculo vehiculo = new Vehiculo(resultado.getString("matricula"), resultado.getString("marca"), resultado.getString("color"), resultado.getFloat("precio"));
                    vehiculo.mostrar();
                } while (resultado.next());
            }
            //Liberamos recursos
            sentenciaPreparada.close();
            resultado.close();
        } catch (SQLException e) {
            System.out.println(SQLEXCEPTION);
        }
    }

}
