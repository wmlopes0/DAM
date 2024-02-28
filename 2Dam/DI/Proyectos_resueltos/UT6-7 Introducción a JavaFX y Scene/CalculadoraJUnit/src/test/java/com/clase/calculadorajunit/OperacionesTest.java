package com.clase.calculadorajunit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author wmartinl01
 */
public class OperacionesTest {
    
    public OperacionesTest() {
    }

    /**
     * Test of sumar method, of class Operaciones.
     */
    @org.junit.jupiter.api.Test
    public void testSumar() {
        System.out.println("sumar");
        int a = 5;
        int b = 5;
        int expResult = 10;
        int result = Operaciones.sumar(a, b);
        assertEquals(expResult, result);
    }
    
}
