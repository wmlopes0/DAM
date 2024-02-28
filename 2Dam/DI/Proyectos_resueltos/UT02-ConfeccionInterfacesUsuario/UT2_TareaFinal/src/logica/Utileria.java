package logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Walter
 */
public class Utileria {

    //Método que recibe un String por parámetro, lo codifica y lo devuelve codificado
    public static String encodeToMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    //Método que carga los datos de los usuarios registrados para el login
    public static Map<String, String> cargarDatosLogin() {
        Map<String, String> lUsuarios = new LinkedHashMap<>();
        try {
            //Abro flujos
            BufferedReader br = new BufferedReader(new FileReader(logica.Constantes.FICHERO_USERS));
            String linea = br.readLine();
            String[] usuario;
            //Leo mientras haya usuarios
            while (linea != null) {
                usuario = linea.split("-");
                if (usuario.length != 1) {
                    lUsuarios.put(usuario[0], usuario[1]);
                    linea = br.readLine();
                } else {
                    linea = br.readLine();
                }
            }
            //Cierro flujos
            br.close();
        } catch (IOException ex) {
            System.out.println("ERROR: NO SE HA PODIDO CARGAR LOS DATOS DEL FICHERO.");
        }
        //Retorno mapa
        return lUsuarios;
    }

    //Método que carga los datos de los clientes dados de alta 
    public static List<Cliente> cargarDatosClientes() {
        List<Cliente> lClientes = new ArrayList<>();
        try {
            //Abro flujos
            BufferedReader br = new BufferedReader(new FileReader(logica.Constantes.FICHERO_CLIENTES));
            String linea = br.readLine();
            String[] cliente;
            //Leo mientras haya usuarios
            while (linea != null) {
                //Controlo que la linea no esté vacia, si es asi lo ignoramos
                cliente = linea.split(";");
                if (cliente.length != 1) {
                    lClientes.add(new Cliente(Integer.parseInt(cliente[0]), cliente[1], LocalDate.of(Integer.parseInt(cliente[2]), Integer.parseInt(cliente[3]), Integer.parseInt(cliente[4])), cliente[5], Integer.parseInt(cliente[6]), cliente[7]));
                    linea = br.readLine();
                } else {
                    linea = br.readLine();
                }
            }
            //Cierro flujos
            br.close();
        } catch (IOException ex) {
            System.out.println("ERROR: NO SE HA PODIDO CARGAR LOS DATOS DEL FICHERO.");
        }
        return lClientes;
    }

    //Método que vuelca los datos de los clientes dados de alta
    public static void volcarDatosClientes(List<Cliente> clientes) {
        String cliente;
        //ESCRITURA EN FICHERO
        try {
            //Abro flujos
            PrintWriter pw = new PrintWriter(new FileWriter(logica.Constantes.FICHERO_CLIENTES));//Conservo datos
            for (Cliente c : clientes) {
                cliente = clienteToString(c);
                pw.println(cliente);//Escribo
            }
            //Cierro flujos
            pw.close();
        } catch (IOException ex) {
            System.out.println("ERROR: NO SE HA PODIDO CARGAR LOS DATOS DEL FICHERO.");
        }
    }

    //Método que recibe un cliente y lo escribe en el fichero
    public static void escribirCliente(Cliente c) {
        //CREO STRING PARA ESCRIBIRLO
        String cliente = clienteToString(c);
        //ESCRITURA EN FICHERO
        try {
            //Abro flujos
            PrintWriter pw = new PrintWriter(new FileWriter(logica.Constantes.FICHERO_CLIENTES, true));//Conservo datos
            pw.println(cliente);//Escribo
            //Cierro flujos
            pw.close();
        } catch (IOException ex) {
            System.out.println("ERROR: NO SE HA PODIDO CARGAR LOS DATOS DEL FICHERO.");
        }
    }

    //Método que recibe un Cliente y retorna el String para escribirlo en el fichero
    public static String clienteToString(Cliente c) {
        //CREO STRING PARA ESCRIBIRLO
        String cliente = c.getNumCliente() + ";";//NumCliente
        cliente += c.getDni() + ";";//Dni
        cliente += c.getFechaAlta().getYear() + ";";//AñoFechaAlta
        cliente += c.getFechaAlta().getMonthValue() + ";";//MesFechaAlta
        cliente += c.getFechaAlta().getDayOfMonth() + ";";//DiaFechaAlta
        cliente += c.getDireccion() + ";";//Dirección
        cliente += c.getTelefono() + ";";//Telefono
        if (c.getFoto().equals("")) {
            cliente += "/interfazGrafica/IMG/user.png";//Si la foto está vacia se asigna una por defecto
        } else {
            if (!c.getFoto().startsWith("/interfazGrafica/IMG/")) {
                cliente += "/interfazGrafica/IMG/" + c.getFoto();//Foto
            } else {
                cliente += c.getFoto();
            }
        }
        return cliente;
    }

    //Método que me retorna true si existe un cliente con ese numCliente
    public static boolean existeCliente(int numCliente) {
        boolean existe = false;
        int contador = 0;

        //Recupero la lista de clientes del fichero
        List<Cliente> lClientes = cargarDatosClientes();

        //Busqueda lineal con un while para que sea mas eficiente
        while (contador < lClientes.size() && !existe) {
            if (lClientes.get(contador).getNumCliente() == numCliente) {
                existe = true;
            }
            contador++;
        }
        return existe;
    }

    //Método que me retorna true si existe un cliente con ese dni
    public static boolean existeCliente(String dni) {
        boolean existe = false;
        int contador = 0;

        //Recupero la lista de clientes del fichero
        List<Cliente> lClientes = cargarDatosClientes();

        //Busqueda lineal con un while para que sea mas eficiente
        while (contador < lClientes.size() && !existe) {
            if (lClientes.get(contador).getDni().equalsIgnoreCase(dni)) {
                existe = true;
            }
            contador++;
        }
        return existe;
    }

}
