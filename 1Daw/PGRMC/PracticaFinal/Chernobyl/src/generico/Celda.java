package generico;

import java.util.ArrayList;
import personajes.*;

/**
 *
 * @author Walter
 */
public class Celda {

    private int idCelda;
    private ArrayList<Personaje> lPersonajes;
    private boolean refrigerada;
    private int escombros;
    private Puerta puerta;
    private Llave llave;

    //CONSTRUCTORES
    public Celda() {
        idCelda = 0;
        lPersonajes = new ArrayList<>();
        refrigerada = false;
        escombros = 0;
        puerta = null;
        llave = null;
    }

    public Celda(int idCelda) {
        this.idCelda = idCelda;
        lPersonajes = new ArrayList<>();
        refrigerada = false;
        escombros = 0;
        puerta = null;
        llave = null;
    }

    public Celda(int idCelda, boolean refrigerada, int escombros) {
        this.idCelda = idCelda;
        lPersonajes = new ArrayList<>();
        this.refrigerada = refrigerada;
        this.escombros = escombros;
        puerta = null;
        llave = null;
    }

    //SETTER Y GETTER
    public void setIdCelda(int idCelda) {
        this.idCelda = idCelda;
    }

    public void setPersonajes(ArrayList<Personaje> lPersonajes) {
        this.lPersonajes = lPersonajes;
    }

    public void setRefrigerada(boolean refrigerada) {
        this.refrigerada = refrigerada;
    }

    public void setEscombros(int escombros) {
        this.escombros = escombros;
    }

    public void setPuerta(Puerta puerta) {
        this.puerta = puerta;
    }

    public void setLlave(Llave llave) {
        this.llave = llave;
    }

    public int getIdCelda() {
        return idCelda;
    }

    public ArrayList<Personaje> getPersonajes() {
        return lPersonajes;
    }

    public boolean isRefrigerada() {
        return refrigerada;
    }

    public int getEscombros() {
        return escombros;
    }

    public Puerta getPuerta() {
        return puerta;
    }

    public Llave getLlave() {
        return llave;
    }

    //MÉTODOS DE LA CLASE CELDA
    public void insertarPersonaje(Personaje personaje) {
        lPersonajes.add(personaje);
    }

    public Personaje buscarPersonaje(String nombre) {
        int pos = 0;
        boolean encontrado = false;
        Personaje personaje = null;

        //Realizo la búsqueda
        while (pos < lPersonajes.size() && !encontrado) {
            if (nombre.equalsIgnoreCase(lPersonajes.get(pos).getNombre())) {
                encontrado = true;
                personaje = lPersonajes.get(pos);
            } else {
                pos++;
            }

        }
        return personaje;
    }

    public void borrarPersonaje(String nombre) {
        int contador = 0;
        boolean encontrado = false;

        //Realizo la búsqueda del personaje
        while (contador < lPersonajes.size() && !encontrado) {
            if (nombre.equalsIgnoreCase(lPersonajes.get(contador).getNombre())) {
                encontrado = true;
                lPersonajes.remove(contador);
            } else {
                contador++;
            }
        }
    }

    public boolean buscarAlgunOperador() {
        int contador = 0;
        boolean hayOperador = false;
        //Realizo la búsqueda del personaje
        while (contador < lPersonajes.size() && !hayOperador) {
            if ((lPersonajes.get(contador) instanceof Operador)) {
                hayOperador = true;
            } else {
                contador++;
            }
        }
        return hayOperador;
    }

    public Personaje retornarPersonaje(int posicion) {
        return lPersonajes.get(posicion);
    }

    public int cantidadPersonajesCelda() {
        return lPersonajes.size();
    }

    //MÉTODOS PARA PINTAR EL MAPA
    @Override
    public String toString() {
        if (!this.lPersonajes.isEmpty()) {
            if (this.lPersonajes.size() == 1) {
                //valueOf convierte a String el char recibido por parámetro
                return String.valueOf(this.lPersonajes.get(0).getMarca());
            } else {
                //valueOf convierte a String el int recibido por parámetro
                return String.valueOf(this.lPersonajes.size());
            }
        } else {
            //Si no hay personajes y hay una llave pinto su símbolo
            if (this.llave != null) {
                return Constantes.SIMBOLO_LLAVE;
            } else {
                return Constantes.CERO;
            }
        }
    }
}
