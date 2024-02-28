package ejercicio13;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor13 {

    //Variables globales
    private static List<Persona> personasInscritas;
    private static int puerto = 8000;
    //Instancia clase
    private static Servidor13 claseServidor;
    private ServerSocket servidor;
    private Socket cliente;
    private ObjectOutputStream flujoEscritura;
    private ObjectInputStream flujoLectura;

    public static void main(String[] args) {
        //Instancio clase
        claseServidor = new Servidor13();
        //Inicio lista de personas inscritas
        personasInscritas = new ArrayList<>();
        //Inicio servidor
        claseServidor.iniciarServidor();
        //Inicio gestion
        claseServidor.iniciarGestion();
    }

    public void iniciarServidor() {
        try {
            servidor = new ServerSocket(puerto);//Inicio servidor
            System.out.println("SERVIDOR INICIADO... ESPERANDO CLIENTE...");
            cliente = servidor.accept();//Espero conexión del cliente
            System.out.println("CONEXIÓN ESTABLECIDA CON EL CLIENTE " + cliente.getPort());
            flujoEscritura = new ObjectOutputStream(cliente.getOutputStream());//Recupero el flujoEscritura
            flujoLectura = new ObjectInputStream(cliente.getInputStream());//Recupero el flujoLectura
        } catch (IOException e) {
            System.out.println("ERROR AL INICIAR EL SERVIDOR");
        }
    }

    public void iniciarGestion() {
        try {
            //Mientras que el cliente no se haya cerrado
            while (!cliente.isClosed()) {
                Peticion peticion = (Peticion) flujoLectura.readObject();//Recibo petición del cliente
                //Si el cliente quiere inscribir una persona al evento
                if (peticion.getPeticion() == 1) {
                    Persona persona = peticion.getPersona();//Recupero la persona
                    personasInscritas.add(persona);//Añado la persona a la lista
                    Respuesta respuesta = new Respuesta(personasInscritas);//Creo respuesta
                    flujoEscritura.writeObject(respuesta);//Mando la respuesta al cliente
                    flujoEscritura.flush();
                    flujoEscritura.reset();
                    //CHECK
                    System.out.println("\nNuevo inscrito. La lista queda:");
                    respuesta.mostrarPersonasInscritas();
                } else {
                    //Si solo quiere el listado de personas inscritas
                    Respuesta respuesta = new Respuesta(personasInscritas);//Creo respuesta
                    flujoEscritura.writeObject(respuesta);//Mando la respuesta al cliente
                    flujoEscritura.flush();
                    flujoEscritura.reset();
                }
            }
        } catch (IOException e) {
            System.out.println("EL CLIENTE SE HA DESCONECTADO, FIN DE LA CONEXIÓN");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR, NO SE ENCONTRO LA CLASE CORRESPONDIENTE");
        }
    }
}
