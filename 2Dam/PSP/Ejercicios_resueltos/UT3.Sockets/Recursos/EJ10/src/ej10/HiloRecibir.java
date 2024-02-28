/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej10;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author imartinb04
 */
public class HiloRecibir extends Thread
{

    private BufferedReader flujoEntrada;
    private static Socket cliente;

    public HiloRecibir()
    {
        this.flujoEntrada = null;
    }

    public HiloRecibir(BufferedReader entrada)
    {
        this.flujoEntrada = entrada;
    }

    @Override
    public void run()
    {
        try
        {
            String mensaje = "";

            do
            {
                mensaje = flujoEntrada.readLine();
                System.out.println("[Didac]: " + mensaje);
            } while (!mensaje.equalsIgnoreCase("EXIT"));

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }
}
