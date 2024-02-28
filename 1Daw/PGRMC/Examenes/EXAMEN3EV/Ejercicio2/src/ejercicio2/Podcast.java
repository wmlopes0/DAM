package ejercicio2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author final23
 */
public class Podcast {

    private String nombre;
    private List<Plataforma> plataformas;

    //CONSTRUCTORES
    public Podcast() {
        nombre = "";
        plataformas = new ArrayList<>();
    }

    public Podcast(String nombre) {
        this.nombre = nombre;
        plataformas = new ArrayList<>();
    }

    public Podcast(String nombre, List<Plataforma> plataformas) {
        this.nombre = nombre;
        this.plataformas = plataformas;
    }

    //SETTER Y GETTER
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPlataformas(List<Plataforma> plataformas) {
        this.plataformas = plataformas;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Plataforma> getPlataformas() {
        return plataformas;
    }

    //MÉTODOS PROPIOS
    public void rellenarInfo() {
        System.out.println("***** P O D C A S T *****");
        nombre = pedirCadena("Introduce el nombre: ");
        introducirPlataformas();
    }

    public void mostrarInfo() {
        System.out.println("***** P O D C A S T *****");
        System.out.println("Nombre: " + nombre);
        mostrarPlataformas();
    }

    public void mostrarPlataformas() {
        //Creo iterador
        Iterator<Plataforma> it = plataformas.iterator();
        //Mientras haya alguna plataforma la muestro
        while (it.hasNext()) {
            Plataforma plataforma = it.next();
            plataforma.mostrarInfo();
        }
    }

    //Este metodo hace una busqueda lineal, si encuentra la plataforma retorna su numero de reproducciones, sino devuelve -1.
    public int existePlataforma(String nombrePlataforma) {
        Plataforma plataforma;
        boolean encontrado = false;
        int contador = 0;
        int numReproducciones = -1;

        while (contador < plataformas.size() && !encontrado) {
            plataforma = plataformas.get(contador);
            if (plataforma.getNombre().equalsIgnoreCase(nombrePlataforma)) {
                encontrado = true;
                numReproducciones = plataforma.getNumReproducciones();
            } else {
                contador++;
            }
        }
        return numReproducciones;
    }

    /*Estos métodos son privados porque no tiene sentido llamarlos si no es desde
    el método rellenar info o el introducir plataformas*/
    private String pedirCadena(String mensaje) {
        Scanner entrada = new Scanner(System.in);
        System.out.println(mensaje);
        return entrada.nextLine();
    }

    private boolean seguir() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("¿Desea seguir?");
        return entrada.nextLine().equalsIgnoreCase("si");
    }

    //Este metodo te obliga a añadir minimo una plataforma puesto que así se indica en el enunciado.
    private void introducirPlataformas() {
        Plataforma plataforma;
        do {
            //Creo la plataforma
            plataforma = new Plataforma();
            //Relleno su info
            plataforma.rellenarInfo();
            //Añado la plataforma al atributo
            plataformas.add(plataforma);
        } while (seguir());
    }

}
