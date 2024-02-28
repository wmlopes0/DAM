package caritmetica2;

    /**
     * @author Walter Martin Lopes
     * Operaciones Aritméticas
     * @param args the command line arguments
     */
public class CAritmetica2 {

    
    public static void main(String[] args) {
        int dato1,dato2,dato3,resultado;//Declaro, a la vez, cuatro variables enteras: dato1, dato2, dato3 y resultado
        
        dato1 = 20; //Asigno el valor 20 a la variable dato1
        dato2 = 10;
        dato3 = 5;
        
        //Suma
        resultado = dato1 + dato2 + dato3; //Guardo la suma de las dos variables en la variable resultado
        System.out.println(dato1 + " + " + dato2 + " + " + dato3 + " = " + resultado);
        /* El método println escribe por pantalla tanto el valor de las variables asi como las 
        cadenas que están entre comillas. Para unir los 7 elementos se ha utilizado el operador + */
        
        //Resta
        resultado = dato1 - dato2 - dato3; 
        System.out.println(dato1 + " - " + dato2 + " - " + dato3 + " = " + resultado);
        
        //Producto
        resultado = dato1 * dato2 * dato3; 
        System.out.println(dato1 + " * " + dato2 + " * " + dato3 + " = " + resultado);
    }
    
}
