/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio7;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wmartinl01
 */
public class PintaTabla extends Thread {

    //Variables globales
    private static File directorio = new File("TABLAS");

    //Atributos
    private int numero;

    //Constructor
    public PintaTabla(int numero) {
        this.numero = numero;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }

    //Override
    @Override
    public void run() {
        directorio.mkdir();
        try {
            escribirTabla();
        } catch (IOException ex) {
            Logger.getLogger(PintaTabla.class.getName()).log(Level.SEVERE, null, ex);
        }catch (InterruptedException ex) {
            Logger.getLogger(PintaTabla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void escribirTabla() throws IOException, InterruptedException {
        File fichero = new File(directorio.getAbsolutePath(), String.valueOf(numero) + ".txt");
        PrintWriter pw = new PrintWriter(new FileWriter(fichero));
        pw.println("TABLA DEL " + numero);
        for (int i = 0; i < 10; i++) {
            pw.println(String.valueOf(numero) + " * " + i + " = " + (numero * i));
            Thread.sleep(500);
        }
        pw.close();
    }
    
}
