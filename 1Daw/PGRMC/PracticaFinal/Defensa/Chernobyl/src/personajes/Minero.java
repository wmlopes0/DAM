package personajes;

import generico.Celda;
import java.util.ArrayList;
import generico.Constantes;
import java.io.PrintWriter;

/**
 *
 * @author Walter
 */
public class Minero extends Operador {

    private ArrayList<Integer> lEscombros;

    //CONSTRUCTORES
    public Minero() {
        super();
        lEscombros = new ArrayList<>();
    }

    public Minero(String nombre, int turno, int idCeldaActual, char marca) {
        super(nombre, turno, idCeldaActual, marca);
        lEscombros = new ArrayList<>();
    }

    //SETTER Y GETTER
    public void setlEscombros(ArrayList<Integer> lEscombros) {
        this.lEscombros = lEscombros;
    }

    public ArrayList<Integer> getlEscombros() {
        return lEscombros;
    }

    //MÉTODOS DE LA CLASE MINERO
    //Accion desescombrar
    public void desescombrar(PrintWriter pw) {
        //Celda actual
        Celda celdaActual = this.celdaActual();

        if (celdaActual.getEscombros() > 0) { //Si tiene escombros...
            lEscombros.add(celdaActual.getEscombros());//Añado escombros a la lista
            celdaActual.setEscombros(0);//Elimino los escombros recogidos de la celda
            mostrarEscombrosRecogidosCelda(pw);//Muestro el total de escombros
        }
    }

    public void mostrarEscombrosRecogidosCelda(PrintWriter pw) {
        int totalEscombros = 0;
        for (int i = 0; i < lEscombros.size(); i++) {
            totalEscombros += lEscombros.get(i);
        }
        pw.println(super.getNombre() + Constantes.DOSPUNTOS + Constantes.MENSAJE_MINERO1 + totalEscombros + Constantes.MENSAJE_MINERO2);
    }

    public void mostrarEscombrosRecogidos(PrintWriter pw) {
        int totalEscombros = 0;
        for (int i = 0; i < lEscombros.size(); i++) {
            totalEscombros += lEscombros.get(i);
        }
        pw.println(super.getNombre() + Constantes.DOSPUNTOS + Constantes.MENSAJE_MINERO + totalEscombros);
    }

    @Override
    public void realizarAcciones(PrintWriter pw) {
        /*En primer lugar compruebo si es su turno, seguidamente controlo si está en la puerta 
          de salida,si lo está no hace nada, si no lo está realiza sus acciones con normalidad*/
        if (this.comprobarTurno()) {
            if (!comprobarPuertaSalida()) {
                super.mover(pw);
                desescombrar(pw);
            }
        }
    }
}
