/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej8;

/**
 *
 * @author dcorderos01
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor
{

    private static int puerto = 8000;
    private Socket cliente;
    private ServerSocket servidor;
    private DataInputStream flujoEntrada = null;
    private DataOutputStream flujoSalida = null;
    private String mensaje;
    private static boolean salir;

    public static void main(String[] args)
    {
        Scanner teclado = new Scanner(System.in);
        String msj;
        Servidor servidor = new Servidor();
        servidor.setConexion();

        do
        {
            servidor.getMensajeCliente();
            msj = teclado.nextLine();
            servidor.setMensajeCliente(msj);
        } while (!salir);

        servidor.closeStreamsSockets();
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
        String respuesta;
        try
        {
            flujoEntrada = new DataInputStream(cliente.getInputStream());
            //El cliente me envía un mensaje
            respuesta = flujoEntrada.readUTF();
            System.out.println("recibo: " + respuesta);
            if (respuesta.equalsIgnoreCase("EXIT"))
            {
                salir = true;
            } else
            {
                salir = false;
            }
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
    public void setMensajeCliente(String mansaje)
    {
        try
        {
            flujoSalida = new DataOutputStream(cliente.getOutputStream());
            flujoSalida.writeUTF(mansaje);
            System.out.println("envio: " + mansaje);
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
