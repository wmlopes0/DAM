/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebacomplejidadciclomatica;

/**
 *
 * @author Walter
 */
public class SumaPrimos {

    public static int sumaPrimo(int numeroMaximo) {
        int acumulador = 0;                             //NODO 1
        int numero = 2;                                 //NODO 1
        int contador;                                   //NODO 1
        boolean primo;                                  //NODO 1

        do {                
            System.out.println(numero);                 //NODO 2
            contador = 2;                               //NODO 2
            primo = true;                               //NODO 2
            while ((primo) && (contador != numero)) {   //NODO 3
                if (numero % contador == 0) {           //NODO4
                    primo = false;                      //NODO 5
                }
                contador++;                             //NODO 6
            }
            if (primo) {                                //NODO 7
                acumulador = acumulador + numero;       //NODO 8
            }
            numero++;                                   //NODO 9
            
        } while (numero <= numeroMaximo);               //NODO 10

        return acumulador;                              //NODO 11
    }

}
