package ejercicio1;

/**
 *
 * @author java2
 */
public class Ejercicio1 {

    public static void rellenarMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = (int) Math.round(Math.random() * 99 + 1);
            }
        }
    }
    /**
     * 
     * @param matriz 
     */
    public static void mostrarMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            System.out.println("");//Salto de linea
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(" " + matriz[i][j] + " ");
            }
        }
    }

    public static int retornarMayor(int[][] matriz) {
        int mayor = matriz[0][0];
        //Recorro la matriz comprobando que la variable mayor es la mayor, si no lo es la intercambio
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (mayor < matriz[i][j]) {
                    mayor = matriz[i][j];
                }
            }
        }
        return mayor;
    }

    public static int retornarSuma(int[][] matriz) {
        int suma = 0;
        //Recorro la matriz sumando todos los valores en la variable suma
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                suma += matriz[i][j];
            }
        }
        return suma;
    }

    public static int retornarPares(int[][] matriz) {
        int pares = 0;
        //Recorro la matriz en busca de numeros pares, si encuentro uno incremento el contador "pares"
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] % 2 == 0) {
                    pares++;
                }
            }
        }
        return pares;
    }

    public static void main(String[] args) {
        //Matriz
        int[][] matriz = new int[4][3];
        //Rellenar matriz
        rellenarMatriz(matriz);
        //Mostrar informacion
        System.out.println("==========MATRIZ==========");
        mostrarMatriz(matriz);
        System.out.println("\n==========================");
        System.out.println("El numero mayor es: " + retornarMayor(matriz));
        System.out.println("La suma total de los numeros de la matriz es: " + retornarSuma(matriz));
        System.out.println("El numero de pares es: " + retornarPares(matriz));
    }

}
