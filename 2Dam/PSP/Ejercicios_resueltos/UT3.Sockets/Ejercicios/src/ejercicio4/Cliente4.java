package ejercicio4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente4 {
    //Variables globales
    private static int puerto = 8000;
    private Socket cliente;
    private DataInputStream flujoEntrada;
    private DataOutputStream flujoSalida;

    public static void main(String[] args) {
        //Instancio un Cliente
        Cliente4 cliente = new Cliente4();
        //Establezco la conexión
        cliente.setConexion();
        //Recupero lo que me dice el servidor
        String mensaje = cliente.getMensajeServidor();
        //Se lo envio
        cliente.setMensajeServidor(mensaje);
        cliente.closeStreamsSockets();
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
            System.out.println("Recibiendo del servidor: " + mensaje);
            System.out.println("Devolviendo mensaje en mayúsculas...");
        } catch (IOException e) {
            System.out.println("ERROR: NO SE PUDO RECUPERAR EL MENSAJE DEL SERVIDOR.");
        }
        return mensaje.toUpperCase();
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
