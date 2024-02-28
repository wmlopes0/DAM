package ejercicio7;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Cliente7 {
    //Variables globales
    private static int puerto = 8000;
    private Socket cliente;
    private DataInputStream flujoEntrada;
    private DataOutputStream flujoSalida;

    public static void main(String[] args) {
        //Instancio un Cliente
        Cliente7 cliente7 = new Cliente7();
        //Establezco la conexión
        cliente7.setConexion();
        //Pido numero, lo envio al servidor y espero respuesta
        cliente7.setMensajeServidor(cliente7.pedirNumero());
        cliente7.getMensajeServidor();
        //Cierro flujos
        cliente7.closeStreamsSockets();
    }

    public int pedirNumero() {
        Scanner entrada = new Scanner(System.in);
        int num;
        try {
            System.out.println("Introduzca un número:");
            num = entrada.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ERROR: No puedes introducir una letra");
            return pedirNumero();
        }
        return num;
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

    public void setMensajeServidor(int num) {
        try {
            //Recupero el flujo de salida del cliente
            flujoSalida = new DataOutputStream(cliente.getOutputStream());
            //Envio un mensaje al servidor
            flujoSalida.writeUTF(String.valueOf(num));
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
