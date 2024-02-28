/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package pruebacomplejidadciclomatica;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Walter
 */
public class BurbujaTest {

    public BurbujaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of burbuja method, of class Burbuja.
     */
    @Test
    public void testBurbuja() {
        System.out.println("burbuja");
        int[] arrayNumeros = {1, 2, 3};
        Burbuja instance = new Burbuja();
        boolean expResult = false;
        boolean result = instance.burbuja(arrayNumeros);
        assertEquals(expResult, result);

    }

    @Test
    public void testBurbujaCajaNegra() {
        Burbuja hugo = new Burbuja();
        int[] caso1 = {1, 2, 3};
        int[] caso2 = {2, 1, 3};

        assertEquals(true, hugo.burbuja(caso1));
        assertEquals(false, hugo.burbuja(caso2));
        
        assertTrue(hugo.burbuja(caso1));
    }

    @Test
    public void testSumaCajaNegra() {
        Burbuja hugo = new Burbuja();
        int num1 = 1;
        int num2 = 2;
        int resultado = 3;
        
        assertEquals(resultado, hugo.suma(num1, num2));
    }
}
