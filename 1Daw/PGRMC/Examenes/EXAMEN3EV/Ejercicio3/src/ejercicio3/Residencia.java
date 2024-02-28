package ejercicio3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author final23
 */
public class Residencia {

    private String localidad;
    private List<Usuario> usuarios;

    //CONSTRUCTORES
    public Residencia() {
        localidad = "";
        usuarios = new ArrayList<>();
    }

    public Residencia(String localidad) {
        this.localidad = localidad;
        usuarios = new ArrayList<>();
    }

    public Residencia(String localidad, List<Usuario> usuarios) {
        this.localidad = localidad;
        this.usuarios = usuarios;
    }

    //SETTER Y GETTER
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getLocalidad() {
        return localidad;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    //METODOS PROPIOS
    public void rellenarInfo() {
        System.out.println("******** R E S I D E N C I A ********");
        localidad = pedirCadena("Introduce la localidad: ");
        introducirUsuarios();
    }

    public void mostrarInfoResumida() {
        System.out.println("******** R E S I D E N C I A ********");
        System.out.println("Localidad: " + localidad);
        System.out.println("Numero de usuarios: " + usuarios.size());
    }

    public void mostrarInfoCompleta() {
        System.out.println("******** R E S I D E N C I A ********");
        System.out.println("Localidad: " + localidad);
        mostrarInfoUsuarios();
    }

    //Estos metodos son privados porque no tiene sentido llamarlos si no es desde el rellenar info
    private String pedirCadena(String mensaje) {
        Scanner entrada = new Scanner(System.in);
        System.out.println(mensaje);
        return entrada.nextLine();
    }

    private void introducirUsuarios() {
        Usuario usuario;
        do {
            //Creo el usuario
            usuario = new Usuario();
            //Relleno su info
            usuario.rellenarInfo();
            //Añado el usuario al atributo
            usuarios.add(usuario);
        } while (seguir());
    }

    //Este metodo es privado porque no tiene sentido llamarlo desde fuera del metodo mostrarInfoTotal segun el enunciado.
    private void mostrarInfoUsuarios() {
        for (Usuario usuario : usuarios) {
            usuario.mostrarInfo();
        }
    }

    //Este metodo es privado porque no tiene sentido llamarlo si no es desde el introducir usuarios
    private boolean seguir() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("¿Desea seguir?");
        return entrada.nextLine().equalsIgnoreCase("si");
    }
}
