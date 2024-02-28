/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej10;

/**
 *
 * @author imartinb04
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor
{

    private static int puerto = 8000;

    public static void main(String[] args)
    {
        try
        {
            ServerSocket servidor = new ServerSocket(puerto);
            System.out.println("Servidor iniciado ... ");
            
            while (true)
            {
                Socket cliente = new Socket();
                cliente = servidor.accept();// esperando cliente
                HiloServidor hilo = new HiloServidor(cliente);
                hilo.start(); // se atiende al cliente
            }
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
