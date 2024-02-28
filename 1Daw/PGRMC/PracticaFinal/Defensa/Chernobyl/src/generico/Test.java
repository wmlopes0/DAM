package generico;

import ficheros.Escritura;
import ficheros.Lectura;
import java.io.FileWriter;
import java.io.PrintWriter;
import personajes.*;

/**
 *
 * @author Walter
 */
public class Test {

    //Realiza todo lo necesario para el tratamiento de un personaje en su turno
    public static void tratamientoPersonaje(Personaje personaje, PrintWriter pw) {
        CentralNuclear central = CentralNuclear.getInstancia();
        int turnoP = personaje.getTurno();
        if (turnoP == central.getTurno()) {
            personaje.realizarAcciones(pw);
            personaje.setTurno(turnoP + 1);
        }
    }

    public static void simulacion() {
        Personaje personajeEnTratamiento;
        CentralNuclear central = CentralNuclear.getInstancia();

        //Abrimos flujos para escritura de fichero simulación
        FileWriter fw = Escritura.abrirFlujoFW();
        PrintWriter pw = Escritura.abrirFlujoPW(fw);

        //ESCOMBROS
        central.inicializarEscombros();

        pw.println(Constantes.TURNO + central.getTurno());
        central.pintarMatriz(pw);

        //SIMULACIÖN
        for (int i = 0; i < central.getTurnosNecesarios(); i++) {
            central.setTurno(central.getTurno() + 1);

            pw.println(Constantes.TURNO + central.getTurno());

            for (int j = 0; j < central.getSizeListaPersonajes(); j++) {
                personajeEnTratamiento = central.getPersonajeDeListaPorPosicion(j);
                tratamientoPersonaje(personajeEnTratamiento, pw);
            }

            central.pintarMatriz(pw);
        }
        Escritura.cerrarFicheroSimulacion(fw, pw);
        Escritura.escribirInformesEnFicheroLog();
        Escritura.escribirInformeCeldasCatalogadas();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean cargado;
        cargado = Lectura.cargarFichero();
        if (cargado) {
            simulacion();
            System.out.println(Constantes.MENSAJEOK);
        }
    }
}
