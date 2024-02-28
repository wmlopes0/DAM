/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ej10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author imartinb04
 */
public class HiloServidor extends Thread
{

    BufferedReader fentrada;
    PrintWriter fsalida;
    Socket socket = null;

    public HiloServidor(Socket socket)
    {
        this.socket = socket;
        try
        {
            // inicializamos los flujos de entrada y de salida
            fsalida = new PrintWriter(socket.getOutputStream(), true);
            fentrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void run()
    {
        try
        {
            String cadena = "";
            System.out.println("COMUNICO CON: " + socket.toString());
            
            while (cadena != null && !cadena.trim().equals("EXIT"))
            {
                
                HiloEnviar enviarMensaje = new HiloEnviar(fsalida);
                enviarMensaje.start();

                cadena = fentrada.readLine(); // Guardamos la cadena (el mensaje de Didac el gay)
                
                if (cadena != null)
                {
                    System.out.println("[Didac]: " + cadena);
                }
            }
            
            System.out.println("FIN CON: " + socket.toString());
            fsalida.close();
            fentrada.close();
            socket.close();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }
    }
}   
