/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EJ7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author imartinb04
 */
public class Servidor
{

    private static int puerto = 8000;
    private Socket cliente;
    private ServerSocket servidor;
    private DataInputStream flujoEntrada = null;
    private DataOutputStream flujoSalida = null;

    public static void main(String[] args)
    {
        Servidor servidor = new Servidor();
        servidor.setConexion();
        servidor.setMensajeCliente("Didac chupa pollas negras de enanos del Aberno");
        servidor.getMensajeCliente();
        servidor.closeStreamsSockets();
    }

    public void numCuadrado(String mensaje)
    {
        int numCuadrado;

        // Parseamos el texto para que sea un número
        numCuadrado = Integer.parseInt(mensaje);
        
        numCuadrado = numCuadrado * numCuadrado;

        System.out.println(numCuadrado); // MOSTRAMOS EL NUMERO AL CUADRADO
    }

    public void setConexion()
    {
        try
        {
            servidor = new ServerSocket(puerto);
            System.out.println("Esperando conexión entrante en el puerto " + puerto
                    + "...");
            cliente = servidor.accept();
            System.out.println("Conexión establecida con: "
                    + cliente.getInetAddress().getHostName() + "\n\n\n");

        } catch (IOException e)
        {
            System.out.println("Error en establecerConexion " + e.getMessage());
        }
    }

    /**
     * Establecemos un flujo de entrada para recibir mensajes desde el Cliente
     */
    public void getMensajeCliente()
    {
        try
        {
            flujoEntrada = new DataInputStream(cliente.getInputStream());
            //El cliente me envía un mensaje
            //System.out.println("Recibiendo del cliente: " + flujoEntrada.readUTF());

            // El mensaje que envía el cliente se manda al método para darle la vuelta
            numCuadrado(flujoEntrada.readUTF());
            
            closeStreamsSockets(); // CERRAMOS CONEXIÓN

        } catch (IOException e)
        {
            System.out.println("setFlujoEntrada " + e.getMessage());
        }
    }

    /**
     * Creamos un flujo de salida hacia el Cliente
     *
     * @param msj
     */
    public void setMensajeCliente(String msj)
    {
        try
        {
            flujoSalida = new DataOutputStream(cliente.getOutputStream());
            flujoSalida.writeUTF(msj);
        } catch (IOException e)
        {
            System.out.println("setFlujoSalida " + e.getMessage());
        }
    }

    /**
     * Cerramos los flujos de entrada/salida y los Sockets Cliente y Servidor
     */
    public void closeStreamsSockets()
    {
        try
        {
            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();
            servidor.close();
        } catch (IOException e)
        {
            System.out.println("closeStreamSockets " + e.getMessage());
        }
    }
}
