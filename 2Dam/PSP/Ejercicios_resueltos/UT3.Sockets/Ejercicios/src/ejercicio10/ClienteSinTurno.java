package ejercicio10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteSinTurno {

    //Variables globales
    private static int puerto = 8000;
    private Socket cliente;
    private PrintWriter flujoSalida;
    private BufferedReader flujoEntrada;

    public static void main(String[] args) {
        //Instancio clase
        ClienteSinTurno claseCliente = new ClienteSinTurno();
        //Establezco conexión
        claseCliente.establecerConexion();
    }

    public void establecerConexion() {
        try {
            //Inicio socket y establezco conexión
            cliente = new Socket("localhost", puerto);
            System.out.println("Conexion establecida");
            //Recupero flujos e inicio hilos
            flujoSalida = new PrintWriter(cliente.getOutputStream(), true);
            HiloEscritura hiloEscritura = new HiloEscritura(flujoSalida);
            flujoEntrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            HiloLectura hiloLectura = new HiloLectura(flujoEntrada, this);
            hiloEscritura.start();
            hiloLectura.start();
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }

    public void cerrarTodo() {
        System.out.println("CHAT TERMINADO");
        try {
            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }


}
