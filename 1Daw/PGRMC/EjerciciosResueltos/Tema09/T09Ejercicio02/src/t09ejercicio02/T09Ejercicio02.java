package t09ejercicio02;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class T09Ejercicio02 {

    public static int mostrarMenu() throws InputMismatchException {
        Scanner entrada = new Scanner(System.in);
        System.out.println("===============MENU===============");
        System.out.println("1.- Te calcula tu edad exacta en años, meses y días.");
        System.out.println("2.- Te dice en que día de la semana naciste.");
        System.out.println("3.- Te dice que estación del año era.");
        System.out.println("4.- Te dice cuantos días llevas vividos.");
        System.out.println("5.- Te dice que año podrás/pudiste conducir.");
        System.out.println("6.- Salir.");
        System.out.println("----------------------------------");
        System.out.println("Introduzca una opcion: ");
        return entrada.nextInt();
    }

    public static LocalDate pedirFechaNac() {
        Scanner entrada = new Scanner(System.in);
        int ano, mes, dia;
        try {
            ano = pedirNum("el año", entrada);
            mes = pedirNum("el mes", entrada);
            dia = pedirNum("el dia", entrada);
        } catch (DateTimeException e) {
            System.out.println("ERROR, INTRODUZCA DATOS VALIDOS");
            ano = pedirNum("el año", entrada);
            mes = pedirNum("el mes", entrada);
            dia = pedirNum("el dia", entrada);
        }
        LocalDate fechaNac = LocalDate.of(ano, mes, dia);
        return fechaNac;
    }

    public static int pedirNum(String texto, Scanner entrada) {
        System.out.println("Introduzca " + texto + ": ");
        return entrada.nextInt();
    }

    public static String calcularEdad(LocalDate fechaNac) {
        LocalDate hoy = LocalDate.now();
        Period edad = Period.between(fechaNac, hoy);
        return edad.getYears() + " años " + edad.getMonths() + " meses " + edad.getDays() + " dias.";
    }

    public static String diaSemana(LocalDate fechaNac) {
        DayOfWeek diaSemana = fechaNac.getDayOfWeek();
        return diaSemana.name();
    }

    public static String estacionDelAno(LocalDate fechaNac) {
        int mes = fechaNac.getMonthValue();
        // Determinar la estación del año
        String estacion;

        switch (mes) {
            case 12:
            case 1:
            case 2:
                estacion = "Invierno";
                break;
            case 3:
            case 4:
            case 5:
                estacion = "Primavera";
                break;
            case 6:
            case 7:
            case 8:
                estacion = "Verano";
                break;
            case 9:
            case 10:
            case 11:
                estacion = "Otoño";
                break;
            default:
                estacion = "Mes inválido";
                break;
        }
        return estacion;
    }

    public static int diasVividos(LocalDate fechaNac) {
        LocalDate hoy = LocalDate.now();
        return (int) ChronoUnit.DAYS.between(fechaNac, hoy);
    }

    public static int anoConducir(LocalDate fechaNac) {
        return fechaNac.getYear() + 18;
    }

    public static void main(String[] args) {
        int opcion;
        System.out.println("#POR FAVOR, INTRODUZCA SU EDAD");
        LocalDate fechaNac = pedirFechaNac();
        do {
            try {
                opcion = mostrarMenu();
            } catch (InputMismatchException e) {
                System.out.println("ERROR, INTRODUCA UN NUMERO");
                opcion = mostrarMenu();
            }
            switch (opcion) {
                case 1:
                    System.out.println("--------TU EDAD--------");
                    System.out.println("Tu edad es de : " + calcularEdad(fechaNac));
                    break;
                case 2:
                    System.out.println("--------¿QUE DIA DE LA SEMANA?--------");
                    System.out.println("Naciste un " + diaSemana(fechaNac));
                    break;
                case 3:
                    System.out.println("--------¿QUE ESTACION DEL AÑO?--------");
                    System.out.println("Has nacido en " + estacionDelAno(fechaNac));
                    break;
                case 4:
                    System.out.println("--------¿CUANTOS DIAS LLEVAS VIVIDOS?--------");
                    System.out.println("Has vivido un total de " + diasVividos(fechaNac) + " dias.");
                    break;
                case 5:
                    System.out.println("--------¿CUANDO HAS PODIDO/PODRAS CONDUCIR?--------");
                    System.out.println("Podras o has podido conducir en el año " + anoConducir(fechaNac));
                    break;
                case 6:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("ERROR.INTRODUZCA UNA OPCION VALIDA.");
            }
        } while (opcion != 6);

    }

}
