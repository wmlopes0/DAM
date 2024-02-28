/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t05ejercicio02;

/**
 *
 * @author Walter
 */
public class Test {
    public static void main(String[] args) {
        
        //Creamos los dos cursos e incrementamos el contador de cursos
        Curso curso1 = new Curso();
        Curso.sumarCursos();
        
        Curso curso2 = new Curso();
        Curso.sumarCursos();
        
        //Establecemos nombre y numero de horas para cada uno de ellos
        curso1.setNombreyHoras("PGRMC", 200);
        curso2.setNombreyHoras("LMSGI", 210);
        
        //Muestro los datos de los dos cursos
        System.out.println("===============CURSOS===============");
        System.out.println("Nombre: "+curso1.getNombre());
        System.out.println("Numero de horas: "+curso1.getHoras());
        System.out.println("------------------------------------");
        System.out.println("Nombre: "+curso2.getNombre());
        System.out.println("Numero de horas: "+curso2.getHoras());
        System.out.println("====================================");
        
        //Muestro el numero de cursos
        Curso.verNumeroCursos();
    }
}
