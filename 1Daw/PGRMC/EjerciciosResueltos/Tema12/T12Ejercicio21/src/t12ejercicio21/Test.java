package t12ejercicio21;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Walter
 */
public class Test {

    final static String[] NOMBRE_MATERIAS = {"Programacion", "Lenguaje de Marcas", "Bases de Datos",
        "Entornos de Desarrollo", "Sistemas Informaticos",
        "Formacion y Orientacion Laboral"};

    final static File FICHERO = new File("notas.obj");

    public static void rellenarNotas(List<Asignatura> asignaturasDam) {
        Asignatura asignatura;
        for (int i = 0; i < NOMBRE_MATERIAS.length; i++) {
            asignatura = new Asignatura(NOMBRE_MATERIAS[i]);
            asignatura.rellenarAsignatura();
            asignaturasDam.add(asignatura);
        }
    }

    public static void escribirFichero(List<Asignatura> asignaturasDam) throws FileNotFoundException, IOException {
        //ABRO FLUJOS
        FileOutputStream fos = new FileOutputStream(FICHERO);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        //ESCRIBO NOTAS
        for (int i = 0; i < asignaturasDam.size(); i++) {
            oos.writeObject(asignaturasDam.get(i));
        }

        //CIERRO FLUJOS
        oos.close();
        fos.close();
    }

    public static void leerFichero(List<Float> notas) throws FileNotFoundException, IOException, ClassNotFoundException {
        Asignatura auxiliar;

        //ABRO FLUJOS
        FileInputStream fis = new FileInputStream(FICHERO);
        ObjectInputStream ois = new ObjectInputStream(fis);

        try {
            //LEO NOTAS
            while (true) {
                auxiliar = (Asignatura) ois.readObject();
                notas.add(auxiliar.getNota());
            }
        } catch (EOFException ex) {
        } finally {
            //CIERRO FLUJOS
            fis.close();
            ois.close();
        }
    }

    public static void mostrarNotaMedia(List<Float> notas) {
        float notaMedia = 0;
        for (int i = 0; i < notas.size(); i++) {
            notaMedia += notas.get(i);
        }
        notaMedia /= NOMBRE_MATERIAS.length;
        System.out.println("LA NOTA MEDIA ES " + notaMedia);
    }

    public static void main(String[] args) {
        List<Asignatura> asignaturasDam = new ArrayList<>();
        List<Float> notas = new ArrayList<>();

        rellenarNotas(asignaturasDam);

        try {
            escribirFichero(asignaturasDam);
            leerFichero(notas);
        } catch (FileNotFoundException ex) {
            System.out.println("FICHERO NO ENCONTRADO");
        } catch (ClassNotFoundException ex) {
            System.out.println("CLASE NO ENCONTRADA");
        } catch (IOException ex) {
            System.out.println("ERROR ENTRADA/SALIDA");
        }

        mostrarNotaMedia(notas);

    }
}
