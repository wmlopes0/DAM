package pruebacomplejidadciclomatica;

/**
 *
 * @author Walter
 */
public class Actividad_Complejidad_Ciclomatica {

    public boolean esPerfecto(int j) {
        int suma;
        boolean resultado; 
        int i;

        suma = 0;                    //NODO1
        resultado = false;           //NODO1

        for (i = 1; i < j; i++) {   //NODO2
            if (j % i == 0) {       //NODO3         
                suma = suma + i;    //NODO4
            }
        }
        if (suma == j) {            //NODO5
            resultado = true;       //NODO6
        }
        return resultado;           //NODO7
    }

}
