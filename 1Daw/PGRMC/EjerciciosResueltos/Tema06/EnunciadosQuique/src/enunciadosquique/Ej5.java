package enunciadosquique;

/**
 *
 * @author Walter
 */
public class Ej5 {
    
    final static int LONGITUD = 23;
    
    public static void rellenarVector1(int[] vector) {
        for (int i = 0; i < LONGITUD; i++) {
            vector[i] = (int) Math.round(Math.random() * 49 + 1);
        }
    }
    
    public static void rellenarVector2(int[] vector) {
        for (int i = 0; i < LONGITUD; i++) {
            vector[i] = -1;
        }
    }
    
    public static void mostrarVector(int[] vector) {
        for (int i = 0; i < LONGITUD; i++) {
            System.out.print(vector[i] + "  ");
        }
    }
    
    public static void insertarVector(int[] vector1, int[] vector2) {
        for (int i = 0; i < LONGITUD; i++) {
            if (vector1[i] > 25 && vector1[i] % 2 == 0) {
                vector2[i]=vector1[i];
            }
        }
    }
    
    public static void main(String[] args) {
        int[] vector1 = new int[LONGITUD];
        rellenarVector1(vector1);
        mostrarVector(vector1);
        System.out.println("");//Salto de linea
        int[] vector2 = new int[LONGITUD];
        rellenarVector2(vector2);
        insertarVector(vector1, vector2);
        mostrarVector(vector2);
    }
}
