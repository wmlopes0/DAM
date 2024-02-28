package ejercicio5;

import ejercicio4.Cliente4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente5 {

    //Variables globales
    private static int puerto = 8000;
    private Socket cliente;
    private DataInputStream flujoEntrada;
    private DataOutputStream flujoSalida;

    public static void main(String[] args) throws InterruptedException {
        //Instancio un Cliente
        Cliente5 cliente = new Cliente5();
        //Establezco la conexión
        cliente.setConexion();
        //Envio PIM
        Thread.sleep(3000);
        cliente.setMensajeServidor("PIM");
        //Recibo PAM
        cliente.getMensajeServidor();
        //Envio PUM
        Thread.sleep(3000);
        cliente.setMensajeServidor("PUM");
        //Recibo FUEGO
        cliente.getMensajeServidor();
        //Cierro flujos
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
            System.out.println("[Cliente] envia: " + msj);
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
