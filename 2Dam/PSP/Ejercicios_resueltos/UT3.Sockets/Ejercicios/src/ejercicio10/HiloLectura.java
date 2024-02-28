package ejercicio10;

import java.io.BufferedReader;
import java.io.IOException;

public class HiloLectura extends Thread{

    //Variables globales
    private BufferedReader flujoEntrada;
    private ClienteSinTurno cliente;
    private ServidorSinTurno servidor;

    //Constructores
    public HiloLectura(BufferedReader flujoEntrada,ClienteSinTurno cliente) {
        this.flujoEntrada = flujoEntrada;
        this.cliente = cliente;
    }
    public HiloLectura(BufferedReader flujoEntrada,ServidorSinTurno servidor) {
        this.flujoEntrada = flujoEntrada;
        this.servidor = servidor;
    }

    @Override
    public void run() {
        try {
            String mensaje;
            do {
                //Recupero mensaje
                mensaje = flujoEntrada.readLine();
                //Si el mensaje es exit cierro flujos, sino muestro el mensaje por pantalla
                if (mensaje.equalsIgnoreCase("EXIT")){
                    if (cliente !=null){
                        cliente.cerrarTodo();
                    }else{
                        servidor.cerrarTodo();
                    }
                }else{
                    System.out.println("--->"+mensaje);
                }
            }while(!mensaje.trim().equalsIgnoreCase("EXIT"));

        } catch (IOException e) {
//            throw new RuntimeException(e);
        }catch (NullPointerException e) {
//            throw new RuntimeException(e);
        }
    }
}
