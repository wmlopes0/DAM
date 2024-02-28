package t12ejercicio26;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Test {

    final static File FICHERO = new File("catalogo.obj");
    private static float carrito = 0;

    public static int mostrarMenu() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("========MENU========");
        System.out.println("1.- Introduccion de bebidas.");
        System.out.println("2.- Comprar productos.");
        System.out.println("3.- Salir.");
        System.out.println("----------------------");
        System.out.println("Introduce una opcion: ");
        return entrada.nextInt();
    }

    public static boolean seguir() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("¿Desea seguir?");
        return entrada.nextLine().equalsIgnoreCase("si");
    }

    public static void introducirBebidas(ArrayList<Bebida> catalogo) {
        Bebida bebida;
        do {
            bebida = new Bebida();
            bebida.rellenar();
            catalogo.add(bebida);
        } while (seguir());
    }

    public static void leerFichero(ArrayList<Bebida> catalogo) throws IOException, ClassNotFoundException {
        Bebida auxiliar;

        //ABRO FLUJOS
        FileInputStream fis = new FileInputStream(FICHERO);
        ObjectInputStream ois = new ObjectInputStream(fis);

        try {
            //LEO NOTAS
            while (true) {
                auxiliar = (Bebida) ois.readObject();
                catalogo.add(auxiliar);
            }
        } catch (EOFException ex) {
            
        } finally {
            //CIERRO FLUJOS
            fis.close();
            ois.close();
        }
    }

    public static void escribirFichero(ArrayList<Bebida> catalogo) throws FileNotFoundException, IOException, ClassNotFoundException {
        if (!FICHERO.exists()) {
            //ABRO FLUJOS
            FileOutputStream fos = new FileOutputStream(FICHERO);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            //ESCRIBO NOTAS
            for (int i = 0; i < catalogo.size(); i++) {
                oos.writeObject(catalogo.get(i));
            }

            //CIERRO FLUJOS
            oos.close();
            fos.close();
        } else {
            leerFichero(catalogo);
            //ABRO FLUJOS
            FileOutputStream fos = new FileOutputStream(FICHERO);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            //ESCRIBO NOTAS
            for (int i = 0; i < catalogo.size(); i++) {
                oos.writeObject(catalogo.get(i));
            }

            //CIERRO FLUJOS
            oos.close();
            fos.close();
        }
    }

    public static void escribirFicheroDespuesDeCompra(ArrayList<Bebida> catalogo) throws FileNotFoundException, IOException, ClassNotFoundException {
        //ABRO FLUJOS
        FileOutputStream fos = new FileOutputStream(FICHERO);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        //ESCRIBO NOTAS
        for (int i = 0; i < catalogo.size(); i++) {
            oos.writeObject(catalogo.get(i));
        }

        //CIERRO FLUJOS
        oos.close();
        fos.close();
    }

    public static void mostrarCatalogo(ArrayList<Bebida> catalogo) {
        Bebida bebida;
        System.out.println("===CATALOGO===");
        for (int i = 0; i < catalogo.size(); i++) {
            bebida = catalogo.get(i);
            System.out.println((i + 1) + "- " + bebida.getNombre() + "---" + bebida.getPrecio() + "€" + "---" + bebida.getStock() + " unidades.");
        }
    }

    public static int pedirBebida() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce la bebida que quieres comprar: ");
        return entrada.nextInt() - 1;
    }

    public static int pedirCantidad() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce la cantidad que quieres comprar: ");
        return entrada.nextInt();
    }

    public static void comprarProducto() throws IOException, ClassNotFoundException {
        ArrayList<Bebida> catalogoCompra = new ArrayList<>();
        int opcion, cantidad;
        Bebida auxiliar;

        System.out.println("========BIENVENIDO AL MENU DE COMPRA========");
        do {
            //Cargo datos del fichero en el array y lo muestro
            leerFichero(catalogoCompra);
            mostrarCatalogo(catalogoCompra);
            //Pido bebida y cantidad
            opcion = pedirBebida();
            cantidad = pedirCantidad();
            //Actualizo bebida y carrito
            auxiliar = catalogoCompra.get(opcion);
            auxiliar.setStock(auxiliar.getStock() - cantidad);
            carrito += cantidad * auxiliar.getPrecio();
        } while (seguir());
        System.out.println("EL IMPORTE DE LA COMPRA ASCIENDE A " + carrito);
        escribirFicheroDespuesDeCompra(catalogoCompra);
    }

    public static void main(String[] args) {
        ArrayList<Bebida> catalogo = new ArrayList<>();
        int opcion;

        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    introducirBebidas(catalogo);
                    try {
                        escribirFichero(catalogo);
                    } catch (FileNotFoundException ex) {
                        System.out.println("FICHERO NO ENCONTRADO");
                    } catch (ClassNotFoundException ex) {
                        System.out.println("CLASE NO ENCONTRADA");
                    } catch (IOException ex) {
                        System.out.println("ERROR ENTRADA/SALIDA");
                    }
                    break;
                case 2:
                     try {
                    comprarProducto();
                     } catch (FileNotFoundException ex) {
                        System.out.println("FICHERO NO ENCONTRADO");
                    } catch (ClassNotFoundException ex) {
                        System.out.println("CLASE NO ENCONTRADA");
                    } catch (IOException ex) {
                        System.out.println("ERROR ENTRADA/SALIDA");
                    }
                    break;
                case 3:
                    System.out.println("HASTA PRONTO MANOLO");
                    break;
                default:
                    System.out.println("ERROR.INTRODUCE UNA OPCION VALIDA.");
            }

        } while (opcion != 3);
    }
}
