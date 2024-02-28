package ejercicio12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HiloCliente12 extends Thread {

    //Variables globales
    private Socket socket;
    private ObjectOutputStream flujoEscritura;
    private Paciente paciente;
    private BufferedReader flujoLectura;

    //Constructor
    public HiloCliente12(Socket socket) {
        try {
            this.socket = socket;
            flujoEscritura = new ObjectOutputStream(socket.getOutputStream());
            this.flujoLectura = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("ERROR: No se pudo obtener el flujo de escritura");
        }
    }

    @Override
    public void run() {
        try {
            //Envio 5 veces la info del paciente cada 5 segundos
            for (int i = 0; i < 5; i++) {
                paciente = new Paciente(false);//Creo paciente con nuevos valores (false para que este malo de vez en cuando)
                Thread.sleep(5000);//Espero x segundos
                flujoEscritura.writeObject(paciente);//Mando el paciente
                flujoEscritura.flush();
                flujoEscritura.reset();
                System.out.println(flujoLectura.readLine());
            }

        } catch (InterruptedException e) {
            System.out.println("ERROR: El sleep no ha funcionado bien");
        } catch (IOException e) {
            System.out.println("ERROR: No se pudo enviar el objeto paciente.");
        }
    }
}
