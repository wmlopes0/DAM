package repasoexamentablemodel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wmartinl01
 */
public class LogicaNegocio {

    private List<Mensaje> mensajes = new ArrayList<>();

    public LogicaNegocio() {
        Mensaje mensaje1 = new Mensaje("Walter", "Izzy", "Patata", "Vamos a comer patatas");
        Mensaje mensaje2 = new Mensaje("Pepe", "Didac", "Fresa", "Vamos a comer fresas");
        Mensaje mensaje3 = new Mensaje("Jorge", "Rosa", "Platano", "Vamos a comer platanos");
        Mensaje mensaje4 = new Mensaje("Roberto", "Rene", "Banana", "Vamos a comer bananas");
        mensajes.add(mensaje1);
        mensajes.add(mensaje2);
        mensajes.add(mensaje3);
        mensajes.add(mensaje4);
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    
}
