package enunciadosquique;

/**
 *
 * @author Walter
 */
public class Ej4 {

    public static boolean compararArrays(int[] array1, int[] array2) {
        boolean iguales = true;
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                iguales = false;
            }
        }
        return iguales;
    }

    public static void main(String[] args) {
        int[] array1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] array2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("¿SON IGUALES LOS DOS ARRAYS?");
        System.out.println(compararArrays(array1, array2));

    }
}
