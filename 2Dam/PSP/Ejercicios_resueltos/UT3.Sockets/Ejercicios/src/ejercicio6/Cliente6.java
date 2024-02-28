package ejercicio6;

import ejercicio5.Cliente5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente6 {

    //Variables globales
    private static int puerto = 8000;
    private Socket cliente;
    private DataInputStream flujoEntrada;
    private DataOutputStream flujoSalida;

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        //Instancio un Cliente
        Cliente6 cliente6 = new Cliente6();
        //Establezco la conexión
        cliente6.setConexion();
        //Bucle
        String mensaje;
        do {
            System.out.println("Introduzca un mensaje (EXIT para terminar): ");
            mensaje = entrada.nextLine();
            cliente6.setMensajeServidor(mensaje);
            if (!mensaje.equalsIgnoreCase("EXIT")) {
                cliente6.getMensajeServidor();
            }
        } while (!mensaje.equalsIgnoreCase("EXIT"));

        //Cierro flujos
        cliente6.closeStreamsSockets();
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

    public void getMensajeServidor() {
        String mensaje = "";
        try {
            //Recupero el flujo de entrada del cliente
            flujoEntrada = new DataInputStream(cliente.getInputStream());
            //El servidor me envia un mensaje
            System.out.println("[Cliente] recibe: " + flujoEntrada.readUTF());
        } catch (IOException e) {
            System.out.println("ERROR: NO SE PUDO RECUPERAR EL MENSAJE DEL SERVIDOR.");
        }
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
