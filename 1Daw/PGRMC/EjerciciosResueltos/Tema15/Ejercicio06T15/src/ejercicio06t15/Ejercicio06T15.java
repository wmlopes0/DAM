/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio06t15;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Quique Pineda
 */
public class Ejercicio06T15 {

    //Pide un string (el que recoba por parámetros) y lo retorna
    public static String pedirString(String sms){
        Scanner teclado = new Scanner(System.in);
        System.out.print(sms);
        String cad = teclado.nextLine();
        return cad;
    }
    
    //Crea un coche a partir de los datos introducidos por el usuario y lo retorna
    public static Coche pedirCoche(){
        Scanner teclado = new Scanner(System.in);
        String matricula = pedirString("Matrícula: ");
        String modelo = pedirString("Modelo: ");
        Coche C = new Coche(matricula, modelo);
        C.rellenarRuedas();
        return C;
    }
    
    //Inserta el coche recibido por parámetros en la lista recibida por parámetros
    public static void insCoche(Coche C, ArrayList<Coche> lCoches){
        lCoches.add(C);
        System.out.println("Coche insertado correctamente");
    }
    
    //Muestra todos los coches, junto con sus ruedas, de la lista
    public static void mostrarCoches(ArrayList<Coche> lCoches){
        if  (lCoches.isEmpty()){
            System.out.println("No existen coches en la lista");
        }
        else{
            for(int i = 0;i < lCoches.size();i++){
                lCoches.get(i).mostrarCoche();
            }
        }
    }
    
    //ESCRIBIR FICHERO
    //Escribe todos los coches de la lista en el fichero
    public static void rellenarFichero(ObjectOutputStream oos, ArrayList<Coche> lCoches) throws IOException {
        for(int i = 0;i < lCoches.size();i++){
            oos.writeObject(lCoches.get(i));
        }
        System.out.println("Volcado correcto");
    }
    
    //Esqueleto escribir en fichero
    public static void escribirFichero(ArrayList<Coche> lCoches){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        File fichero = new File("coches.obj");
        try{
            if  (!fichero.exists()){
                fos = new FileOutputStream("coches.obj");
                oos = new ObjectOutputStream(fos);
            }
            else{
                fos = new FileOutputStream("coches.obj", true);
                oos = new MiObjectOutputStream(fos);
            }
            rellenarFichero(oos, lCoches);
        }catch(IOException e){
            System.out.println("Error en escribir fichero");
        }
        finally{
            if  (oos != null){
                try{
                    oos.close();
                }
                catch(IOException e){
                    System.out.println("Error al cerrar fichero");
                }
            }
            if  (fos != null){
                try{
                    fos.close();
                }
                catch(IOException e){
                    System.out.println("Error al cerrar fichero");
                }
            }
        }
    }
    
    //LEER FICHERO
    //Lee todos los coches del fichero
    public static void leerCoches(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        Coche cocheAux;
        while(true){
            cocheAux = (Coche) ois.readObject();
            cocheAux.mostrarCoche();
        }
    }
    
    //Esqueleto leer en fichero
    public static void leerFichero(){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            fis = new FileInputStream("coches.obj");
            ois = new ObjectInputStream(fis);
            leerCoches(ois);
        }
        catch(FileNotFoundException e){
            System.out.println("No existe el fichero");
        }
        catch(ClassNotFoundException e){
            System.out.println("Error en el casting explícito");
        }
        catch(EOFException e){
            System.out.println(" --- ---");
        }
        catch(IOException e){
            System.out.println("Error en leer fichero");
        }
        finally{
            if  (ois != null){
                try{
                    ois.close();
                }
                catch(IOException e){
                    System.out.println("Error al cerrar fichero");
                }
            }
            if  (fis != null){
                try{
                    fis.close();
                }
                catch(IOException e){
                    System.out.println("Error al cerrar fichero");
                }
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ArrayList<Coche> lCoches = new ArrayList<>();
        Coche C;
        
        int opc;
        do{
            System.out.println("1. Añadir un coche a una lista.");
            System.out.println("2. Mostrar los coches junto con sus ruedas.");
            System.out.println("3. Volcar coches a fichero.");
            System.out.println("4. Mostrar los coches del fichero.");
            System.out.println("5. Salir.");
            System.out.print("   Opción: ");
            opc = teclado.nextInt();
            switch(opc){
                case 1:
                    C = pedirCoche();
                    insCoche(C, lCoches);
                    break;
                case 2:
                    mostrarCoches(lCoches);
                    break;
                case 3:
                    escribirFichero(lCoches);
                    break;
                case 4:
                    leerFichero();
                    break;
                case 5:
                    System.out.println("Hasta la próxima");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        }while(opc != 5);
    }
    
}
