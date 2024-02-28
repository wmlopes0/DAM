package personajes;

import generico.Constantes;
import java.io.PrintWriter;

/**
 *
 * @author Walter
 */
public class Voluntario extends Kgb {

    //CONSTRUCTORES
    public Voluntario() {
        super();
    }

    public Voluntario(String nombre, int turno, int idCeldaActual, char marca) {
        super(nombre, turno, idCeldaActual, marca);
    }

    //MÉTODOS DE LA CLASE VOLUNTARIO
    @Override
    public void realizarAcciones(PrintWriter pw) {
        if (this.comprobarTurno()) {
            super.mover(pw);
            catalogar(pw);
        }

    }

    //MÉTODOS MOSTRAR INFORMACIÓN
    @Override
    public void mostrarInfoCatalogado(Operador catalogado, PrintWriter pw) {
        pw.println(super.getNombre() + Constantes.DOSPUNTOS + " " + catalogado.getNombre() + Constantes.SEPARADOR + catalogado.getIdCeldaActual() + Constantes.SEPARADOR + catalogado.getMarca());
    }
}
