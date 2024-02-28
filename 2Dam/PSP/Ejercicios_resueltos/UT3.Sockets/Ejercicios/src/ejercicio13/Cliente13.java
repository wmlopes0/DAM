package ejercicio13;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Cliente13 {

    //Variables globales
    private static int puerto = 8000;
    private Socket socket;
    private ObjectOutputStream flujoEscritura;
    private ObjectInputStream flujoLectura;

    //Instancia de la clase
    private static Cliente13 cliente;

    public static void main(String[] args) {
        //Instancio clase
        cliente = new Cliente13();
        //Establezco conexión
        cliente.establecerConexion();
        //Lanzar menú
        cliente.iniciarGestionEvento();
    }

    public void establecerConexion() {
        try {
            socket = new Socket("localhost", puerto);
            flujoEscritura = new ObjectOutputStream(socket.getOutputStream());
            flujoLectura = new ObjectInputStream(socket.getInputStream());
            System.out.println("CONEXIÓN ESTABLECIDA");
        } catch (IOException e) {
            System.out.println("ERROR AL ESTABLECER LA CONEXIÓN CON EL SERVIDOR");
        }
    }

    public void iniciarGestionEvento() {
        int opcion;
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    inscribirPersona();
                    break;
                case 2:
                    mostrarListadoPersonasInscritas();
                    break;
                case 3:
                    System.out.println("HASTA PRONTO!");
                    cerrarConexion();//Cierro conexión
                    break;
            }

        } while (opcion != 3);
    }

    private static int mostrarMenu() {
        System.out.println("\n************************************************");
        System.out.println("*********  E V E N T O S   W A L T E R *********");
        System.out.println("************************************************");
        System.out.println("1.- Inscribir persona al evento.");
        System.out.println("2.- Mostrar listado de personas inscritas.");
        System.out.println("3.- Salir.");
        System.out.println("------------------------------------------------");
        return pedirOpcion();
    }

    private static int pedirOpcion() {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        System.out.println("Por favor, introduzca una opción: ");
        try {
            opcion = entrada.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ERROR: No puedes introducir una letra.");
            opcion = pedirOpcion();
        }
        return opcion;
    }

    private static void inscribirPersona() {
        //Creo objeto Persona y relleno info
        Persona persona = new Persona();
        //Creo objeto peticion
        Peticion peticion = new Peticion(1, persona);
        try {
            cliente.flujoEscritura.writeObject(peticion);//Mando la peticion al servidor
            cliente.flujoEscritura.flush();
            cliente.flujoEscritura.reset();
            Respuesta respuesta = (Respuesta) cliente.flujoLectura.readObject();//Recibo respuesta del servidor
            //CHECK
            System.out.println("\n#Inscripción realizada correctamente, la lista queda:");
            System.out.println("--------------------------------------------------------------------------");
            respuesta.mostrarPersonasInscritas();
            System.out.println("--------------------------------------------------------------------------");
        } catch (IOException e) {
            System.out.println("ERROR LECTURA/ESCRITURA");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR No se encontró la clase respuesta");
        }
    }

    private static void mostrarListadoPersonasInscritas() {
        //Creo objeto Persona a null
        Persona persona = null;
        //Creo objeto peticion
        Peticion peticion = new Peticion(2, persona);
        try {
            cliente.flujoEscritura.writeObject(peticion);//Mando la peticion al servidor
            cliente.flujoEscritura.flush();
            cliente.flujoEscritura.reset();
            Respuesta respuesta = (Respuesta) cliente.flujoLectura.readObject();//Recibo respuesta del servidor
            //CHECK
            if (!respuesta.getPersonasApuntadas().isEmpty()) {
                System.out.println("\n#Solicitud de listado completada, el listado de personas es el siguiente:");
                System.out.println("--------------------------------------------------------------------------");
                respuesta.mostrarPersonasInscritas();
                System.out.println("--------------------------------------------------------------------------");
            } else {
                System.out.println("#Solicitud de listado completada, aún no existen personas inscritas.");
            }
        } catch (IOException e) {
            System.out.println("ERROR LECTURA/ESCRITURA");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR No se encontró la clase respuesta");
        }
    }

    public void cerrarConexion() {
        try {
            flujoLectura.close();
            flujoEscritura.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("ERROR AL LIBERAR RECURSOS");
        }
    }
}
