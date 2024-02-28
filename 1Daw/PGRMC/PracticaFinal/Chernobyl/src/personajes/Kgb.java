package personajes;

import generico.Celda;
import java.io.PrintWriter;

/**
 *
 * @author Walter
 */
public abstract class Kgb extends Personaje {

    //CONSTRUCTORES
    public Kgb() {
        super();
    }

    public Kgb(String nombre, int turno, int idCeldaActual, char marca) {
        super(nombre, turno, idCeldaActual, marca);
    }

    //MÉTODOS DE LA CLASE KGB
    //Accion de catalogar
    public void catalogar(PrintWriter pw) {
        //Celda actual
        Celda celdaActual = this.celdaActual();
        //Busqueda de operadores y accion de catalogar
        for (Personaje personaje : celdaActual.getPersonajes()) {
            if (personaje instanceof Operador) {
                //Creo un operador y le asigno el personaje haciendo casting para acceder al atributo reconocido
                Operador operador = (Operador) personaje;
                if (!operador.isReconocido()) {
                    operador.setReconocido(true);
                    mostrarInfoCatalogado(operador, pw);
                }
            }
        }
    }

    public abstract void mostrarInfoCatalogado(Operador operador, PrintWriter pw);

    @Override
    public abstract void realizarAcciones(PrintWriter pw);

}
