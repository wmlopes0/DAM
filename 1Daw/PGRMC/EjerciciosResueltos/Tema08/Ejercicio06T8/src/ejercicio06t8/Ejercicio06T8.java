/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio06t8;

/**
 *
 * @author admin
 */
public class Ejercicio06T8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String cadena1, cadena2;
        int edad = 34;
        cadena1 = "Empieza";
        cadena2 = "Zorro";
        if  (cadena1.compareTo(cadena2) < 0){
            System.out.println(cadena1+" mayor que "+cadena2);
        }
        else{
            if  (cadena1.compareTo(cadena2) > 0){
                System.out.println(cadena2+" mayor que "+cadena1);
            }
            else{
                System.out.println("Iguales");
            }
        }
        cadena1 = String.valueOf(edad);
        System.out.println(cadena1);
        System.out.println("Longitud: "+cadena1.length());
        cadena1 = cadena1.concat(" años");
        System.out.println(cadena1);
        System.out.println("Longitud: "+cadena1.length());
        for(int i = 0;i < cadena1.length();i++){
            if  (cadena1.charAt(i) == 'a'){
                System.out.println("Wee");
            }
        }
        
        cadena1 = "Wellcome to Java Class";
        System.out.println("Caracteres entre posiciones 12 y 15: "+cadena1.substring(12, 16));
        
        System.out.println("a aparece en la posición... "+cadena1.indexOf("a"));
        cadena1 = cadena1.replace('e', 'a');
        System.out.println(cadena1);
        
        cadena1 = "Wellcome#to#Java#Class";
        String[] vCadenas = cadena1.split("#");
        for(int i = 0;i < vCadenas.length;i++){
            System.out.println(vCadenas[i]);
        }
    }
    
}
