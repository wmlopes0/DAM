package personajes;

import generico.Celda;
import generico.CentralNuclear;
import generico.Constantes;
import generico.Utilidad;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Walter
 */
public class Robot extends Personaje {

    private Queue<Integer> registroCeldas;

    //CONSTRUCTORES
    public Robot() {
        super();
        registroCeldas = new LinkedList<>();
    }

    public Robot(String nombre, int turno, int idCeldaActual, char marca) {
        super(nombre, turno, idCeldaActual, marca);
        registroCeldas = new LinkedList<>();
    }

    public Robot(String nombre, int turno, int idCeldaActual, char marca, Queue<Integer> registroCeldas) {
        super(nombre, turno, idCeldaActual, marca);
        this.registroCeldas = registroCeldas;
    }

    //SETTER Y GETTER
    public void setRegistroCeldas(Queue<Integer> registroCeldas) {
        this.registroCeldas = registroCeldas;
    }

    public Queue<Integer> getRegistroCeldas() {
        return registroCeldas;
    }

    //MÉTODOS DE LA CLASE ROBOT
    public void descifrarRuta(String rutaCifrada) {
        char movimiento;
        String rutaDescifrada = "";

        /* Recorro la ruta cifrada letra a letra, si alguna de ellas coincide con algun movimiento 
        la concateno en el string rutaDescifrada*/
        for (int i = 0; i < rutaCifrada.length(); i++) {
            movimiento = rutaCifrada.charAt(i);
            if (movimiento == Constantes.N || movimiento == Constantes.S || movimiento == Constantes.E || movimiento == Constantes.O) {
                rutaDescifrada += movimiento;
            }
        }

        //Cargo la ruta descifrada
        this.cargarMovimientos(rutaDescifrada.toCharArray());
    }

    /*Este método recibe por parametro un booleano que indica si el personaje ha conseguido moverse o no*/
    public void registrarMovimiento(boolean movimientoRealizado) {
        int idCelda;
        idCelda = this.getIdCeldaActual();

        /*Si se ha realizado el movimiento añado el idCelda actual, sino un -1*/
        if (movimientoRealizado) {
            registroCeldas.add(idCelda);
        } else {
            registroCeldas.add(-1);
        }
    }

    //Acción escanear, el numero de personajes que se encuentra en el area de influencia.
    public void escanear(PrintWriter pw) {
        int idCeldaActual, fila, columna, sumaPersonajes;
        Celda celda;
        List<Integer> adyacentes;

        idCeldaActual = this.getIdCeldaActual();
        //Obtengo fila y columna de la idCelda actual
        fila = Utilidad.calcularFila(idCeldaActual);
        columna = Utilidad.calcularColumna(idCeldaActual);
        //Patron Singleton
        CentralNuclear central = CentralNuclear.getInstancia();
        //Obtengo adyacentes
        adyacentes = central.getAdyacentes(fila, columna);

        //Sumar personajes adyacentes
        sumaPersonajes = 0;
        for (Integer adyacente : adyacentes) {
            celda = central.retornarCelda(adyacente);
            sumaPersonajes += celda.getPersonajes().size();
        }

        //Muestro mensaje si hay algun personaje adyacente
        if (sumaPersonajes > 0) {
            pw.println(this.getNombre() + Constantes.DOSPUNTOS + Constantes.MENSAJE_ROBOT_ESCANEAR1 + sumaPersonajes + Constantes.MENSAJE_ROBOT_ESCANEAR2);
        }
    }

    public void mostrarCeldasVisitadas(PrintWriter pw) {
        pw.println(Constantes.MENSAJE_ROBOT_REGISTRAR + this.getNombre());
        for (Integer registro : registroCeldas) {
            pw.print("[" + registro + "]");
        }
    }

    @Override
    public void realizarAcciones(PrintWriter pw) {
        boolean movimientoRealizado;
        if (this.comprobarTurno()) {
            movimientoRealizado = super.mover(pw);
            registrarMovimiento(movimientoRealizado);
            escanear(pw);
        }
    }

}
