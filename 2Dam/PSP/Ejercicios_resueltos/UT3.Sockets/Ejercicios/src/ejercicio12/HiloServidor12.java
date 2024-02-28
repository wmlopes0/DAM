package ejercicio12;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServidor12 extends Thread {

    //Variables globales
    private Socket socket;
    private PrintWriter flujoEscritura;
    private ObjectInputStream flujoLectura;
    private Paciente paciente;

    public HiloServidor12(Socket socket) {
        this.socket = socket;
        try {
            flujoEscritura = new PrintWriter(socket.getOutputStream(), true);
            flujoLectura = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("ERROR: No se pudo recuperar los flujos escritura/lectura en el servidor.");
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                //Recibo un objeto paciente
                paciente = (Paciente) flujoLectura.readObject();
                //Muestro info consola
                System.out.println("Recibo del cliente: Socket[" + socket.getPort() + "]");
                System.out.println(paciente);
                comprobarPacienteMalito(paciente);
            }
        } catch (IOException e) {
//            System.out.println("ERROR DE ESCRITURA/LECTURA.");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: No se encontró la clase paciente.");
        }
    }

    private void comprobarPacienteMalito(Paciente paciente) {
        boolean malito = false;
        String mensajeAlerta = "****** PELIGRO, los siguientes valores son muy altos: ";

        //Compruebo valor por valor
        //Temperatura
        if (paciente.getTemperatura() < 37 || paciente.getTemperatura() > 42) {
            malito = true;
            mensajeAlerta += "[TEMPERATURA: " + paciente.getTemperatura() + "]";
        }
        //Frecuencia cardiaca
        if (paciente.getFrecuenciaCardiaca() < 60 || paciente.getFrecuenciaCardiaca() > 100) {
            malito = true;
            mensajeAlerta += "[FRECUENCIA CARDIACA: " + paciente.getFrecuenciaCardiaca() + "]";
        }
        //Sistólica
        if (paciente.getPresionArterialSistolica() < 120 || paciente.getPresionArterialSistolica() > 140) {
            malito = true;
            mensajeAlerta += "[SISTÓLICA: " + paciente.getPresionArterialSistolica() + "]";
        }
        //Diastólica
        if (paciente.getPresionArterialDiastolica() < 70 || paciente.getPresionArterialDiastolica() > 90) {
            malito = true;
            mensajeAlerta += "[DIASTÓLICA: " + paciente.getPresionArterialDiastolica() + "]";
        }

        //Si está malito, es decir, tiene algun valor fuera de lo normal, se lo enviamos al cliente
        if (malito) {
            flujoEscritura.println(mensajeAlerta);
        }
    }
}
