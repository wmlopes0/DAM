package ejercicio1;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author final23
 */
public class Alarma implements Serializable {

    private String nombreTeleoperador;
    private String nombreUsuario;
    private LocalDate fecha;

    //CONSTRUCTORES
    public Alarma() {
        nombreTeleoperador = "";
        nombreUsuario = "";
        fecha = LocalDate.now();
    }

    public Alarma(String nombreTeleoperador, String nombreUsuario, LocalDate fecha) {
        this.nombreTeleoperador = nombreTeleoperador;
        this.nombreUsuario = nombreUsuario;
        this.fecha = fecha;
    }

    //SETTER Y GETTER
    public void setNombreTeleoperador(String nombreTeleoperador) {
        this.nombreTeleoperador = nombreTeleoperador;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getNombreTeleoperador() {
        return nombreTeleoperador;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    //METODOS PROPIOS
    public void rellenarInfo() {
        System.out.println("***** A L A R M A *****");
        nombreTeleoperador = pedirCadena("Introduce el nombre del teleoperador: ");
        nombreUsuario = pedirCadena("Introduce el nombre del usuario: ");
        fecha = pedirFecha();
    }

    public void mostrarInfo() {
        System.out.println("***** A L A R M A *****");
        System.out.println("Nombre del Teleoperador: " + nombreTeleoperador);
        System.out.println("Nombre del Usuario: " + nombreUsuario);
        System.out.println("Fecha: " + fecha);
    }

    /*Estos métodos de pedir son privados porque no tiene sentido llamarlos si no es desde
    el método rellenar info*/
    private String pedirCadena(String mensaje) {
        Scanner entrada = new Scanner(System.in);
        System.out.println(mensaje);
        return entrada.nextLine();
    }

    //Este metodo pide la fecha y controla que sea valida, si no es valida la vuelve a pedir
    private LocalDate pedirFecha() {
        LocalDate fecha;
        int anio, mes, dia;

        anio = pedirEntero("Introduce el año: ");
        mes = pedirEntero("Introduce el mes: ");
        dia = pedirEntero("Introduce el dia: ");
        try {
            fecha = LocalDate.of(anio, mes, dia);
        } catch (DateTimeException e) {
            System.out.println("ERROR: Introduce una fecha valida.");
            fecha = pedirFecha();//Utilizo la recursividad para asegurarme que me introduce una fecha valida.
        }
        return fecha;
    }

    private int pedirEntero(String mensaje) {
        Scanner entrada = new Scanner(System.in);
        int numero;
        System.out.println(mensaje);
        try {
            numero = entrada.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ERROR: Debes introducir un numero.");
            entrada.next(); //Limpio el buffer
            numero = pedirEntero(mensaje); //Utilizo la recursividad para asegurarme que me introduce un numero
        }
        return numero;
    }

}
