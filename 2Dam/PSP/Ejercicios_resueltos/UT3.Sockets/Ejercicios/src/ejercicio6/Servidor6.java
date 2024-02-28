package ejercicio6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Servidor6 {

    //Variables globales
    private static int puerto = 8000;
    private ServerSocket servidor;
    private Socket cliente;
    private DataInputStream flujoEntrada;
    private DataOutputStream flujoSalida;

    public static void main(String[] args) {
        //Instancio un servidor
        Servidor6 servidor = new Servidor6();
        //Establezco la conexión
        servidor.setConexion();
        //Bucle
        String mensaje;
        do {
            mensaje = servidor.getMensajeCliente();
            if (!mensaje.equalsIgnoreCase("EXIT")) {
                //Transformar mensaje
                mensaje = transformarMensaje(mensaje);
                //Envio mensaje
                servidor.setMensajeCliente(mensaje);
            }

        } while (!mensaje.equalsIgnoreCase("EXIT"));
        //Cierro flujos
        servidor.closeStreamsSockets();
    }

    //Método para transformar el mensaje
    public static String transformarMensaje(String mensaje) {
        List<Character> lista = new ArrayList<>();
        for (char c : mensaje.toUpperCase().toCharArray()) {
            lista.add(c);
        }
        Collections.shuffle(lista);

        StringBuilder mensajeDesordenado = new StringBuilder();
        for (char c : lista) {
            mensajeDesordenado.append(c);
        }

        return mensajeDesordenado.toString();
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
