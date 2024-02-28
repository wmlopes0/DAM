/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EJ6;

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
    private static boolean salir;

    public static void main(String[] args)
    {
        Servidor servidor = new Servidor();
        servidor.setConexion();
        
        do
        {
            servidor.getMensajeCliente();
            servidor.setMensajeCliente("DIDAC CHUPA POLLAS DEL AVERNO");
            
        } while (!salir);

        servidor.closeStreamsSockets();

    }

    public void reordenarCadena(String mensaje)
    {
        if (mensaje.equalsIgnoreCase("SALIR"))
        {
            this.salir = true;
        }

        String mensajeInvertido = "";

        // INVERTIMOS EL MENSAJE QUE NOS LLEGA
        for (int i = mensaje.length() - 1; i >= 0; i--)
        {
            mensajeInvertido += mensaje.charAt(i);
        }

        System.out.println(mensajeInvertido); // MOSTRAMOS EL MENSAJE INVERTIDO

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
            reordenarCadena(flujoEntrada.readUTF());

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
