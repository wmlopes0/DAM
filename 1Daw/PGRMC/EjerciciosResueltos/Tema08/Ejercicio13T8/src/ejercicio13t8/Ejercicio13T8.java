/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio13t8;

import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Ejercicio13T8 {

    public static String pedirUsuario(){
        Scanner teclado = new Scanner(System.in);
        String usuario;
        System.out.print("Usuario: ");
        usuario = teclado.nextLine();
        return usuario;
    }
    
    public static String pedirContrasenia(){
        Scanner teclado = new Scanner(System.in);
        String pass;
        System.out.print("Contraseña: ");
        pass = teclado.nextLine();
        return pass;
    }
    
    public static boolean comprobarSeisCaracteres(String pass){
        if  (pass.length() >= 6){
            return true;
        }
        else{
            return false;
        }
    }
    
    public static boolean comprobarDosDigitos(String pass){
        int i = 0, numDigitos = 0;
        int caracter;
        while((i < pass.length())&&(numDigitos < 2)){
            caracter = (int) pass.charAt(i);  //Convertimos el char a su correspondiente entero
            if  ((caracter >= 48)&&(caracter <= 59)){  //Si está enctre el "0" y el "9"
                numDigitos++;
            }
            i++;
        }
        if  (numDigitos == 2){
            return true;
        }
        else{
            return false;
        }
    }
    
    public static boolean comprobarUsuarioEnContrasenia(String usuario, String pass){
        if  (usuario.indexOf(pass) < 0){  //Si "Usuario" está contenido en "pass" retorna un < 0
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String usuario, pass;
        boolean incorrecta, okSeis, okDos, okUsuarioEnPass;
        usuario = pedirUsuario();
        do{
            incorrecta = false;  //Damos por hecho que es correcta
            pass = pedirContrasenia();
            okSeis = comprobarSeisCaracteres(pass);
            okDos = comprobarDosDigitos(pass);
            okUsuarioEnPass = comprobarUsuarioEnContrasenia(usuario, pass);
            if  ((!okSeis)||(!okDos)||(!okUsuarioEnPass)){
                incorrecta = true;
                System.out.println("Contraseña incorrecta. ");
            }
        }while(incorrecta);
    }
    
}
