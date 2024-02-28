/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author imartinb04
 */
public class HiloEnviar extends Thread
{

    private PrintWriter flujoSalida;

    public HiloEnviar()
    {
        this.flujoSalida = null;
    }

    public HiloEnviar(PrintWriter flujoSalida)
    {
        this.flujoSalida = flujoSalida;
    }

    @Override
    public void run()
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String cadena  = br.readLine();

            while (!cadena.equalsIgnoreCase("EXIT"))
            {
                System.out.print("--> ");
                
                flujoSalida.println(cadena);
                System.out.println("[Israel]: " + cadena);
                cadena  = br.readLine();
            }

            flujoSalida.close();

        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}
