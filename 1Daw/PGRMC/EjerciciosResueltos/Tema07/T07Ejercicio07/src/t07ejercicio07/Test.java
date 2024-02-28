package t07ejercicio07;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Test {

    public static int mostrarMenu() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("\n====================MENU====================");
        System.out.println("1. Rellenar de forma aleatoria las temperaturas.\n"
                + "2. Mostrar las temperaturas.\n"
                + "3. Visualizar la temperatura media del mes.\n"
                + "4. Día o días más calurosos del mes.\n"
                + "5. Salir del programa.");
        System.out.println("============================================");

        return entrada.nextInt();
    }

    public static void rellenarTemperaturas(Dia[] temperaturas, String[] diasSemana) {
        //Genero un array auxiliar con los nombres de los dias que empiece en un dia aleatorio
        String[] nombres = new String[temperaturas.length];
        int dia = (int) Math.round(Math.random() * 6);

        for (int i = 0; i < nombres.length; i++) {
            if (i == 0) {
                nombres[i] = diasSemana[dia];
            } else {
                if (dia < 6) {
                    dia++;
                    nombres[i] = diasSemana[dia];
                } else {
                    dia = 0;
                    nombres[i] = diasSemana[dia];
                }
            }
        }

        //Relleno el array temperaturas
        for (int i = 0; i < temperaturas.length; i++) {
            temperaturas[i] = new Dia();
            temperaturas[i].setNombre(nombres[i]);
            temperaturas[i].setTemperatura((int) Math.round(Math.random() * 50));
        }
    }

    public static void mostrarTemperaturas(Dia[] temperaturas) {
        for (int i = 0; i < temperaturas.length; i++) {
            System.out.println("- " + temperaturas[i].getNombre() + " dia " + (i + 1) + ": " + temperaturas[i].getTemperatura() + "º.");
        }
    }

    public static float mediaTemperaturas(Dia[] temperaturas) {
        float temperaturaMedia = 0;
        for (int i = 0; i < temperaturas.length; i++) {
            temperaturaMedia += temperaturas[i].getTemperatura();
        }
        return temperaturaMedia / temperaturas.length;
    }

    public static void diasMasCalurosos(Dia[] temperaturas) {
        //Primero busco la temperatura mas alta
        int menudoCalor = temperaturas[0].getTemperatura();
        for (int i = 0; i < temperaturas.length; i++) {
            if (menudoCalor < temperaturas[i].getTemperatura()) {
                menudoCalor = temperaturas[i].getTemperatura();
            }
        }
        
        //Muestro el dia o dias que tienen esa temperatura
        for (int i = 0; i < temperaturas.length; i++) {
            if (temperaturas[i].getTemperatura()== menudoCalor) {
                System.out.println("# El "+temperaturas[i].getNombre()+" dia "+(i+1)+" con "+temperaturas[i].getTemperatura()+"º.");
            }
        }
    }

    public static void main(String[] args) {
        //Creamos arrays necesarios
        Dia[] temperaturas = new Dia[30];
        String[] diasSemana = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};

        //Variables
        int opcion;

        //MENU
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    System.out.println("==========RELLENA LAS TEMPERATURAS==========");
                    rellenarTemperaturas(temperaturas, diasSemana);
                    System.out.println("TEMPERATURAS RELLENADAS AUTOMATICAMENTE");
                    break;
                case 2:
                    System.out.println("==========MOSTRANDO LAS TEMPERATURAS==========");
                    mostrarTemperaturas(temperaturas);
                    break;
                case 3:
                    System.out.println("============MEDIA DE TEMPERATURAS============");
                    System.out.print(mediaTemperaturas(temperaturas) + "º\n");
                    break;
                case 4:
                    System.out.println("=============DIAS MAS CALUROSOS==============");
                    diasMasCalurosos(temperaturas);
                    break;
                case 5:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("ERROR: Por favor, introduzca una opcion valida.");
            }
        } while (opcion != 5);
    }
}
