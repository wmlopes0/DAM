/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio20t8;

import java.util.Scanner;

/**
 *
 * @author admin
 */
public class Ejercicio20T8 {
    
    /* Elige la palabra secreta de entre un grupo de palabras del vector vPalabrasSecretas */
    public static String elegirPalabraSecreta(){
        String[] vPalabrasSecretas = {"pino", "casa", "cama", "loza", "lata", "bici", "nuez", "vino", "moto", "frio"};
        int posSecreta = (int)(Math.random() * 10); //Aleatorio entre 0 y 9
        return vPalabrasSecretas[posSecreta];
    }

    /* Inicializa la palabra del usuario con _ */
    public static void inicializarPalabraUsuario(char[] palabraUsuario){
        for(int i = 0;i < palabraUsuario.length;i++){
            palabraUsuario[i] = '_';
        }
    }
    
    /* Retorna el caracter introducido. teclado.nextLine().charAt(0) lo que hace es obtener el caracter 0 del String introducido */
    public static char pedirLetra(){
        Scanner teclado = new Scanner(System.in);
        char c;
        System.out.print("\nIntroduzca una letra: ");
        c = teclado.nextLine().charAt(0);
        return c;
    }

    /* Muestra la cadena del usuario con la nueva letra.
       Retorna true si existe algún hueco en la palabra que está rellenando el usuario. False en caso de que esté completa */
    public static boolean mostrarAcierto(char c, char[] palabraUsuario, String palabraSecreta){
        boolean hueco = false;
        int i;
        System.out.print("¡Acertaste!. Resultado: ");
        for(i = 0;i < palabraSecreta.length();i++){
            if(c == palabraSecreta.charAt(i)){
                palabraUsuario[i] = c;
            }
            else{
                if  (palabraUsuario[i] == '_'){ 
                    hueco = true;
                }
            }
            System.out.print(palabraUsuario[i]+" ");
        }
        System.out.print("\n");
        return hueco;
    }
    
    /* Muestra la cadena del usuario con la nueva letra */
    public static void mostrarFallo(char[] palabraUsuario){
        int i;
        System.out.print("¡Has fallado!. Resultado: ");
        for(i = 0;i < palabraUsuario.length;i++){
            System.out.print(palabraUsuario[i]+" ");
        }
        System.out.print("\n");
    }

    /* Dibuja el juego en función de las vidas que nos quedan */
    public static void dibujo(int vidas){
        switch(vidas){
            case 5:
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("_ _ _|");
                break;
            case 4:
                System.out.println("      __");
                System.out.println("     |  |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("_ _ _|");
                break;
            case 3:
                System.out.println("      __");
                System.out.println("     |  |");
                System.out.println("     |  O");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("_ _ _|");
                break;
            case 2:
                System.out.println("      __");
                System.out.println("     |  |");
                System.out.println("     |  O");
                System.out.println("     |  |");
                System.out.println("     |");
                System.out.println("_ _ _|");
                break;    
            case 1:
                System.out.println("      __");
                System.out.println("     |  |");
                System.out.println("     |  O");
                System.out.println("     | -|-");
                System.out.println("     |");
                System.out.println("_ _ _|");
                break;    
            case 0:
                System.out.println("      __");
                System.out.println("     |  |");
                System.out.println("     |  O");
                System.out.println("     | -|-");
                System.out.println("     | / \\");
                System.out.println("_ _ _|");
                break;    
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        char[] palabraUsuario = new char[4];
        String palabraSecreta;
        boolean partidaGanada, hueco;
        int vidas;
        char c;
        System.out.println("- Bienvenido al Juego del Ahorcado - ");
        System.out.println("Intente adivinar la palabra oculta tras los guiones ");
        System.out.println("_ _ _ _");
        
        palabraSecreta = elegirPalabraSecreta();
        System.out.println("Prueba: "+palabraSecreta);
        inicializarPalabraUsuario(palabraUsuario);
        
        partidaGanada = false;
        vidas = 6;
        do{
            c = pedirLetra();
            if  (palabraSecreta.indexOf(c) != -1){  //Si existe la letra en la palabraSecreta
                hueco = mostrarAcierto(c, palabraUsuario, palabraSecreta);
                if  (hueco == false){ //Si no hay hueco
                    partidaGanada = true;
                }
            }
            else{  //Si no existe la letra en la palabraSecreta
                mostrarFallo(palabraUsuario);
                vidas--;
                System.out.println("Te quedan "+vidas+" intentos");
                dibujo(vidas);
            }
        }while((vidas > 0)&&(partidaGanada == false));  /* Mientras queden vidas y no haya acertado la palabra, seguimos jugando */
        if  (vidas == 0){
            System.out.println("¡HAS PERDIDO!");
            System.out.println("La palabra secreta era "+palabraSecreta);
        }
        else{
            System.out.println("¡HAS GANADO!");
        }
    }
}
