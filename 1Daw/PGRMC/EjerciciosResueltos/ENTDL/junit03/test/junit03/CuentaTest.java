/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package junit03;

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
public class CuentaTest {

    public CuentaTest() {
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
     * Test of ingresar method, of class Cuenta.
     */
    @Test
    public void testIngresar() {
        System.out.println("ingresar");
        Cuenta instance = new Cuenta("prueba", 100);
        assertEquals(100, instance.getCantidad(), 0);

        //INGRESAR NEGATIVO
        instance.ingresar(-150);
        assertEquals(100, instance.getCantidad(), 0);
    }

    /**
     * Test of retirar method, of class Cuenta.
     */
    @Test
    public void testRetirar() {
        System.out.println("retirar");
        Cuenta instance = new Cuenta("prueba", 100);
        instance.retirar(50);
        assertEquals(50, instance.getCantidad(), 0);
        
        //RETIRAR MAYOR A SALDO
        instance.retirar(70);
        assertEquals(0, instance.getCantidad(), 0);
    }

}
