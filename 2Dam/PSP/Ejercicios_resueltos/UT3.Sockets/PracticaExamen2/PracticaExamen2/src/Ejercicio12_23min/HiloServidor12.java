package Ejercicio12_23min;

import java.io.*;
import java.net.Socket;

public class HiloServidor12 extends Thread {

    //Variables globales
    private Socket cliente;

    private ObjectInputStream flujoEntrada;
    private PrintWriter flujoSalida;


    public HiloServidor12(Socket cliente) {
        try {
            this.cliente = cliente;
            flujoEntrada = new ObjectInputStream(cliente.getInputStream());
            flujoSalida = new PrintWriter(cliente.getOutputStream(), true);
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            //Mientras el cliente no se haya desconectado
            while (!cliente.isClosed()) {
                //Recibo paciente
                Paciente paciente = (Paciente) flujoEntrada.readObject();
                System.out.println("Recibo del cliente: " + cliente);
                System.out.println(paciente);
                //Mando info al cliente
                comprobarPacienteMalito(paciente);
            }
        } catch (IOException e) {
//                throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
//                throw new RuntimeException(e);
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
            flujoSalida.println(mensajeAlerta);
        }
    }
}
