package ejercicio8;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Servidor8 {
    //Variables globales
    private static int puerto = 8000;
    private ServerSocket servidor;
    private Socket cliente;
    private DataInputStream flujoEntrada;
    private DataOutputStream flujoSalida;

    public static void main(String[] args) {
        //Instancio un servidor
        Servidor8 servidor = new Servidor8();
        //Establezco la conexión
        servidor.setConexion();
        //Bucle
        String mensaje;
        do {
            mensaje = servidor.getMensajeCliente();
            if (!mensaje.equalsIgnoreCase("EXIT")) {
                mensaje = pedirMensaje();
                servidor.setMensajeCliente(mensaje);
            }
        } while (!mensaje.equalsIgnoreCase("EXIT"));
        //Cierro flujos
        servidor.closeStreamsSockets();
    }

    public static String pedirMensaje() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduzca un mensaje. (EXIT para salir):");
        return entrada.nextLine();
    }

    public void setConexion() {
        try {
            //Abro el servidor en el puerto indicado
            servidor = new ServerSocket(puerto);
            System.out.println("Esperando conexión entrante en el puerto " + puerto + " ...");
            //Socket escuchando...
            cliente = servidor.accept();
            System.out.println("Conexión establecida con: " + cliente.getInetAddress().getHostName());
        } catch (IOException e) {
            System.out.println("ERROR: NO SE PUDO ESTABLECER LA CONEXIÓN.");
        }
    }

    public String getMensajeCliente() {
        String mensaje = null;
        try {
            //Recupero el InputStream del cliente
            flujoEntrada = new DataInputStream(cliente.getInputStream());
            //El cliente me envia un mensaje
            mensaje = flujoEntrada.readUTF();
            System.out.println("[Servidor] recibe: " + mensaje);
        } catch (IOException e) {
            System.out.println("ERROR: NO SE PUDO RECUPERAR EL MENSAJE DEL CLIENTE.");
        }
        return mensaje;
    }

    public void setMensajeCliente(String msj) {
        try {
            //Recupero el OutputStream del cliente
            flujoSalida = new DataOutputStream(cliente.getOutputStream());
            //Envio un mensaje al cliente
            flujoSalida.writeUTF(msj);
        } catch (IOException e) {
            System.out.println("ERROR: NO SE PUDO ENVIAR EL MENSAJE AL CLIENTE.");
        }
    }

    public void closeStreamsSockets() {
        try {
            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();
            servidor.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
