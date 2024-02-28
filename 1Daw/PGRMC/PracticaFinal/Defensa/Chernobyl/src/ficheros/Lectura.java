package ficheros;

import generico.CentralNuclear;
import generico.Constantes;
import generico.Llave;
import generico.Puerta;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import personajes.Bombero;
import personajes.Cientifico;
import personajes.Minero;
import personajes.Oficial;
import personajes.Robot;
import personajes.Voluntario;

/**
 *
 * @author Walter
 */
public class Lectura {

    public static boolean cargarFichero() {
        File fichero = new File(Constantes.FICHERO_INICIO);
        boolean correcto = true;

        FileReader fr = null;
        BufferedReader br = null;

        try {
            //ABRO FLUJOS
            fr = new FileReader(fichero);
            br = new BufferedReader(fr);
            leerFichero(br);
        } catch (FileNotFoundException ex) {
            System.out.println(Constantes.ERROR_FICHERO_NO_ENCONTRADO);
            correcto = false;
        } catch (IOException e) {
            System.out.println(Constantes.ERROR_ENTRADA_SALIDA);
            correcto = false;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    System.out.println(Constantes.ERROR_ENTRADA_SALIDA);
                    correcto = false;
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException ex) {
                    System.out.println(Constantes.ERROR_ENTRADA_SALIDA);
                    correcto = false;
                }
            }
        }
        return correcto;
    }

    public static void leerFichero(BufferedReader br) throws IOException {
        String linea;
        //LEO LINEA A LINEA
        linea = br.readLine();
        while (linea != null) {
            queElemento(linea);
            linea = br.readLine();
        }
    }

    public static void queElemento(String linea) {
        String[] info;
        String objeto;

        if (!linea.startsWith(Constantes.COMENTARIO)) {
            info = linea.split(Constantes.SEPARADOR);
            objeto = info[0];

            if (objeto.equals(Constantes.CENTRAL)) {
                crearCentral(info);
            } else if (esPersonaje(objeto)) {
                crearPersonaje(info);
            } else if (objeto.equals(Constantes.LLAVE)) {
                crearLlave(info);
            } else if (objeto.equals(Constantes.PUERTA)) {
                crearPuerta(info);
            }
        }
    }

    public static boolean esPersonaje(String objeto) {
        return objeto.equals(Constantes.CIENTIFICO)
                || objeto.equals(Constantes.MINERO)
                || objeto.equals(Constantes.BOMBERO)
                || objeto.equals(Constantes.OFICIAL)
                || objeto.equals(Constantes.VOLUNTARIO)
                || objeto.equals(Constantes.ROBOT);
    }

    public static void crearCentral(String[] info) {
        int turnosNecesarios;
        turnosNecesarios = Integer.parseInt(info[1]);
        CentralNuclear central = CentralNuclear.getInstancia();

        central.setTurnosNecesarios(turnosNecesarios);
        central.inicializarEscombros();
    }

    public static void crearPersonaje(String[] info) {
        String tipo, nombre, rutaRobot;
        int turno, celdaActual;
        char marca;
        char[] ruta;

        //SINGLETON
        CentralNuclear central = CentralNuclear.getInstancia();

        //ATRIBUTOS
        tipo = info[0];
        nombre = info[1];
        turno = Integer.parseInt(info[2]);
        celdaActual = Integer.parseInt(info[3]);
        marca = info[4].charAt(0);
        ruta = info[5].toCharArray();

        //CREACIÓN
        switch (tipo) {
            case Constantes.BOMBERO:
                Bombero b = new Bombero(nombre, turno, celdaActual, marca);
                b.cargarMovimientos(ruta);
                central.insPersonaje(b);
                break;
            case Constantes.MINERO:
                Minero m = new Minero(nombre, turno, celdaActual, marca);
                m.cargarMovimientos(ruta);
                central.insPersonaje(m);
                break;
            case Constantes.CIENTIFICO:
                Cientifico c = new Cientifico(nombre, turno, celdaActual, marca);
                c.cargarMovimientos(ruta);
                central.insPersonaje(c);
                break;
            case Constantes.OFICIAL:
                Oficial o = new Oficial(nombre, turno, celdaActual, marca);
                o.cargarMovimientos(ruta);
                central.insPersonaje(o);
                break;
            case Constantes.VOLUNTARIO:
                Voluntario v = new Voluntario(nombre, turno, celdaActual, marca);
                v.cargarMovimientos(ruta);
                central.insPersonaje(v);
                break;
            case Constantes.ROBOT:
                Robot r = new Robot(nombre, turno, celdaActual, marca);
                rutaRobot = info[5];
                r.descifrarRuta(rutaRobot);
                central.insPersonaje(r);
                break;
        }
    }

    public static void crearLlave(String[] info) {
        int codigo, idCelda;

        //SINGLETON
        CentralNuclear central = CentralNuclear.getInstancia();

        //ATRIBUTOS
        codigo = Integer.parseInt(info[1]);
        idCelda = Integer.parseInt(info[2]);

        //CREACIÓN
        Llave llave = new Llave(codigo, idCelda);
        central.insLlaveEnCelda(llave);
    }

    public static void crearPuerta(String[] info) {
        int idCelda, llaveCerradura;

        //SINGLETON
        CentralNuclear central = CentralNuclear.getInstancia();

        //ATRIBUTOS
        idCelda = Integer.parseInt(info[1]);
        llaveCerradura = Integer.parseInt(info[2]);

        //CREACIÓN
        Puerta puerta = new Puerta(idCelda, llaveCerradura);
        central.insPuertaSalida(puerta);
    }
}
