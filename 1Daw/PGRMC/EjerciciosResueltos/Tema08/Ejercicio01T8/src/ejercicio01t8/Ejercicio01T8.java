/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio01t8;

/**
 *
 * @author admin
 */
public class Ejercicio01T8 {

    /*  EQUIVALENCIA
            //48 equivale a 0
            //49 equivale a 1
            //50 equivale a 2
            //51 equivale a 3
            //52 equivale a 4
            //53 equivale a 5
            //54 equivale a 6
            //55 equivale a 7
            //56 equivale a 8
            //57 equivale a 9
    */
    
    //Convierte una cadena de caracteres a entero
    public static int convertirCharToInt(char cadena[]){
        int i, aux, x;
        int entero = 0;
        for(i = 0;i < cadena.length;i++){
            x = (int)cadena[i];
            aux = x - 48;  //Mirar equivalencia
            entero = entero * 10;
            entero = entero + aux;
        }
        return entero;
    }
    
    //Pedimos al usuario que introduzca una cadena y la almacenamos en un vector de char
    public static void pedirCadena(char[] dni){
        int caracter, escrito = 0;
        System.out.print("Introduzca DNI. Pulse 'Enter' para finalizar: ");
        //Recogemos valor del DNI en vector dni
        try{
            //Mientras el caracter sea distinto de 'Enter' y no me haya pasado de la longitud del vector cadena
            while(((caracter = System.in.read())!='\n') && (escrito<dni.length)){
                dni[escrito] = (char)caracter; //Almacenamos el caracter en la celda del vector cadena
                escrito++;
            }
        } catch(Exception e){
            System.out.println("Longitud del texto escrito: "+e.getMessage());
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        char[] dni = new char[8];
        char[] letrasDni = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        int i, caracter, dniInt, resto, escrito = 0;
        pedirCadena(dni);
        dniInt = convertirCharToInt(dni);
        resto = dniInt % 23;
        System.out.print(dniInt);
        System.out.print(letrasDni[resto]);
    }
    
}
