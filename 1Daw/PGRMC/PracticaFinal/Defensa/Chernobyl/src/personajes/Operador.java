package personajes;

import generico.Celda;
import java.io.PrintWriter;

/**
 *
 * @author Walter
 */
public abstract class Operador extends Personaje {

    private boolean reconocido;
    private boolean enPuertaDeSalida;

    //CONSTRUCTORES
    public Operador() {
        super();
        reconocido = false;
        enPuertaDeSalida = false;
    }

    public Operador(String nombre, int turno, int idCeldaActual, char marca) {
        super(nombre, turno, idCeldaActual, marca);
        reconocido = false;
        enPuertaDeSalida = false;
    }

    //SETTER Y GETTER
    public void setReconocido(boolean reconocido) {
        this.reconocido = reconocido;
    }

    public void setEnPuertaDeSalida(boolean enPuertaDeSalida) {
        this.enPuertaDeSalida = enPuertaDeSalida;
    }

    public boolean isReconocido() {
        return reconocido;
    }

    public boolean isEnPuertaDeSalida() {
        return enPuertaDeSalida;
    }

    //MÉTODOS DE LA CLASE OPERADOR
    @Override
    public abstract void realizarAcciones(PrintWriter pw);

    public boolean comprobarPuertaSalida() {
        //Celda actual
        Celda celdaActual = this.celdaActual();

        if (!enPuertaDeSalida) {
            if (celdaActual.getPuerta() != null) {
                enPuertaDeSalida = true;
            } else {
                enPuertaDeSalida = false;
            }
        }
        return enPuertaDeSalida;
    }
}
