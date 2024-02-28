/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EJ7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author imartinb04
 */
public class Cliente
{

    private static int puerto = 8000;
    private Socket cliente;
    private DataInputStream flujoEntrada = null;
    private DataOutputStream flujoSalida = null;

    public static void main(String[] args)
    {
        Cliente cliente = new Cliente();
        cliente.setConexion();
        //cliente.getMensajeServidor();
        cliente.setMensajeServidor("4");
        cliente.getMensajeServidor();
        cliente.setMensajeServidor("6");
        cliente.getMensajeServidor();
        cliente.closeStreamsSockets();
    }

    public void setConexion()
    {
        try
        {
            cliente = new Socket("192.168.100.63", puerto);
            System.out.println("Socket cliente iniciado...");
        } catch (IOException e)
        {
            System.out.println("Error en establecerConexion " + e.getMessage());
        }
    }

    /**
     * Establecemos un flujo de entrada para recibir mensajes desde el Servidor
     */
    public void getMensajeServidor()
    {
        try
        {
            flujoEntrada = new DataInputStream(cliente.getInputStream());
// El servidor me envía un mensaje
            System.out.println("Recibiendo del servidor: "
                    + flujoEntrada.readUTF());

        } catch (IOException e)
        {
            System.out.println("setFlujoEntrada " + e.getMessage());
        }
    }

    /**
     * Creamos un flujo de salida hacia el Servidor
     *
     * @param msj
     */
    public void setMensajeServidor(String msj)
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
        } catch (IOException e)
        {
            System.out.println("closeStreamSockets " + e.getMessage());
        }
    }
}
