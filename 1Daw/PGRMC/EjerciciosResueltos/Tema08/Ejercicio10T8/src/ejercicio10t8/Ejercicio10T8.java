/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio10t8;

import java.util.Scanner;

/**
 *
 * @author admin
 */
public class Ejercicio10T8 {

    public static String pedirDiaSemana(){
        String dia;
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduzca día de la semana (Lunes, ..., Domingo): ");
        dia = teclado.nextLine();
        return dia;
    }
    
    /* Retorna la posición en la que se encuentra el día de la semana dentro del vector */
    public static int comprobarDiaSemana(String dia){
        String[] diaSemana = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        int i = 0;
        boolean enc = false;
        while((i < diaSemana.length)&&(enc == false)){
            if  (dia.equalsIgnoreCase(diaSemana[i])){
                enc = true;
            }
            else{
                i++;
            }
        }
        if  (enc){
            return i;
        }
        else{
            return -1;
        }
        
    }
    
    public static void mostrarPosicionDia(int posicion){
        switch(posicion){
            case 0:
                System.out.println("Es el primer día de la semana");
                break;
            case 1:
                System.out.println("Es el segundo día de la semana");
                break;
            case 2:
                System.out.println("Es el tercer día de la semana");
                break;
            case 3:
                System.out.println("Es el cuarto día de la semana");
                break;
            case 4:
                System.out.println("Es el quinto día de la semana");
                break;
            case 5:
                System.out.println("Es el sexto día de la semana");
                break;
            case 6:
                System.out.println("Es el séptimo día de la semana");
                break;
            default:
                System.out.println("Dia inexistente");
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String diaSemana;
        int posicion;
        diaSemana = pedirDiaSemana();
        posicion = comprobarDiaSemana(diaSemana);
        mostrarPosicionDia(posicion);
    }
    
}
