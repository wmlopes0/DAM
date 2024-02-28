/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package junit02;

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
public class SalarioTest {
    
    public SalarioTest() {
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
     * Test of main method, of class Salario.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Salario.main(args);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of calculaSalarioBruto method, of class Salario.
     */
    @Test
    public void testCalculaSalarioBruto() {
        System.out.println("calculaSalarioBruto");
        //Caso1
        String tipo = "vendedor";
        float ventasMes = 2000;
        float horasExtra = 8;
        float expResult = 1360;
        float result = Salario.calculaSalarioBruto(tipo, ventasMes, horasExtra);
        assertEquals(expResult, result, 0);
        
        //Caso2
        tipo = "vendedor";
        ventasMes = 1500;
        horasExtra = 3;
        expResult = 1260;
        result = Salario.calculaSalarioBruto(tipo, ventasMes, horasExtra);
        assertEquals(expResult, result, 0);
        
        //Caso3
        tipo = "vendedor";
        ventasMes = 1499.99f;
        horasExtra = 0;
        expResult = 1100;
        result = Salario.calculaSalarioBruto(tipo, ventasMes, horasExtra);
        assertEquals(expResult, result, 0);
        
        //Caso4
        tipo = "encargado";
        ventasMes = 1250;
        horasExtra = 8;
        expResult = 1760;
        result = Salario.calculaSalarioBruto(tipo, ventasMes, horasExtra);
        assertEquals(expResult, result, 0);
        
        //Caso5
        tipo = "encargado";
        ventasMes = 1000;
        horasExtra = 0;
        expResult = 1600;
        result = Salario.calculaSalarioBruto(tipo, ventasMes, horasExtra);
        assertEquals(expResult, result, 0);
        
        //Caso6
        tipo = "encargado";
        ventasMes = 999.99f;
        horasExtra = 3;
        expResult = 1560;
        result = Salario.calculaSalarioBruto(tipo, ventasMes, horasExtra);
        assertEquals(expResult, result, 0);
        
        //Caso7
        tipo = "encargado";
        ventasMes = 500;
        horasExtra = 0;
        expResult = 1500;
        result = Salario.calculaSalarioBruto(tipo, ventasMes, horasExtra);
        assertEquals(expResult, result, 0);
        
        //Caso8
        tipo = "encargado";
        ventasMes = 0;
        horasExtra = 8;
        expResult = 1660;
        result = Salario.calculaSalarioBruto(tipo, ventasMes, horasExtra);
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of calculaSalarioNeto method, of class Salario.
     */
    @Test
    public void testCalculaSalarioNeto() {
        System.out.println("calculaSalarioNeto");
        //Caso1
        float salarioBruto = 2000;
        float expResult = 1640;
        float result = Salario.calculaSalarioNeto(salarioBruto);
        assertEquals(expResult, result, 0);
        
        //Caso2
        salarioBruto = 1500;
        expResult = 1230;
        result = Salario.calculaSalarioNeto(salarioBruto);
        assertEquals(expResult, result, 0);
        
        //Caso3
        salarioBruto = 1499.99f;
        expResult = 1259.9916f;
        result = Salario.calculaSalarioNeto(salarioBruto);
        assertEquals(expResult, result, 0);
        
        //Caso4
        salarioBruto = 1250;
        expResult = 1050;
        result = Salario.calculaSalarioNeto(salarioBruto);
        assertEquals(expResult, result, 0);
        
        //Caso5
        salarioBruto = 1000;
        expResult = 840;
        result = Salario.calculaSalarioNeto(salarioBruto);
        assertEquals(expResult, result, 0);
        
        //Caso6
        salarioBruto = 999.99f;
        expResult = 999.99f;
        result = Salario.calculaSalarioNeto(salarioBruto);
        assertEquals(expResult, result, 0);
        
        //Caso7
        salarioBruto = 500;
        expResult = 500;
        result = Salario.calculaSalarioNeto(salarioBruto);
        assertEquals(expResult, result, 0);
        
        //Caso8
        salarioBruto = 0;
        expResult = 0;
        result = Salario.calculaSalarioNeto(salarioBruto);
        assertEquals(expResult, result, 0);
    }
    
}
