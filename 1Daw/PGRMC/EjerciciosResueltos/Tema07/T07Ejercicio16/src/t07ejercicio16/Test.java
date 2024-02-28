package t07ejercicio16;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Test {

    //INICIO----------------------------------------------------
    public static int mostrarMenu() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("\n====================MENU====================");
        System.out.println("1. Rellenar las peliculas junto con los socios que han acudido a verla.\n"
                + "2. Mostrar peliculas y los socios que la han visto.\n"
                + "3. Mostrar la pelicula mas rentable.\n"
                + "4. Mostrar la pelicula menos rentable.\n"
                + "5. Buscar informacion de una pelicula por su nombre.\n"
                + "6. Contar el numero de socios que han abonado una cantidad mayor a la pedida por pantalla.\n"
                + "7. Mostrar clientes que empiecen por una letra introducida.\n"
                + "8. Mostrar peliculas que empiecen por un texto indicado.\n"
                + "9. Modificar las 'a' por 'e' para la ultima pelicula.\n"
                + "10. Convertir los titulos de todas la peliculas a mayusculas.\n"
                + "11. Salir del programa.");
        System.out.println("============================================");
        System.out.print("Por favor, introduzca una opcion: ");
        System.out.println("");//Salto de linea
        return entrada.nextInt();
    }
    
    public static int pedirLongitudCartelera() {
        Scanner entrada = new Scanner(System.in);
        System.out.print("#POR FAVOR, INTRODUZCA CUANTAS PELICULAS DESEA TENER EN LA CARTELERA: ");
        return entrada.nextInt();
    }
    //-----------------------------------------------------------

    //CLASE PELICULA
    public static String pedirTitulo() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Por favor, introduzca el titulo: ");
        return entrada.nextLine();
    }
    
    public static float pedirCosteLicencia() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Por favor, introduzca el coste de la licencia: ");
        return entrada.nextFloat();
    }
    
    public static float pedirCantidad() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Por favor, introduzca la cantidad: ");
        return entrada.nextFloat();
    }
    
    public static String pedirLetraTexto() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Por favor, introduzca una letra o un texto: ");
        return entrada.nextLine();
    }
    //-----------------------------------------------------------

    //CLASE SOCIO
    public static String pedirNombre() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Por favor, introduzca el nombre del socio: ");
        return entrada.nextLine();
    }
    
    public static float pedirPrecioAbonado() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Por favor, introduzca el precio abonado: ");
        return entrada.nextFloat();
    }
    //-----------------------------------------------------------

    //OPCIONES DEL MENU
    public static void rellenarPeliculas(Pelicula[] peliculas) {
        for (int i = 0; i < peliculas.length; i++) {
            System.out.println("==========PELICULA " + (i + 1) + "==========");
            peliculas[i] = new Pelicula();
            peliculas[i].setTitulo(pedirTitulo());
            peliculas[i].setCosteLicencia(pedirCosteLicencia());
            
            Socio[] socios = new Socio[peliculas[i].getSocios().length];//Creo array de socios con la longitud del atributo.

            for (int j = 0; j < socios.length; j++) {
                System.out.println("----------SOCIO " + (j + 1) + "----------");
                socios[j] = new Socio();
                socios[j].setNombre(pedirNombre());
                socios[j].setPrecioAbonado(pedirPrecioAbonado());
            }

            //Asigno el array creado al atributo de Pelicula
            peliculas[i].setSocios(socios);
        }
    }
    
    public static void mostrarPeliculas(Pelicula[] peliculas) {
        for (int i = 0; i < peliculas.length; i++) {
            System.out.println("==========PELICULA " + (i + 1) + "==========");
            peliculas[i].mostrarPelicula();
        }
    }
    
    public static void peliculaMasRentable(Pelicula[] peliculas) {
        int posicionPelicula = 0;
        for (int i = 0; i < peliculas.length; i++) {
            if (peliculas[posicionPelicula].beneficioNeto() < peliculas[i].beneficioNeto()) {
                posicionPelicula = i;
            }
        }
        System.out.println("La pelicula mas rentable ha sido la numero " + (posicionPelicula + 1) + " con el titulo " + peliculas[posicionPelicula].getTitulo() + " y con un beneficio neto de " + peliculas[posicionPelicula].beneficioNeto() + " euros.");
    }
    
    public static void peliculaMenosRentable(Pelicula[] peliculas) {
        int posicionPelicula = 0;
        for (int i = 0; i < peliculas.length; i++) {
            if (peliculas[posicionPelicula].beneficioNeto() > peliculas[i].beneficioNeto()) {
                posicionPelicula = i;
            }
        }
        System.out.println("La pelicula menos rentable ha sido la numero " + (posicionPelicula + 1) + " con el titulo " + peliculas[posicionPelicula].getTitulo() + " y con un beneficio neto de " + peliculas[posicionPelicula].beneficioNeto() + " euros.");
    }
    
    public static void buscadorPorNombre(Pelicula[] peliculas) {
        String titulo = pedirTitulo();
        int i = 0;
        while (i < peliculas.length && !peliculas[i].getTitulo().equalsIgnoreCase(titulo)) {
            i++;
        }
        if (i < peliculas.length) {
            System.out.println("La pelicula '" + peliculas[i].getTitulo() + "' ha obtenido un beneficio neto de " + peliculas[i].beneficioNeto() + " euros y los socios que han asistido a verla son: ");
            peliculas[i].mostrarSociosPelicula();
        } else {
            System.out.println("La pelicula introducida no ha sido encontrada.");
        }
        
    }
    
    public static void sociosTontos(Pelicula[] peliculas) {
        int sociosTontos = 0;
        float cantidad = pedirCantidad();
        for (int i = 0; i < peliculas.length; i++) {
            sociosTontos += peliculas[i].sociosTontos(cantidad);
        }
        System.out.println("El numero de socios que han abonado una cantidad mayor a la introducida han sido " + sociosTontos);
    }
    
    public static void buscadorSociosLetra(Pelicula[] peliculas) {
        String letra = pedirLetraTexto();
        for (int i = 0; i < peliculas.length; i++) {
            peliculas[i].mostrarSociosLetra(letra);
        }
    }
    
    public static void buscadorPeliculas(Pelicula[] peliculas) {
        String texto = pedirLetraTexto();
        for (int i = 0; i < peliculas.length; i++) {
            if (peliculas[i].getTitulo().startsWith(texto)) {
                System.out.println("- " + peliculas[i].getTitulo());
            }
        }
    }
    
    public static void modificarLetras(Pelicula[] peliculas) {
        String titulo = peliculas[peliculas.length - 1].getTitulo();
        titulo = titulo.replace('a', 'e');
        peliculas[peliculas.length - 1].setTitulo(titulo);
    }
    
    public static void titulosMayusculas(Pelicula[] peliculas) {
        for (int i = 0; i < peliculas.length; i++) {
            peliculas[i].setTitulo(peliculas[i].getTitulo().toUpperCase());
        }
    }
    //-----------------------------------------------------------

    public static void main(String[] args) {
        System.out.println("***********************************************************************");
        System.out.println("*****************BIENVENIDO AL PROGRAMA INFORMATICO DE*****************");
        System.out.println("***************************NOS GUSTA EL CINE***************************");
        System.out.println("***********************************************************************");
        //Creamos arrays necesarios
        Pelicula[] peliculas = new Pelicula[pedirLongitudCartelera()];
        System.out.println("");//Salto de linea

        //Variables
        int opcion;

        //MENU
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    System.out.println("==========RELLENA PELICULAS==========");
                    rellenarPeliculas(peliculas);
                    break;
                case 2:
                    System.out.println("==========MOSTRANDO LAS PELICULAS==========");
                    mostrarPeliculas(peliculas);
                    break;
                case 3:
                    System.out.println("============MOSTRANDO LA PELICULA MAS RENTABLE============");
                    peliculaMasRentable(peliculas);
                    break;
                case 4:
                    System.out.println("============MOSTRANDO LA PELICULA MENOS RENTABLE============");
                    peliculaMenosRentable(peliculas);
                    break;
                case 5:
                    System.out.println("=============BUSCADOR POR NOMBRE==============");
                    buscadorPorNombre(peliculas);
                    break;
                case 6:
                    System.out.println("=============¿CUANTOS SOCIOS TONTOS HAY?==============");
                    sociosTontos(peliculas);
                    break;
                case 7:
                    System.out.println("=============¿CUANTOS SOCIOS EMPIEZAN POR...?==============");
                    buscadorSociosLetra(peliculas);
                    break;
                case 8:
                    System.out.println("=============¿CUANTAS PELICULAS EMPIEZAN POR...?==============");
                    buscadorPeliculas(peliculas);
                    break;
                case 9:
                    System.out.println("=============MODIFICANDO LAS 'A' POR LAS 'E'==============");
                    modificarLetras(peliculas);
                    System.out.println("Las letras se han modificado con exito en el titulo de la ultima pelicula.");
                    break;
                case 10:
                    System.out.println("=============CONVIRTIENDO LOS TITULOS A MAYUSCULAS==============");
                    titulosMayusculas(peliculas);
                    System.out.println("Los titulos se han convertido a mayusculas con exito.");
                    break;
                case 11:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("ERROR: Por favor, introduzca una opcion valida.");
            }
        } while (opcion != 11);
    }
}
