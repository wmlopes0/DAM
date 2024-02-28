package Ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente1 {

    //Variables globales
    private static int puerto = 8000;
    private Socket socket;
    private ObjectOutputStream flujoSalida;
    private BufferedReader flujoEntrada;

    //Main
    public static void main(String[] args) {
        //Instancio clase
        Cliente1 cliente = new Cliente1();
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
            //Recupero flujos
            flujoSalida = new ObjectOutputStream(socket.getOutputStream());
            flujoEntrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Inicio petición
            iniciarPeticion();
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }

    //Método para iniciar la petición
    public void iniciarPeticion() {
        try {
            //Creo objeto coche pidiendo datos (se hace en el constructor)
            Coche coche = new Coche();
            //Mando el coche al servidor para obtener la respuesta
            flujoSalida.writeObject(coche);
            flujoSalida.flush();
            flujoSalida.reset();
            //Recupero respuesta del servidor y la muestro por pantalla
            System.out.println(flujoEntrada.readLine());
            //Libero recursos (desconecto)
            flujoEntrada.close();
            flujoSalida.close();
            socket.close();
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }
}
