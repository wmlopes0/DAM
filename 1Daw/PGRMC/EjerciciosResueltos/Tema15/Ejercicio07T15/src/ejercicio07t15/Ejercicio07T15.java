/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio07t15;

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
 * @author Usuario
 */
public class Ejercicio07T15 {

    public static String pedirCIF(){
        Scanner teclado = new Scanner(System.in);
        String cif;
        System.out.print("CIF: ");
        cif = teclado.nextLine();
        return cif;
    }
    
    public static String pedirNombreEmpresa(){
        Scanner teclado = new Scanner(System.in);
        String nom;
        System.out.print("Nombre Empresa: ");
        nom = teclado.nextLine();
        return nom;
    }
    
    //Rellena el vector de empresas con los datos introducidos por el usuario
    public static void rellenarVector(Empresa[] vEmpresas){
        String cif, nombre;
        for(int i = 0;i < vEmpresas.length;i++){
            cif = pedirCIF();
            nombre = pedirNombreEmpresa();
            vEmpresas[i] = new Empresa(cif, nombre);
            vEmpresas[i].rellenarEmpleados();
        }
    }
    
    public static void mostrarEmpresasDelVector(Empresa[] vEmpresas){
        for(int i = 0;i < vEmpresas.length;i++){
            vEmpresas[i].mostrarEmpresa();
        }
    }
    
    //ESCRIBIR FICHERO
    //Escribe todos los coches de la lista en el fichero
    public static void rellenarFichero(ObjectOutputStream oos, Empresa[] vEmpresas) throws IOException {
        for(int i = 0;i < vEmpresas.length;i++){
            oos.writeObject(vEmpresas[i]);
        }
        System.out.println("Volcado correcto");
    }
    
    //Esqueleto escribir en fichero
    public static void escribirFichero(Empresa[] vEmpresas){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        File fichero = new File("empresas.obj");
        try{
            if  (!fichero.exists()){
                fos = new FileOutputStream("empresas.obj");
                oos = new ObjectOutputStream(fos);
            }
            else{
                fos = new FileOutputStream("empresas.obj", true);
                oos = new MiObjectOutputStream(fos);
            }
            rellenarFichero(oos, vEmpresas);
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
    //Muestratodas las empresas del fichero
    public static void mostrarEmpresasDeFichero(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        Empresa empresaAux;
        while(true){
            empresaAux = (Empresa) ois.readObject();
            empresaAux.mostrarEmpresa();
        }
    }
    
    //Calcula la media de sueldos de las empresas del fichero
    public static void getEmpresasDeFichero(ObjectInputStream ois, ArrayList<Empresa> lEmpresasAux) throws IOException, ClassNotFoundException {
        Empresa empresaAux;
        int numEmpleados = 0, totalSueldos = 0;
        while(true){
            empresaAux = (Empresa) ois.readObject();
            lEmpresasAux.add(empresaAux);
        }
    }
    
    public static void calcularMediaSueldosDeFichero(ArrayList<Empresa> lEmpresasAux){
        int numEmpleados = 0, totalSueldos = 0;
        for(int i = 0;i < lEmpresasAux.size();i++){
            numEmpleados = numEmpleados + lEmpresasAux.get(i).getNumEmpleados();
            totalSueldos = totalSueldos + lEmpresasAux.get(i).getTotalSueldos();
        }
        System.out.println("Media sueldos de empresas del fichero: "+(totalSueldos/numEmpleados));
    }
    
    //Esqueleto leer en fichero
    public static void leerFichero(int opc){
        ArrayList<Empresa> lEmpresasAux = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            fis = new FileInputStream("empresas.obj");
            ois = new ObjectInputStream(fis);
            if  (opc == 4){
                mostrarEmpresasDeFichero(ois);
            }
            else{
                lEmpresasAux = new ArrayList<>();
                getEmpresasDeFichero(ois, lEmpresasAux);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("No existe el fichero");
        }
        catch(ClassNotFoundException e){
            System.out.println("Error en el casting explícito");
        }
        catch(EOFException e){
            if  (opc == 5){
                calcularMediaSueldosDeFichero(lEmpresasAux);
            }
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
    
    /* Ordena todos los empleados del vector según su sueldo (si quisiésemos ordenar los del fichero,
       tendríamos que hacer previamente una lectura del fichero para obtener las empresas) */
    public static void ordenarEmpleados(Empresa[] vEmpresas){
        ArrayList<Empleado> lEmpleadosAux;
        ArrayList<Empleado> lEmpleadosTotales = new ArrayList<>();
        Empleado empleadoAux;
        //AÑADIMOS TODOS LOS EMPLEADOS EN UN SOLO ARRAYLIST
        for(int i = 0; i < vEmpresas.length;i++){
            //Obtenemos los empleados de la empresa i
            lEmpleadosAux = vEmpresas[i].getLEmpleados();
            //Insertamos uno a uno los empleados de la empresa i en la lista lEmpleadosTotales
            for(int j = 0; j < lEmpleadosAux.size();j++){
                lEmpleadosTotales.add(lEmpleadosAux.get(j));
            }
        }
        //MÉTODO DE LA BURBUJA
        for(int i = 0; i < lEmpleadosTotales.size() - 1;i++){
            for(int j = 0; j < lEmpleadosTotales.size() -i - 1;j++){  //-i-1
                if  (lEmpleadosTotales.get(j).getSueldo() < lEmpleadosTotales.get(j+1).getSueldo()){
                    empleadoAux = lEmpleadosTotales.get(j+1);
                    lEmpleadosTotales.set(j+1, lEmpleadosTotales.get(j));
                    lEmpleadosTotales.set(j, empleadoAux);
                }
            }
        }
        //MOSTRAMOS EMPLEADOS ORDENADOS
        for(int i = 0;i < lEmpleadosTotales.size();i++){
            lEmpleadosTotales.get(i).mostrarEmpleado();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Empresa[] vEmpresas = new Empresa[3];
        int opc;
        do{
            System.out.println("1. Rellenar array de 3 empresas.");
            System.out.println("2. Mostrar empresas junto con empleados");
            System.out.println("3. Volvar empresas a fichero.");
            System.out.println("4. Mostrar las empresas del fichero.");
            System.out.println("5. Calcular la media de los sueldos de todos los empleados del fichero.");
            System.out.println("6. Mostrar empleados ordnados por sueldo.");
            System.out.println("7. Salir");
            System.out.print("   Opción: ");
            opc = teclado.nextInt();
            switch(opc){
                case 1:
                    rellenarVector(vEmpresas);
                    break;
                case 2:
                    mostrarEmpresasDelVector(vEmpresas);
                    break;
                case 3:
                    escribirFichero(vEmpresas);
                    break;
                case 4:
                    leerFichero(opc);
                    break;
                case 5:
                    leerFichero(opc);
                    break;
                case 6:
                    ordenarEmpleados(vEmpresas);
                    break;
                case 7:
                    System.out.println("¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción incorrecta.");
            }
        }while(opc != 7);
    }
    
}
