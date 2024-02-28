package Ejercicio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente2 {

    //Variables globales
    private static int puerto = 8000;
    private Socket socket;
    private ObjectOutputStream flujoSalida;
    private BufferedReader flujoEntrada;

    //Main
    public static void main(String[] args) {
        //Instancio clase
        Cliente2 cliente = new Cliente2();
        //Establezco la conexión y realizo la petición al servidor
        cliente.establecerConexionIniciarPeticion();
    }

    //Método para establecer la conexión
    public void establecerConexionIniciarPeticion() {
        try {
            //Establezco conexión con el servidor
            socket = new Socket("localhost", puerto);
            //Mensaje Check
            System.out.println("Socket cliente iniciado...\n");
            HiloCliente hiloCliente = new HiloCliente(socket);
            hiloCliente.start();

            //No cierro y libero recursos porque vas a forzar el cierre.

        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }

}
