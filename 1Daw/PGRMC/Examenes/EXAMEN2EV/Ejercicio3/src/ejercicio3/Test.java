package ejercicio3;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 *
 * @author java2
 */
public class Test {

    public static int mostrarMenu() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("==================MENU==================");
        System.out.println("1.- Añadir atraccion."
                + "\n2.- ¿Cuantas parcelas hay libres?."
                + "\n3.- Mostrar atraccion con mayor numero de ingresos."
                + "\n4.- Buscar atraccion por nombre."
                + "\n5.- Salir del programa.");
        System.out.println("---------------------------------------");
        System.out.println("Por favor, introduzca una opcion: ");
        return entrada.nextInt();
    }

    public static void introducirAtraccion(Atraccion[] parcelas) {
        int numParcela;
        numParcela = pedirNumParcela(parcelas);

        if (numParcela != -1) { //Si el numParcela es -1 significa que la parcela no esta vacia, por lo que muestro el mensaje, sino relleno datos
            parcelas[numParcela] = new Atraccion();
            parcelas[numParcela].rellenarAtraccion();
        } else {
            System.out.println("¡LA PARCELA SELECCIONADA ESTA OCUPADA!");
        }

    }

    public static int pedirNumParcela(Atraccion[] parcelas) {
        Scanner entrada = new Scanner(System.in);

        //Declaro variables necesarias
        int numParcela = 0;
        boolean valido;//Variable para controlar la excepcion InputMismatchException

        System.out.println("Por favor, introduzca el numero de la parcela: ");
        do {
            valido = true;
            try {
                numParcela = entrada.nextInt() - 1;//Resto 1 a lo que meta el usuario porque los arrays empiezan por 0
            } catch (InputMismatchException e) {//Si salta la excepcion muestro mensaje de error,  y vuelvo a pedir
                System.out.println("Error. No puedes introducir una letra.");
                System.out.println("Por favor, introduzca el numero de la parcela: ");
                entrada.next();//Limpio el buffer
                valido = false;
            }
        } while (!valido); //Con el do while hago que pida el numParcela hasta que sea un numero

        //Si la parcela no esta vacia asigno a numParcela un numero negativo(-1), ya que no puede coincidir con ninguna posicion del array
        if (!parcelaVacia(numParcela, parcelas)) {
            numParcela = -1;
        }
        return numParcela;
    }

    public static boolean parcelaVacia(int num, Atraccion[] parcelas) {
        return parcelas[num] == null;
    }

    public static int parcelasLibres(Atraccion[] parcelas) {
        //Inicializo contador a 0
        int parcelasLibres = 0;
        //Recorro parcelas y compruebo que por cada parcela vacia (null), incremente el contador
        for (Atraccion parcela : parcelas) {
            if (parcela == null) {
                parcelasLibres++;
            }
        }
        return parcelasLibres;
    }

    public static void atraccionMasRentable(Atraccion[] parcelas) {
        //Variables donde se almacenaran los datos de la atraccion mas rentable
        float ingresos = 0;
        String nombre = "";

        //Recorro parcelas, si los ingresos de la parcela son mayores a los de la variable "ingresos", asigno los datos correspondientes
        for (Atraccion parcela : parcelas) {
            if (parcela != null) { //Controlo si esa parcela esta vacia, para si es el caso que se la salte
                if (parcela.ingresos() > ingresos) {
                    ingresos = parcela.ingresos();
                    nombre = parcela.getNombre();
                }
            }
        }
        //Muestro info
        System.out.println("La atraccion mas rentable es " + nombre + " con " + ingresos + " euros de ingresos.");
    }

    public static void buscarPorNombre(Atraccion[] parcelas, String nombre) {
        //Declaro variables necesarias
        int i = 0;
        boolean encontrado = false;

        //Uso un while porque no me interesa seguir buscando si lo he encontrado
        while (i < parcelas.length && !encontrado) {
            if (parcelas[i] != null) {//Controlo si esa parcela esta vacia, para si es el caso que se la salte e incremente el contador
                if (parcelas[i].getNombre().equalsIgnoreCase(nombre)) {
                    encontrado = true;//Si la encuentra, se sale del bucle, e i se queda con el valor de la posicion de la atraccion buscada
                }
            } else {
                i++;
            }
        }
        if (encontrado) {
            parcelas[i].mostrarAtraccion(); //Muestro info
        } else {
            System.out.println("¡ATRACCION NO ENCONTRADA!");
        }
    }

    public static String pedirNombre() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Por favor, introduzca el nombre: ");
        return entrada.nextLine();
    }

    public static void main(String[] args) {
        //Array de parcelas
        Atraccion[] parcelas = new Atraccion[6];

        int opcion;

        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    System.out.println("==========INTRODUCIR ATRACCION==========");
                    introducirAtraccion(parcelas);
                    break;
                case 2:
                    System.out.println("==========PARCELAS LIBRES==========");
                    System.out.println("El numero de parcelas libres es: " + parcelasLibres(parcelas));
                    break;
                case 3:
                    System.out.println("==========MOSTRANDO ATRACCION MAS RENTABLE==========");
                    atraccionMasRentable(parcelas);
                    break;
                case 4:
                    System.out.println("==========BUSCADOR POR NOMBRE==========");
                    buscarPorNombre(parcelas, pedirNombre());
                    break;
                case 5:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("ERROR.Por favor, introduzca una opcion valida.");
                    break;
            }
        } while (opcion != 5);
    }
}
