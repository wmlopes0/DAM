package acadt_ut3_p2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.time.DateTimeException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import static acadt_ut3_p2.Main.PUERTO;

public class Utileria {
    //ESTABLECER CONEXIÓN
    public static Connection establecerConexion() throws ClassNotFoundException, SQLException {
        //Cargamos el driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Establecemos la conexión
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:" + PUERTO + "/empresa", "root", "");
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
            System.out.println("ERROR:No puedes introducir una letra.");
            entero = pedirEntero(texto);
        }
        return entero;
    }

    //PEDIR FLOAT
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

    //PEDIR STRING
    public static String pedirString(String texto) {
        Scanner s = new Scanner(System.in);
        System.out.println(texto);
        return s.nextLine();
    }

    //PEDIR DATE
    public static Date pedirDate() {
        Scanner s = new Scanner(System.in);
        LocalDate fecha;
        String fechaString = pedirString("Introduce la fecha en formato '1998-08-27':");
        String[] fechaArray = fechaString.split("-");
        try {
            fecha = LocalDate.of(Integer.valueOf(fechaArray[0]), Integer.valueOf(fechaArray[1]), Integer.valueOf(fechaArray[2]));
        } catch (DateTimeException e) {
            System.out.println("ERROR:Por favor, introduzca una fecha válida.");
            return pedirDate();
        }
        return Date.valueOf(fecha);
    }

    //SIMULACIÓN ESPERA
    public static void simulacionEspera() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            System.out.print(" .");
            Thread.sleep(1000);
        }
    }

    //SI O NO
    public static boolean seguir(String texto){
        Scanner s = new Scanner (System.in);
        System.out.println(texto);
        return s.nextLine().equalsIgnoreCase("si");
    }
}
