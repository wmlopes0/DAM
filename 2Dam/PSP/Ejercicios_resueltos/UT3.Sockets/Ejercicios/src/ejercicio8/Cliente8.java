package ejercicio8;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente8 {
    //Variables globales
    private static int puerto = 8000;
    private Socket cliente;
    private DataInputStream flujoEntrada;
    private DataOutputStream flujoSalida;

    public static void main(String[] args) {
        //Instancio un Cliente
        Cliente8 cliente8 = new Cliente8();
        //Establezco la conexión
        cliente8.setConexion();
        //Bucle
        String mensaje;
        cliente8.setMensajeServidor(pedirMensaje());
        do {
            mensaje = cliente8.getMensajeServidor();
            if (!mensaje.equalsIgnoreCase("EXIT")) {
                mensaje = pedirMensaje();
                cliente8.setMensajeServidor(mensaje);
            }
        } while (!mensaje.equalsIgnoreCase("EXIT"));

        //Cierro flujos
        cliente8.closeStreamsSockets();
    }

    public static String pedirMensaje() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduzca un mensaje. (EXIT para salir):");
        return entrada.nextLine();
    }

    public void setConexion() {
        try {
            //Inicio el Socket cliente en el puerto e ip indicados
            cliente = new Socket("localhost", puerto);
            System.out.println("Socket cliente iniciado...");
        } catch (IOException e) {
            System.out.println("ERROR: NO SE PUDO ESTABLECER LA CONEXIÓN.");
        }
    }

    public String getMensajeServidor() {
        String mensaje = "";
        try {
            //Recupero el flujo de entrada del cliente
            flujoEntrada = new DataInputStream(cliente.getInputStream());
            //El servidor me envia un mensaje
            mensaje = flujoEntrada.readUTF();
            System.out.println("[Cliente] recibe: " + mensaje);
        } catch (IOException e) {
            System.out.println("ERROR: NO SE PUDO RECUPERAR EL MENSAJE DEL SERVIDOR.");
        }
        return mensaje;
    }

    public void setMensajeServidor(String msj) {
        try {
            //Recupero el flujo de salida del cliente
            flujoSalida = new DataOutputStream(cliente.getOutputStream());
            //Envio un mensaje al servidor
            flujoSalida.writeUTF(msj);
        } catch (IOException e) {
            System.out.println("ERROR: NO SE PUDO ENVIAR EL MENSAJE AL SERVIDOR.");
        }
    }

    public void closeStreamsSockets() {
        try {
            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
