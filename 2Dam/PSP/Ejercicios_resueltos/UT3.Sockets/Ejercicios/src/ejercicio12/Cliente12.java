package ejercicio12;

import java.io.IOException;
import java.net.Socket;

public class Cliente12 {

    //Variables globales
    private static int puerto = 8000;
    private Socket socket;

    public static void main(String[] args) {
        //Instancio clase
        Cliente12 cliente = new Cliente12();
        //Establezco conexión
        cliente.establecerConexion();
    }

    //Establecer conexión
    public void establecerConexion() {
        try {
            //Inicio hilo1
            socket = new Socket("localhost", puerto);
            HiloCliente12 hiloCliente1 = new HiloCliente12(socket);
            hiloCliente1.start();
            Thread.sleep(2000);
            //Inicio hilo2
            socket = new Socket("localhost", puerto);
            HiloCliente12 hiloCliente2 = new HiloCliente12(socket);
            hiloCliente2.start();
            Thread.sleep(2000);
            //Inicio hilo3
            socket = new Socket("localhost", puerto);
            HiloCliente12 hiloCliente3 = new HiloCliente12(socket);
            hiloCliente3.start();
            Thread.sleep(2000);

        } catch (IOException e) {
            System.out.println("ERROR: No se pudo establecer la conexión cliente servidor.");
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
    }

    public void liberarRecursos() {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("ERROR: No se pudo liberar los recursos.");
        }
    }
}
