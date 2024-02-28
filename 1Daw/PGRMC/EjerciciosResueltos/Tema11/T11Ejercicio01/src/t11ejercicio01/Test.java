/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t11ejercicio01;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Test {

    final static int NDIAS = 31;

    public static int mostrarMenu() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("\n"
                + "*************************************************\n"
                + "******************** M E N U ********************\n"
                + "*************************************************\n"
                + "1. Rellenar de forma aleatoria las temperaturas.\n"
                + "2. Mostrar las temperaturas.\n"
                + "3. Visualizar la temperatura media del mes.\n"
                + "4. Dia o días más calurosos del mes.\n"
                + "5. Salir del programa.\n"
                + "--------------------------------------------------\n"
                + "Eliga una opcion: ");
        return entrada.nextInt();
    }

    public static void rellenarTemperaturas(Map<Integer, Dia> temperaturas) {
        String[] nombreDias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
        Dia dia;
        int diaSemana = random(0, 6);

        for (int i = 1; i <= NDIAS; i++) {
            if (diaSemana > 6) {//Controlo que el indice del nombreDias[] no se salga de rango
                diaSemana = 0;
            }
            dia = new Dia();
            dia.setNombreDia(nombreDias[diaSemana]);
            diaSemana++;//Incremento el indicie de nombreDias[] para que vayan en orden
            dia.setTemperatura(random(0, 50));
            temperaturas.put(i, dia);
        }
    }

    public static int random(int inicio, int fin) {
        return (int) Math.floor(Math.random() * fin + inicio);
    }

    //Muestra todos los dias
    public static void mostrarTemperaturas(Map<Integer, Dia> temperaturas) {
        Dia dia;
        for (Integer i : temperaturas.keySet()) {
            dia = temperaturas.get(i);
            System.out.println("======DIA " + i + "======");
            dia.mostrarDia();
        }
    }

    //Muestra los dias cuya temperatura sea igual a la del dia que paso por parametro
    public static void mostrarTemperaturas(Map<Integer, Dia> temperaturas, Dia queCalor) {
        Dia dia;
        for (Integer i : temperaturas.keySet()) {
            dia = temperaturas.get(i);
            if (dia.getTemperatura() == queCalor.getTemperatura()) {
                System.out.println("======DIA " + i + "======");
                dia.mostrarDia();
            }
        }
    }

    public static void temperaturaMedia(Map<Integer, Dia> temperaturas) {
        int temperaturaMedia = 0;
        Dia dia;
        for (Integer i : temperaturas.keySet()) {
            dia = temperaturas.get(i);
            temperaturaMedia += dia.getTemperatura();
        }
        temperaturaMedia /= NDIAS;

        System.out.println("La temperatura media del mes es de " + temperaturaMedia + "º.");
    }

    public static void diaMasCaluroso(Map<Integer, Dia> temperaturas) {
        Iterator<Integer> it = temperaturas.keySet().iterator();
        Dia queCalor = temperaturas.get(it.next());
        Dia dia;
        //Compruebo que dia es el mas caluroso
        while (it.hasNext()) {
            dia = temperaturas.get(it.next());
            if (queCalor.getTemperatura() < dia.getTemperatura()) {
                queCalor = dia;
            }
        }
        //Muestro
        mostrarTemperaturas(temperaturas, queCalor);
    }

    public static void main(String[] args) {
        Map<Integer, Dia> temperaturas = new HashMap<>();

        int opcion;
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    rellenarTemperaturas(temperaturas);
                    System.out.println("Temperaturas rellenadas automaticamente.");
                    break;
                case 2:
                    System.out.println("=======MOSTRANDO TEMPERATURAS=======");
                    mostrarTemperaturas(temperaturas);
                    break;
                case 3:
                    System.out.println("=======TEMPERATURA MEDIA=======");
                    temperaturaMedia(temperaturas);
                    break;
                case 4:
                    System.out.println("=======DIA O DIAS MAS CALUROSOS=======");
                    diaMasCaluroso(temperaturas);
                    break;
                case 5:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("ERROR. Introduzca una opcion valida.");
            }
        } while (opcion != 5);
    }

}
