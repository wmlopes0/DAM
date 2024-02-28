package personajes;

import generico.Celda;
import generico.Constantes;
import generico.Llave;
import generico.Puerta;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Walter
 */
public class Cientifico extends Operador {

    private Map<Integer, Llave> llaves;

    //CONSTRUCTORES
    public Cientifico() {
        super();
        llaves = new HashMap<>();
    }

    public Cientifico(String nombre, int turno, int idCeldaActual, char marca) {
        super(nombre, turno, idCeldaActual, marca);
        llaves = new HashMap<>();
    }

    //SETTER Y GETTER
    public void setLlaves(Map<Integer, Llave> llaves) {
        this.llaves = llaves;
    }

    public Map<Integer, Llave> getLlaves() {
        return llaves;
    }

    //MÉTODOS DE LA CLASE CIENTÍFICO
    public void cogerLlave(PrintWriter pw) {
        Llave llave;
        //Obtengo el objeto celda donde se encuentra
        Celda celdaActual = this.celdaActual();

        llave = celdaActual.getLlave();

        //Si hay una llave en el suelo
        if (llave != null) {
            llaves.put(llave.getIdLlave(), llave);
            celdaActual.setLlave(null);
            pw.println(this.getNombre() + Constantes.DOSPUNTOS + Constantes.MENSAJE_CIENTIFICO + celdaActual.getIdCelda() + ".");
        }
    }

    public boolean abrirPuerta() {
        Puerta puerta;
        int idLlave;
        boolean puertaAbierta = false;
        //Obtengo el objeto celda donde se encuentra
        Celda celdaActual = this.celdaActual();
        //Obtengo la puerta
        puerta = celdaActual.getPuerta();

        if (puerta != null) {
            idLlave = puerta.getIdLlave();
            if (llaves.containsKey(idLlave)) {
                puerta.setAbierta(true);
                puertaAbierta = true;
            }
        }

        return puertaAbierta;
    }

    @Override
    public void realizarAcciones(PrintWriter pw) {
        /*En primer lugar compruebo si es su turno, seguidamente controlo si está en la puerta
        de salida,si lo está no hace nada, si no lo está realiza sus acciones con normalidad.
        NOTA: Se comprueba 2 veces si está en puerta de salida puesto que según el orden de las acciones
        especificado en la práctica, después de comprobarlo una primera vez, se mueve el personaje, por lo tanto 
        puede que despues de moverse si esté en puerta de salida.*/
        if (this.comprobarTurno()) {
            if (!comprobarPuertaSalida()) {
                super.mover(pw);
                cogerLlave(pw);
                if (abrirPuerta()) {
                    pw.println(this.getNombre() + Constantes.DOSPUNTOS + Constantes.MENSAJE_ABRIR_PUERTA);
                }
            }
        }
    }
}
