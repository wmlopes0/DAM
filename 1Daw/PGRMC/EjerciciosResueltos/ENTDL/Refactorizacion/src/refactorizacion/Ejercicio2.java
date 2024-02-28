/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package refactorizacion;

/**
 * Hola esto es una prueba
 * @author Walter
 */
public class Ejercicio2 {

    private int num1;
    private int num2;
    private int resultado;

    public Ejercicio2() {
        num1 = 3;
        num1 = 6;
        resultado = 0;
    }

    /**
     * @return the num1
     * 
     */
    public int getNum1() {
        return num1;
    }

    /**
     * @param num1 the num1 to set
     */
    public void setNum1(int num1) {
        this.num1 = num1;
    }

    /**
     * @return the num2
     */
    public int getNum2() {
        return num2;
    }

    /**
     * @param num2 the num2 to set
     */
    public void setNum2(int num2) {
        this.num2 = num2;
    }

    /**
     * @return the resultado
     */
    public int getResultado() {
        return resultado;
    }

    /**
     * @param resultado the resultado to set
     */
    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public static void main(String[] args) {
        int num1 = 3;
        int num2 = 6;
        int resultado;
        resultado = Cociente(num1, num2);
        System.out.println("El resultado es: " + resultado);
        // 'c' será igual a cero puesto que '/' calcula el cociente
        resultado = num1 % num2;
        System.out.println("El resultado es: " + resultado);
        // 'c' será igual a 3 puesto que '%' calcula el resto
        num1++;
        System.out.println("El valor de num1 es: " + num1);
        // 'a' será igual a 4 puesto que su valor se incrementa en 1
        ++num1;
        System.out.println("El valor de num1 es: " + num1);
        // 'a' será igual a 5 puesto que incrementemos en 1 el valor anterior
        resultado = ++num1 + num2++;
        System.out.println("El valor de num1 es: " + num1);
        System.out.println("El valor de num2 es: " + num2);
        System.out.println("El resultado es: " + resultado);
        /**
         * 'a' será igual a 6 puesto que incrementemos en 1 el valor anterior
         * 'b' será igual a 7 puesto que su valor se incrementa en 1 'c' será
         * igual a 13, resultado de sumar los números anteriores
         */
        resultado = ++num1 + ++num2;
        System.out.println("El valor de num1 es: " + num1);
        System.out.println("El valor de num2 es: " + num2);
        System.out.println("El resultado es: " + resultado);
        /**
         * 'a' será igual a 7 puesto que incrementemos en 1 el valor anterior
         * 'b' será igual a 8 puesto que su valor se incrementa en 1 'c' será
         * igual a 15, resultado de sumar los números anteriores
         */
    }

    private static int Cociente(int num1, int num2) {
        int resultado;
        resultado = num1 / num2;
        return resultado;
    }
}
