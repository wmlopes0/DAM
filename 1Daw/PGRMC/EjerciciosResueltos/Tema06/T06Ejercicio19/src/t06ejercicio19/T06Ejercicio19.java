/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t06ejercicio19;

/**
 *
 * @author Walter
 */
public class T06Ejercicio19 {

    public static void rellenarArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) Math.round(Math.random() * 70 + 10);
        }
    }

    public static void mostrarArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("Posicion " + (i + 1) + ": " + array[i] + "  ");
        }
    }

    public static int numeroMayor(int[] array) {
        int mayor = array[0];
        for (int i = 0; i < array.length; i++) {
            if (mayor < array[i]) {
                mayor = array[i];
            }
        }
        return mayor;
    }

    public static int numeroMenor(int[] array) {
        int menor = array[0];
        for (int i = 0; i < array.length; i++) {
            if (menor > array[i]) {
                menor = array[i];
            }
        }
        return menor;
    }

    public static int valorMasRepetido(int[] array) {
        int[] contador = new int[array.length];
        int numero;
        int valorMasRepetido = 0;
        int contadorMayor = contador[0];

        //RECORRO EL ARRAY INCREMENTANDO EL ARRAY CONTADOR CADA VEZ QUE SE REPITE EL NUMERO
        for (int i = 0; i < contador.length; i++) {
            numero = array[i];
            for (int j = 0; j < array.length; j++) {
                if (numero == array[j]) {
                    contador[i] += 1;
                }
            }
        }
        //RECORRO EL ARRAY CONTADOR PARA OBTENER EL MAYOR
        for (int i = 0; i < contador.length; i++) {
            if (contadorMayor < contador[i]) {
                contadorMayor = contador[i];
            }
        }
        //LO RECORRO DE NUEVO PARA VER LA POSICION Y ASIGNAR EL VALOR MAS REPETIDO A LA VARIABLE VALORMASREPETIDO PARA RETORNARLA
        for (int i = 0; i < contador.length; i++) {
            if (contadorMayor == 1) {
                valorMasRepetido = 0;
            } else {
                if (contadorMayor == contador[i]) {
                    valorMasRepetido = array[i];
                }
            } 
        }
        return valorMasRepetido;
    }

    public static int mediaArray(int[] array) {
        int media = 0;
        for (int i = 0; i < array.length; i++) {
            media += array[i];
        }
        //CALCULAMOS LA MEDIA
        media /= array.length;
        //RETORNAMOS MEDIA
        return media;
    }

    public static void main(String[] args) {
        int[] array = new int[100];
        rellenarArray(array);
        System.out.println("=======ARRAY=======");
        mostrarArray(array);
        System.out.println("=====RESULTADO=====");
        System.out.println("El numero mayor es: " + numeroMayor(array));
        System.out.println("El numero menor es: " + numeroMenor(array));
        System.out.println("El valor mas repetido es: " + valorMasRepetido(array));
        System.out.println("La media total es: " + mediaArray(array));
    }

}
