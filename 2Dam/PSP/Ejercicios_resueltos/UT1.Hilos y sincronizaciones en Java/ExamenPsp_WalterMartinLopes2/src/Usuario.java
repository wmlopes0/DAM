import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Usuario extends Thread {

    //Atributos
    private String nombre;
    private Servidor servidor;
    private List<String> archivos; //Lista de nombres de archivos

    //Constructor
    public Usuario(String nombre, Servidor servidor, List<String> nombreArchivos) {
        this.nombre = nombre;
        this.servidor = servidor;
        this.archivos = nombreArchivos;
    }

    //Getter
    public String getNombre() {
        return nombre;
    }

    //Método que retorna el nombre de un archivo aleatoriamente
    public String nombreArchivoAleatorio() {
        Collections.shuffle(archivos);//Método que desordena la lista
        return archivos.get(0);//Retorno la primera posición
    }

    @Override
    public void run() {
        //Cada Usuario descarga dos archivos aleatoriamente como dice el enunciado
        for (int i = 0; i < 2; i++) {
            servidor.descargar(nombre, nombreArchivoAleatorio());
        }
    }
}
