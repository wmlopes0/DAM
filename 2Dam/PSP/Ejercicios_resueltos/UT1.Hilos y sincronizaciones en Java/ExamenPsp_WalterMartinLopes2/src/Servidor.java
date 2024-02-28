import java.util.Map;

public class Servidor {
    private Map<String, Archivo> archivos;//Mapa de archivos
    private float maxAnchoBanda;//Máximo ancho de banda permitido para la descarga
    private float anchoBandaActual;//En esta variable se actualiza el ancho de banda consumido en cada momento

    //Constructor
    public Servidor(Map<String, Archivo> listaArchivos, float maxAnchoBanda) {
        this.archivos = listaArchivos;
        this.maxAnchoBanda = maxAnchoBanda;
    }

    //Método para sincronizar la descarga
    public void descargar(String nombreUsuario, String nombreArchivo) {

        System.out.println("El usuario " + nombreUsuario + " desea descargarse el archivo: " + nombreArchivo + " de " + archivos.get(nombreArchivo).getTamanio() + "MB");

        //BLOQUE SINCRONIZADO PARA INICIAR LA DESCARGA SI ES POSIBLE
        synchronized (this) {
            //Controlo que el ancho de banda actual + el peso del archivo que quiere descargar no sobrepase el maxAnchoBanda
            while ((anchoBandaActual + archivos.get(nombreArchivo).getTamanio() >= maxAnchoBanda)) {
                System.out.println("XXActualmente se están descargando simultaneamente " + anchoBandaActual + "MB. Usuario: " + nombreUsuario + " esperando...");
                try {
                    wait();//Espero
                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
                }
            }
            anchoBandaActual += archivos.get(nombreArchivo).getTamanio();//Actualizo la variable anchoBandaActual dentro del bloque sincronizado
        }

        //SIMULACIÓN DESCARGA
        System.out.println("\t==>Se está descargando el archivo....." + nombreArchivo + "(" + nombreUsuario + ") de " + archivos.get(nombreArchivo).getTamanio() + "MB");
        try {
            Thread.sleep((int) archivos.get(nombreArchivo).getTamanio());//Simulo el tiempo de descarga a 1MB/1ms
        } catch (InterruptedException e) {
//                throw new RuntimeException(e);
        }

        //BLOQUE SINCRONIZADO PARA TERMINAR LA DESCARGA
        synchronized (this) {
            anchoBandaActual -= archivos.get(nombreArchivo).getTamanio();//Actualizo la variable anchoBandaActual dentro del bloque sincronizado
            System.out.println("\t\t\tArchivo " + nombreArchivo + "(" + nombreUsuario + ") descargado.....");
            notifyAll();//Notifico que he liberado ancho de banda
        }
    }
}
