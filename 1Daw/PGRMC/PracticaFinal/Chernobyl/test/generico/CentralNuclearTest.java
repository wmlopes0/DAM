/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package generico;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Walter
 */
public class CentralNuclearTest {

    /**
     * Test of getAdyacentes method, of class CentralNuclear.
     */
    @Test
    public void testGetAdyacentes() {
        System.out.println("getAdyacentes");
        CentralNuclear central = CentralNuclear.getInstancia();
        ArrayList<Integer> expResult;
        ArrayList<Integer> result;
        int fila, columna;

        //TODAS LAS POSIBLES
        fila = 3;
        columna = 3;
        expResult = new ArrayList<>(Arrays.asList(19, 20, 28, 36, 35, 34, 26, 18));
        result = central.getAdyacentes(fila, columna);
        assertEquals(expResult, result);
        //LATERAL IZQUIERO
        fila = 2;
        columna = 0;
        expResult = new ArrayList<>(Arrays.asList(8, 9, 17, 25, 24));
        result = central.getAdyacentes(fila, columna);
        assertEquals(expResult, result);
        //LATERAL DERECHO
        fila = 2;
        columna = 7;
        expResult = new ArrayList<>(Arrays.asList(15, 31, 30, 22, 14));
        result = central.getAdyacentes(fila, columna);
        assertEquals(expResult, result);
        //ARRIBA
        fila = 0;
        columna = 3;
        expResult = new ArrayList<>(Arrays.asList(4, 12, 11, 10, 2));
        result = central.getAdyacentes(fila, columna);
        assertEquals(expResult, result);
        //ABAJO
        fila = 7;
        columna = 3;
        expResult = new ArrayList<>(Arrays.asList(51, 52, 60, 58, 50));
        result = central.getAdyacentes(fila, columna);
        assertEquals(expResult, result);
    }

}
