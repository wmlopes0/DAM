package personajes;

import generico.Celda;
import generico.Constantes;
import java.io.PrintWriter;

/**
 *
 * @author Walter
 */
public class Oficial extends Kgb {

    //CONSTRUCTORES
    public Oficial() {
        super();
    }

    public Oficial(String nombre, int turno, int idCeldaActual, char marca) {
        super(nombre, turno, idCeldaActual, marca);
    }

    //MÉTODOS DE LA CLASE OFICIAL
    public void destruirLlave(PrintWriter pw) {
        //Obtengo el objeto celda donde se encuentra
        Celda celdaActual = this.celdaActual();

        //Si hay una llave la destruyo
        if (celdaActual.getLlave() != null) {
            celdaActual.setLlave(null);
            pw.println(this.getNombre() + Constantes.DOSPUNTOS + Constantes.MENSAJE_OFICIAL + celdaActual.getIdCelda() + ".");
        }
    }

    @Override
    public void realizarAcciones(PrintWriter pw) {
        if (this.comprobarTurno()) {
            super.mover(pw);
            catalogar(pw);
            destruirLlave(pw);
        }
    }

    //MÉTODOS MOSTRAR INFORMACIÓN
    @Override
    public void mostrarInfoCatalogado(Operador catalogado, PrintWriter pw) {
        String tipoOperador = "";

        if (catalogado instanceof Bombero) {
            tipoOperador = Constantes.BOMBERO;
        } else if (catalogado instanceof Cientifico) {
            tipoOperador = Constantes.CIENTIFICO;
        } else if (catalogado instanceof Minero) {
            tipoOperador = Constantes.MINERO;
        }

        pw.println(super.getNombre() + ": " + tipoOperador + Constantes.SEPARADOR + catalogado.getNombre() + Constantes.SEPARADOR + catalogado.getIdCeldaActual() + Constantes.SEPARADOR + catalogado.getMarca());
    }
}
