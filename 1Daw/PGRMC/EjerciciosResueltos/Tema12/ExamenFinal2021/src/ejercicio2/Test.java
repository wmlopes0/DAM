package ejercicio2;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Test {

    public static int mostrarMenu() {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        System.out.println("=====================================================");
        System.out.println("===============ULTRAMARINOS WALTER S.L===============");
        System.out.println("=====================================================");
        System.out.println("1.- Introducir productos.");
        System.out.println("2.- Mostrar productos.");
        System.out.println("3.- Eliminar producto.");
        System.out.println("4.- Salir del programa.");
        System.out.println("-----------------------------------------------------");
        System.out.println("Por favor, introduzca una opcion: ");
        try {
            opcion = entrada.nextInt();
        } catch (InputMismatchException e) {
            opcion = -1;
        }
        return opcion;
    }

    public static boolean seguir() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("¿Desea seguir?");
        return entrada.nextLine().equalsIgnoreCase("si");
    }

    public static String pedirCodProd() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce el codigo del producto: ");
        return entrada.nextLine();
    }

    //OPCION1
    public static void introducirProductos(Map<String, Producto> catalogo) {
        Producto producto;
        String codProducto;
        //Considero que siempre que se marca la opción ha de introducirse un producto minimo
        do {
            producto = new Producto();
            producto.rellenarInfoProducto();
            codProducto = producto.getNombre().substring(0, 3);

            catalogo.put(codProducto, producto);
        } while (seguir());
    }

    //OPCION2
    public static void mostrarProductos(Map<String, Producto> catalogo) {
        String codProd;
        Iterator<String> it = catalogo.keySet().iterator();
        while (it.hasNext()) {
            codProd = it.next();
            System.out.println("********************");
            System.out.println("*********" + codProd + "********");
            System.out.println("********************");
            catalogo.get(codProd).mostrarInfoProducto();
        }
    }

    //OPCION3
    public static void eliminarProducto(Map<String, Producto> catalogo) {
        LocalDate hoy = LocalDate.now();
        String codProd;
        codProd = pedirCodProd();

        if (catalogo.containsKey(codProd)) {
            catalogo.remove(codProd);
            System.out.println("Producto eliminado el " + hoy);
        } else {
            System.out.println("ERROR.EL PRODUCTO NO EXISTE.");
        }
    }

    public static void main(String[] args) {
        Map<String, Producto> catalogo = new HashMap<>();

        int opcion;
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    System.out.println("******INTRODUCIR PRODUCTOS******");
                    introducirProductos(catalogo);
                    break;
                case 2:
                    System.out.println("******MOSTRAR PRODUCTOS******");
                    mostrarProductos(catalogo);
                    break;
                case 3:
                    System.out.println("******ELIMINAR PRODUCTO******");
                    eliminarProducto(catalogo);
                    break;
                case 4:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("ERROR. INTRODUZCA UNA OPCIÓN VALIDA.");
            }

        } while (opcion != 4);
    }
}
