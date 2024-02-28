package enunciadosquique;

/**
 *
 * @author Walter
 */
public class Ej1 {

    public static void rellenarArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
    }

    public static void mostrarArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("Posicion " + (i + 1) + ": " + array[i]);
        }
    }

    public static int sumaArray(int[] array) {
        int suma = 0;
        for (int i = 0; i < array.length; i++) {
            suma += array[i];
        }
        return suma;
    }

    public static int mediaArray(int[] array) {
        int media = 0;
        for (int i = 0; i < array.length; i++) {
            media += array[i];
        }
        //CALCULO LA MEDIA
        media /= array.length;
        
        return media;
    }

    public static void main(String[] args) {
        int[] array = new int[100];
        rellenarArray(array);
        mostrarArray(array);
        System.out.println("==========RESULTADO==========");
        System.out.println("La suma de todos los numeros es: " + sumaArray(array));
        System.out.println("La media de todos los numeros es: " + mediaArray(array));
    }
}
