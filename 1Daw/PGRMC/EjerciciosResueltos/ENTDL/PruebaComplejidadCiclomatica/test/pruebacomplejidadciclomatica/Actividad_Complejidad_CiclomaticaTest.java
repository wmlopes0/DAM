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
public class Actividad_Complejidad_CiclomaticaTest {
    
    public Actividad_Complejidad_CiclomaticaTest() {
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
     * Test of esPerfecto method, of class Actividad_Complejidad_Ciclomatica.
     */
    @Test
    public void testEsPerfectoCajaNegra() {
        System.out.println("EsPerfectoCajaNegra");
        Actividad_Complejidad_Ciclomatica instance = new Actividad_Complejidad_Ciclomatica();
        
        //Como es un método booleano probamos un par de casos
        assertEquals(false,instance.esPerfecto(1));
        assertEquals(true,instance.esPerfecto(6));

    }
    
}
