package t09ejercicio01;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class T09Ejercicio01 {

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

    public static String obtenerSignoZodiacal(LocalDate fecha) {
        int mes = fecha.getMonthValue();
        int dia = fecha.getDayOfMonth();
        String signo = "";

        if ((mes == 3 && dia >= 21) || (mes == 4 && dia <= 19)) {
            signo = "Aries";
        } else if ((mes == 4 && dia >= 20) || (mes == 5 && dia <= 20)) {
            signo = "Tauro";
        } else if ((mes == 5 && dia >= 21) || (mes == 6 && dia <= 20)) {
            signo = "Géminis";
        } else if ((mes == 6 && dia >= 21) || (mes == 7 && dia <= 22)) {
            signo = "Cáncer";
        } else if ((mes == 7 && dia >= 23) || (mes == 8 && dia <= 22)) {
            signo = "Leo";
        } else if ((mes == 8 && dia >= 23) || (mes == 9 && dia <= 22)) {
            signo = "Virgo";
        } else if ((mes == 9 && dia >= 23) || (mes == 10 && dia <= 22)) {
            signo = "Libra";
        } else if ((mes == 10 && dia >= 23) || (mes == 11 && dia <= 21)) {
            signo = "Escorpio";
        } else if ((mes == 11 && dia >= 22) || (mes == 12 && dia <= 21)) {
            signo = "Sagitario";
        } else if ((mes == 12 && dia >= 22) || (mes == 1 && dia <= 19)) {
            signo = "Capricornio";
        } else if ((mes == 1 && dia >= 20) || (mes == 2 && dia <= 18)) {
            signo = "Acuario";
        } else if ((mes == 2 && dia >= 19) || (mes == 3 && dia <= 20)) {
            signo = "Piscis";
        }

        return signo;
    }

    public static void main(String[] args) {
        LocalDate fechaNacimiento;
        System.out.println("****************************************************************");
        System.out.println("***************************BIENVENIDO***************************");
        System.out.println("*************¿QUIERES AVERIGUAR TU SIGNO ZODIACAL?**************");
        System.out.println("****************************************************************");
        System.out.println("#INTRODUCE TU FECHA DE NACIMIENTO");
        fechaNacimiento = pedirFechaNac();
        System.out.println("-----------------------------------------------------------");
        System.out.println("Tu signo del zodiaco es " + obtenerSignoZodiacal(fechaNacimiento));
    }

}
