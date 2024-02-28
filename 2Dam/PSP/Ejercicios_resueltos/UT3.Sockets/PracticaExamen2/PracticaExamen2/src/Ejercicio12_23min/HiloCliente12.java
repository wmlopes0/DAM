package Ejercicio12_23min;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HiloCliente12 extends Thread {

    //Variables globales
    private Socket cliente;
    private ObjectOutputStream flujoSalida;
    private BufferedReader flujoEntrada;

    //Constructor
    public HiloCliente12(Socket cliente) {
        try {
            this.cliente = cliente;
            //Inicio flujos
            flujoSalida = new ObjectOutputStream(cliente.getOutputStream());
            flujoEntrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                //Envio info paciente
                Paciente paciente = new Paciente(false);
                flujoSalida.writeObject(paciente);
                flujoSalida.flush();
                flujoSalida.reset();
                //Recibo respuesta
                System.out.println(flujoEntrada.readLine());
                Thread.sleep(3000);
            }
        } catch (IOException e) {
//                throw new RuntimeException(e);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }

    }
}
