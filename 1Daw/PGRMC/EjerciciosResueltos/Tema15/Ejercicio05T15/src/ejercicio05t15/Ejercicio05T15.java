/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio05t15;

import com.sun.corba.se.impl.io.IIOPOutputStream;
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
public class Ejercicio05T15 {

    public static String pedirString(String sms){
        Scanner teclado = new Scanner(System.in);
        String cadena;
        System.out.println(sms);
        cadena = teclado.nextLine();
        return cadena;
    }
    
    public static int pedirInt(String sms){
        Scanner teclado = new Scanner(System.in);
        int num;
        System.out.println(sms);
        num = teclado.nextInt();
        return num;
    }
    
    public static Fecha pedirFecha(){
        Scanner teclado = new Scanner(System.in);
        String resp;
        int dia, mes, anio;
        Fecha f;
        System.out.println("¿Desea introducir una fecha de matriculación? (SI/NO)");
        resp = teclado.nextLine();
        if  (resp.equalsIgnoreCase("SI")){
            dia = pedirInt("Dia: ");
            mes = pedirInt("Mes: ");
            anio = pedirInt("Año: ");
            f = new Fecha(dia, mes, anio);
            return f;
        }
        else{
            return null;
        }
    }
    
    public static Alumno pedirAlumno(){
        Alumno nAlumno;
        String dni, nombre;
        Fecha fMatricula;
        
        dni = pedirString("DNI: ");
        nombre = pedirString("Nombre: ");
        fMatricula = pedirFecha();
        
        nAlumno = new Alumno(dni, nombre, fMatricula);
        
        return nAlumno;
    }
    
    public static void insertarAlumno(ArrayList<Alumno> lAlumnos, Alumno nAlumno){
        try{
            lAlumnos.add(nAlumno);
        }
        catch(NullPointerException e){
            System.out.println("Lista no creada");
        }
    }
    
    //Muestra todos los alumnos del arraylist
    public static void mostrarAlumnos(ArrayList<Alumno> lAlumnos){
        for(int i = 0;i < lAlumnos.size();i++){
            System.out.println(lAlumnos.get(i).toString());
        }
    }
    
    //Escribe cada uno de los alumnos en el fichero
    public static void volcarListaAFichero(ObjectOutputStream oos, ArrayList<Alumno> lAlumnos) throws IOException {
        for(int i = 0;i < lAlumnos.size();i++){
            oos.writeObject(lAlumnos.get(i));
        }
    }
    
    //Estructura del escribir ficheros serializados
    public static void escribirFicheroAlumnos(ArrayList<Alumno> lAlumnos){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        File fichero = new File("alumnos.obj");        
        try{
            if  (!fichero.exists()){
                fos = new FileOutputStream("alumnos.obj");
                oos = new ObjectOutputStream(fos);
            }
            else{
                fos = new FileOutputStream("alumnos.obj", true);
                oos = new MiObjectOutputStream(fos);
            }
            volcarListaAFichero(oos, lAlumnos);
        }
        catch(IOException e){
            System.out.println("Error de E/S");
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
    
    public static void mostrarAlumnosDeFichero(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        Alumno alumno;
        while(true){
            alumno = (Alumno) ois.readObject();
            System.out.println(alumno.toString());
        }
    }
    
    //Estructura del leer ficheros serializados
    public static void leerFicheroAlumnos(){
        FileInputStream fis = null;
        ObjectInputStream ois = null;      
        try{
            fis = new FileInputStream("alumnos.obj");
            ois = new ObjectInputStream(fis);
            mostrarAlumnosDeFichero(ois);
        }
        catch(ClassNotFoundException e){
            System.out.println("Error en casting explícito");
        }
        catch(EOFException e){
            //No hago nada
        }
        catch(IOException e){
            System.out.println("Error de E/S");
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
        //Declaración lista de personajes
        ArrayList<Alumno> lAlumnos = new ArrayList();
        
        Scanner teclado = new Scanner(System.in);
        Alumno A;
        int opc;
        do{
            System.out.println("1. Insertar alumno. ");
            System.out.println("2. Mostrar alumnos. ");
            System.out.println("3. Volcar alumnos a fichero. ");
            System.out.println("4. Mostrar alumnos de fichero. ");
            System.out.println("5. Salir. ");
            System.out.print("   Opción: ");
            opc = teclado.nextInt();
            switch(opc){
                case 1:
                    A = pedirAlumno();
                    insertarAlumno(lAlumnos, A);
                    break;
                case 2:
                    mostrarAlumnos(lAlumnos);
                    break;
                case 3:
                    escribirFicheroAlumnos(lAlumnos);
                    break;
                case 4:
                    leerFicheroAlumnos();
                    break;
            }
        }while(opc != 5);
    }
    
}
