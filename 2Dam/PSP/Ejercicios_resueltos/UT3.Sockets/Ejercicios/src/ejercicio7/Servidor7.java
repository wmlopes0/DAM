package ejercicio7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Servidor7 {
    //Variables globales
    private static int puerto = 8000;
    private ServerSocket servidor;
    private Socket cliente;
    private DataInputStream flujoEntrada;
    private DataOutputStream flujoSalida;

    public static void main(String[] args) {
        //Instancio un servidor
        Servidor7 servidor = new Servidor7();
        //Establezco la conexión
        servidor.setConexion();
        //Recibo numero y devuelvo el cuadrado
        int num = servidor.getMensajeCliente();
        servidor.setMensajeCliente(num);
        //Cierro flujos
        servidor.closeStreamsSockets();
    }

    //Método para devolver cuadrado
    public static float devolverCuadrado(int num) {
        return (float) Math.sqrt(num);
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

    public int getMensajeCliente() {
        int num = 0;
        try {
            //Recupero el InputStream del cliente
            flujoEntrada = new DataInputStream(cliente.getInputStream());
            //El cliente me envia un mensaje
            num = Integer.parseInt(flujoEntrada.readUTF());
            System.out.println("[Servidor] recibe: " + num);
        } catch (IOException e) {
            System.out.println("ERROR: NO SE PUDO RECUPERAR EL MENSAJE DEL CLIENTE.");
        }
        return num;
    }

    public void setMensajeCliente(int num) {
        try {
            //Recupero el OutputStream del cliente
            flujoSalida = new DataOutputStream(cliente.getOutputStream());
            //Envio un mensaje al cliente
            System.out.println("[Servidor] envia: " + devolverCuadrado(num));
            flujoSalida.writeUTF("El cuadrado de " + num + " es " + devolverCuadrado(num));
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
