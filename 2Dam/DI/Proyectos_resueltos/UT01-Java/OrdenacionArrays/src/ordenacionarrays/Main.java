package ordenacionarrays;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Walter
 */
public class Main {

    //File del fichero
    public static File fichero = new File("Alumnos.txt");

    //Creamos un Array de 200 como dice el enunciado
    public static Alumno[] arrayAlumnos = new Alumno[200];

    public static void main(String[] args) throws InterruptedException {
        animacion("LEYENDO ARCHIVO");
        //Leo el fichero, creo los objetos y los almaceno en el Array
        try {
            leerFichero();
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR.ARCHIVO NO ENCONTRADO");
        } catch (IOException ex) {
            System.out.println("ERROR.IOException");
        }

        //Muestro los alumnos en formato tabla
        mostrarAlumnos();
        //Ordeno los alumnos por curso y los vuelvo a mostrar
        animacion("ORDENANDO POR CURSO");
        ordenarPorCurso();
        mostrarAlumnos();
        //Ordeno los alumnos por nombre y los vuelvo a mostrar
        animacion("ORDENANDO POR NOMBRE");
        ordenarPorNombre();
        mostrarAlumnos();
    }

    //Método que se encarga de leer el fichero, crear un objeto con cada linea del fichero y almacenarla en el array
    public static void leerFichero() throws FileNotFoundException, IOException {
        //Abro flujo
        BufferedReader br = new BufferedReader(new FileReader(fichero));
        //Leo
        Alumno alumno;
        String nombre = br.readLine();
        while (nombre != null) {
            //Creo el objeto alumno con el parameto nombre ya que los otros dos atributos se rellenan solos
            alumno = new Alumno(nombre);
            //Reutilizo el contador del idAlumno para introducirlo en el Array
            arrayAlumnos[alumno.getIdAlumno() - 1] = alumno;
            //Leo siguiente linea
            nombre = br.readLine();
        }
        //Cierro flujo
        br.close();
    }

    public static void mostrarAlumnos() {
        System.out.println("===============================");
        System.out.println("|  ID  |   NOMBRE   |  CURSO  |");
        System.out.println("===============================");
        for (Alumno alumno : arrayAlumnos) {
            System.out.println(cadenaIdAlumnoModificada(alumno) + "   " + alumno.getNombre() + "   |    " + alumno.getCurso() + "   |");
            System.out.println("-------------------------------");
        }
    }

    //Método que devuelve el fragmento que muestra el id alumno modificando los espacios dependiendo del numero de cifras que tiene el número para que salga cuadrado
    public static String cadenaIdAlumnoModificada(Alumno alumno) {
        String idAlumno;
        if (alumno.getIdAlumno() < 10) {
            idAlumno = "|   " + alumno.getIdAlumno() + "   |";
        } else {
            if (alumno.getIdAlumno() < 100) {
                idAlumno = "|  " + alumno.getIdAlumno() + "   |";
            } else {
                idAlumno = "|  " + alumno.getIdAlumno() + "  |";
            }
        }
        return idAlumno;
    }

    public static void animacion(String texto) throws InterruptedException {
        System.out.println("");//SALTO DE LÍNEA
        Thread.sleep(1000);
        for (int i = 0; i < texto.length(); i++) {
            System.out.print(texto.charAt(i));
            Thread.sleep(250);
        }
        //Puntos de espera
        for (int i = 0; i < 4; i++) {
            System.out.print(" . ");
            Thread.sleep(800);
        }
        System.out.println("");//SALTO DE LÍNEA
    }

    public static void ordenarPorCurso() {
        Alumno aux;
        for (int i = 0; i < arrayAlumnos.length - 1; i++) {
            for (int j = 0; j < arrayAlumnos.length - i - 1; j++) {
                if (arrayAlumnos[j].getCurso() > arrayAlumnos[j + 1].getCurso()) {
                    aux = arrayAlumnos[j];
                    arrayAlumnos[j] = arrayAlumnos[j + 1];
                    arrayAlumnos[j + 1] = aux;
                }
            }
        }
    }

    public static void ordenarPorNombre() {
        Alumno aux;
        char primeraInicial;
        char segundaInicial;
        for (int i = 0; i < arrayAlumnos.length - 1; i++) {
            for (int j = 0; j < arrayAlumnos.length - i - 1; j++) {
                primeraInicial = inicial(arrayAlumnos[j].getNombre());
                segundaInicial = inicial(arrayAlumnos[j + 1].getNombre());
                if (primeraInicial > segundaInicial) {
                    aux = arrayAlumnos[j];
                    arrayAlumnos[j] = arrayAlumnos[j + 1];
                    arrayAlumnos[j + 1] = aux;
                }
            }
        }
    }

    //Método que recibe por parámetro un String y devuelve el char de la primera letra ignorando espacios
    public static char inicial(String nombre) {
        boolean encontrado = false;
        int contador = 0;
        char inicial = 0;
        //Utilizo una busqueda con while porque no me interesa seguir recorriendo el string una vez que he encontrado la primera letra
        while (contador < nombre.length() && !encontrado) {
            if (nombre.charAt(contador) != ' ') {
                inicial = nombre.charAt(contador);
                encontrado = true;
            } else {
                contador++;
            }
        }
        return inicial;
    }
}
