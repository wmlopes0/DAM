package t07ejercicio08;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Test {

//MENU DE ADMINISTRACION
    public static Producto[] menuAdministracion() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("# ACCESO AL MENU DE ADMINISTRACION:");

        //Pedir longitud
        System.out.println("¿Cuantos productos desea dar de alta?");
        int numProductos = entrada.nextInt();

        //Array productos
        Producto[] productos = new Producto[numProductos];

        //Rellenamos con datos del usuario el array
        for (int i = 0; i < productos.length; i++) {
            System.out.println("\n-- Producto " + (i + 1) + " --");
            productos[i] = new Producto();
            productos[i].setNombre(pedirNombre());
            productos[i].setPrecio(pedirPrecio());
            productos[i].setStock(pedirStock());
        }

        //Retornamos el array productos
        return productos;
    }

    public static String pedirNombre() {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Intruduzca un nombre para el producto: ");
        return entrada.nextLine();
    }

    public static float pedirPrecio() {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Intruduzca un precio para el producto: ");
        return entrada.nextFloat();
    }

    public static int pedirStock() {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Intruduzca un stock para el producto: ");
        return entrada.nextInt();
    }
//-------------------------------------------

//MENU DE VENTAS
    public static float menuVentas(Producto[] productos) {
        int opcion, unidades;
        float cesta = 0;
        boolean seguirComprando;
        System.out.println("# ACCESO AL MENU DE VENTAS:");
        do {
            opcion = mostrarMenuVentas(productos) - 1; //Resto uno porque el usuario introduce 1 para la posicion 0
            unidades = pedirUnidades();

            //SUMAMOS IMPORTE A LA CESTA
            cesta += productos[opcion].getPrecio() * unidades;

            //ACTUALIZAMOS PRODUCTO
            productos[opcion].setStock(productos[opcion].getStock() - unidades);

            seguirComprando = seguirComprando();
        } while (seguirComprando);
        return cesta;
    }

    public static int mostrarMenuVentas(Producto[] productos) {
        Scanner entrada = new Scanner(System.in);
        for (int i = 0; i < productos.length; i++) {
            System.out.println("Pulse " + (i + 1) + " para comprar " + productos[i].getNombre() + " cuyo precio es de " + productos[i].getPrecio() + " euros y el stock es de " + productos[i].getStock() + " unidades.");
        }
        return entrada.nextInt();
    }

    public static boolean seguirComprando() {
        Scanner entrada = new Scanner(System.in);
        System.out.print("¿Desea comprar otro producto?");
        return entrada.nextLine().equalsIgnoreCase("si");
    }

    public static int pedirUnidades() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("¿Cuantas unidades desea?");
        return entrada.nextInt();
    }

//-------------------------------------------
    public static void main(String[] args) {
        float cesta;
        System.out.println("===============BIENVENIDO A MI TIENDA DE DEPORTES===============\n ");
        //MENU DE ADMINISTRACION
        Producto[] productos = menuAdministracion();
        //MENU DE VENTAS
        cesta = menuVentas(productos);

        //MOSTRAMOS CESTA Y DESPEDIMOS
        System.out.println("==========CESTA==========");
        System.out.println("El total de su compra asciende a " + cesta + " euros.");
        System.out.println("Muchas gracias, vuelve pronto coño.");
    }
}
