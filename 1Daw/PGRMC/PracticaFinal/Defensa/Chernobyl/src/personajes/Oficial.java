package personajes;

import generico.Celda;
import generico.Constantes;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Walter
 */
public class Oficial extends Kgb {

    private List<Integer> celdasCatalogados;

    //CONSTRUCTORES
    public Oficial() {
        super();
        this.celdasCatalogados = new ArrayList<>();
    }

    public Oficial(String nombre, int turno, int idCeldaActual, char marca) {
        super(nombre, turno, idCeldaActual, marca);
        this.celdasCatalogados = new ArrayList<>();
    }

    //SETTER Y GETTER
    public void setCeldasCatalogados(List<Integer> celdasCatalogados) {
        this.celdasCatalogados = celdasCatalogados;
    }

    public List<Integer> getCeldasCatalogados() {
        return celdasCatalogados;
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

        //Añado el idCelda en el que se ha catalogado al operario
        insertarIdCeldaCatalogado();
    }

    //Métodos de la defensa
    private void insertarIdCeldaCatalogado() {
        int idCeldaActual;

        //Añado el idCeldaActual al ArrayList de enteros 
        idCeldaActual = this.getIdCeldaActual();
        celdasCatalogados.add(idCeldaActual);
    }

    public void mostrarCeldasCatalogados(PrintWriter pw) {
        for (Integer idCelda : celdasCatalogados) {
            pw.print("[" + idCelda + "]");
        }
    }
}
