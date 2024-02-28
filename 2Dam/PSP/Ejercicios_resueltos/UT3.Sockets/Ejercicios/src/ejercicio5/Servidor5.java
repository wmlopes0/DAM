package ejercicio5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor5 {
    //Variables globales
    private static int puerto = 8000;
    private ServerSocket servidor;
    private Socket cliente;
    private DataInputStream flujoEntrada;
    private DataOutputStream flujoSalida;

    public static void main(String[] args) throws InterruptedException {
        //Instancio un servidor
        Servidor5 servidor = new Servidor5();
        //Establezco la conexión
        servidor.setConexion();
        //Recibo PIM
        servidor.getMensajeCliente();
        //Envio PAM
        Thread.sleep(3000);
        servidor.setMensajeCliente("PAM");
        //Recibo PUM
        servidor.getMensajeCliente();
        //Envio FUEGO
        Thread.sleep(3000);
        servidor.setMensajeCliente("FUEGO");
        //Cierro flujos
        servidor.closeStreamsSockets();
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

    public void getMensajeCliente() {
        try {
            //Recupero el InputStream del cliente
            flujoEntrada = new DataInputStream(cliente.getInputStream());
            //El cliente me envia un mensaje
            System.out.println("[Servidor] recibe: " + flujoEntrada.readUTF());
        } catch (IOException e) {
            System.out.println("ERROR: NO SE PUDO RECUPERAR EL MENSAJE DEL CLIENTE.");
        }
    }

    public void setMensajeCliente(String msj) {
        try {
            //Recupero el OutputStream del cliente
            flujoSalida = new DataOutputStream(cliente.getOutputStream());
            //Envio un mensaje al cliente
            System.out.println("[Servidor] envia: " + msj);
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
