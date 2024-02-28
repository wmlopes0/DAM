package personajes;

import generico.Celda;
import generico.CentralNuclear;
import generico.Constantes;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Walter
 */
public abstract class Personaje {

    private String nombre;
    private int turno;
    private int idCeldaActual;
    private char marca;
    private ArrayList<Character> lRuta;

    //CONSTRUCTORES
    public Personaje() {
        nombre = "";
        turno = 0;
        idCeldaActual = 0;
        marca = ' ';
        lRuta = new ArrayList<>();
    }

    public Personaje(String nombre, int turno, int idCeldaActual, char marca) {
        this.nombre = nombre;
        this.turno = turno;
        this.idCeldaActual = idCeldaActual;
        this.marca = marca;
        lRuta = new ArrayList<>();
    }

    //SETTER Y GETTER
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public void setIdCeldaActual(int idCeldaActual) {
        this.idCeldaActual = idCeldaActual;
    }

    public void setMarca(char marca) {
        this.marca = marca;
    }

    public void setlRuta(ArrayList<Character> lRuta) {
        this.lRuta = lRuta;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTurno() {
        return turno;
    }

    public int getIdCeldaActual() {
        return idCeldaActual;
    }

    public char getMarca() {
        return marca;
    }

    public ArrayList<Character> getlRuta() {
        return lRuta;
    }

    //MÉTODOS DE LA CLASE PERSONAJE
    public abstract void realizarAcciones(PrintWriter pw);

    public void cargarMovimientos(char[] ruta) {
        for (int i = 0; i < ruta.length; i++) {
            insertarNuevoMovimiento(ruta[i]);
        }
    }

    public void borrarPrimerMovimiento() {
        if (!lRutaVacio()) {
            lRuta.remove(0);
        }
    }

    public void insertarNuevoMovimiento(char movimiento) {
        lRuta.add(movimiento);
    }

    public boolean lRutaVacio() {
        return lRuta.isEmpty();
    }

    public Celda celdaActual() {
        //Patron Singleton
        CentralNuclear miCentral = CentralNuclear.getInstancia();
        return miCentral.retornarCelda(idCeldaActual);
    }

    public boolean comprobarTurno() {
        CentralNuclear central = CentralNuclear.getInstancia();
        return central.getTurno() >= this.getTurno();
    }

    public int calcularSiguienteIdCelda() {
        int idCeldaDestino = idCeldaActual;
        char movimiento = lRuta.get(0);

        switch (movimiento) {
            case Constantes.N:
                idCeldaDestino -= Constantes.COLUMNAS;
                break;
            case Constantes.S:
                idCeldaDestino += Constantes.COLUMNAS;
                break;
            case Constantes.O:
                idCeldaDestino -= 1;
                break;
            case Constantes.E:
                idCeldaDestino += 1;
                break;
            default:
                break;
        }
        //Compruebo que no se sale de la matriz
        if (idCeldaDestino < Constantes.PRIMERACELDA || idCeldaDestino > Constantes.ULTIMACELDA) {
            idCeldaDestino = idCeldaActual;
        }
        return idCeldaDestino;
    }

    /* He actualizado este método en esta entraga final para que retorne un booleano si el movimiento se ha 
    realizado correctamente*/
    public boolean mover(PrintWriter pw) {
        int idCeldaDestino;
        boolean movimientoRealizado = false;
        //Patron Singleton
        CentralNuclear miCentral = CentralNuclear.getInstancia();

        //Si lRuta no esta vacio calculo el siguiente idCelda 
        if (!lRutaVacio()) {
            idCeldaDestino = calcularSiguienteIdCelda();

            //CELDA ACTUAL Y CELDA DESTINO
            Celda celdaActual = miCentral.retornarCelda(idCeldaActual);
            Celda celdaDestino = miCentral.retornarCelda(idCeldaDestino);

            //ACCION MOVER
            if (miCentral.hayCamino(idCeldaActual, idCeldaDestino) && idCeldaDestino != idCeldaActual) {
                celdaActual.borrarPersonaje(nombre);
                celdaDestino.insertarPersonaje(this);
                idCeldaActual = idCeldaDestino;
                movimientoRealizado = true;
            } else {
                pw.println(this.getNombre() + Constantes.DOSPUNTOS + Constantes.NOPUEDO + "(" + lRuta.get(0) + ")");
            }
            borrarPrimerMovimiento();
        }
        return movimientoRealizado;
    }
}
