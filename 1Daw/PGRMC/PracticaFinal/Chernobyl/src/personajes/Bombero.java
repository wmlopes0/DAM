package personajes;

import generico.Celda;
import generico.Constantes;
import java.io.PrintWriter;

/**
 *
 * @author Walter
 */
public class Bombero extends Operador {

    private int cargaRefrigerador;

    //CONSTRUCTORES
    public Bombero() {
        super();
        recargarRefrigerador();
    }

    public Bombero(String nombre, int turno, int idCeldaActual, char marca) {
        super(nombre, turno, idCeldaActual, marca);
        recargarRefrigerador();
    }

    //SETTER Y GETTER
    public void setCargaRefrigerador(int cargaRefrigerador) {
        this.cargaRefrigerador = cargaRefrigerador;
    }

    public int getCargaRefrigerador() {
        return cargaRefrigerador;
    }

    //MÉTODOS DE LA CLASE BOMBERO
    //Acción refrigerar
    public void refrigerar() {
        //Celda actual
        Celda celdaActual = this.celdaActual();
        //Refrigerar
        celdaActual.setRefrigerada(true);
        cargaRefrigerador -= Constantes.DESCARGA_REFRIGERADOR;
    }

    private void recargarRefrigerador() {
        cargaRefrigerador = Constantes.RECARGA_REFRIGERADOR;
    }

    private boolean comprobarRefriferador() {
        return cargaRefrigerador == 0;
    }

    @Override
    public void realizarAcciones(PrintWriter pw) {
        /*En primer lugar compruebo si es su turno, seguidamente controlo si está en la puerta 
          de salida,si lo está no hace nada, si no lo está realiza sus acciones con normalidad*/
        if (this.comprobarTurno()) {
            if (!comprobarPuertaSalida()) {
                if (!comprobarRefriferador()) {
                    super.mover(pw);
                    refrigerar();
                } else {
                    recargarRefrigerador();
                    pw.println(this.getNombre() + Constantes.DOSPUNTOS + Constantes.RECARGANDO_REFRIGERADOR);
                }
            }
        }
    }
}
