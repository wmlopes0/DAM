import java.util.*;

public class Main {

    //Variables globales
    private static Map<String, Archivo> archivos = new HashMap<>(); //Mapa de archivos que paso a servidor
    private static List<String> nombreArchivos = new ArrayList<>(); //Lista de nombres de archivos que paso a usuario

    public static void main(String[] args) {
        //Relleno la lista de nombres y el mapa de Archivos
        rellenarArchivos();
        //Creo el servidor pasando por parámetro el mapa de Archivos y el ancho de banda máximo
        Servidor servidor = new Servidor(archivos, 10000);
        //Creo Usuarios pasandole su nombre, el objeto controlador y la lista de nombres de archivos
        Usuario usuario1 = new Usuario("David", servidor, nombreArchivos);
        Usuario usuario2 = new Usuario("María", servidor, nombreArchivos);
        Usuario usuario3 = new Usuario("Sergio", servidor, nombreArchivos);
        Usuario usuario4 = new Usuario("Marta", servidor, nombreArchivos);

        //Inicio hilos
        usuario1.start();
        usuario2.start();
        usuario3.start();
        usuario4.start();

        //Espero a que terminen todos los hilos para finalizar el hilo principal
        try {
            usuario1.join();
            usuario2.join();
            usuario3.join();
            usuario4.join();
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }

        System.out.println("\t***************************************");
        System.out.println("********* PROGRAMA FINALIZADO *********");
        System.out.println("***************************************");
    }

    //Método que rellena tanto la lista de nombres de archivos como el mapa de objetos archivos
    public static void rellenarArchivos() {
        for (int i = 0; i < 10; i++) {
            //Introduzco como clave del mapa el nombre del archivo para facilitarme el trabajo en el objeto controlador
            archivos.put("archivo-" + i, new Archivo("archivo-" + i));
            nombreArchivos.add("archivo-" + i);
        }
    }
}
