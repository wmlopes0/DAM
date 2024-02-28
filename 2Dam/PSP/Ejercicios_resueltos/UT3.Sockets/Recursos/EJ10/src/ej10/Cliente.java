/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej10;

/**
 *
 * @author imartinb04
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente
{

    private static int puerto = 8000;

    public static void main(String[] args)
    {
        try
        {
            Socket cliente = new Socket("192.168.100.63", puerto);
            BufferedReader flujoEntrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter flujoSalida = new PrintWriter(cliente.getOutputStream(), true);

            // Iniciar hilo para recibir mensajes del servidor
            HiloRecibir recibirMensaje = new HiloRecibir(flujoEntrada);
            recibirMensaje.start();

            // Iniciar hilo para enviar mensajes al servidor
            HiloEnviar enviarMensaje = new HiloEnviar(flujoSalida);
            enviarMensaje.start();

            //flujoEntrada.close();
            //flujoSalida.close();
            //cliente.close();
            
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
