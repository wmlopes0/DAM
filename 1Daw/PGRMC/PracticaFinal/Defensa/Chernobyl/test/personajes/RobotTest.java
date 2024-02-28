/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package personajes;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Walter
 */
public class RobotTest {

    /**
     * Test of descifrarRuta method, of class Robot.
     */
    @Test
    public void testDescifrarRuta() {
        System.out.println("descifrarRuta");
        String rutaCifrada = "RNZXCSETEPLPOWSAAH";
        Robot robot = new Robot();
        ArrayList<Character> resultadoEsperado = new ArrayList<>(Arrays.asList('N','S','E','E','O','S'));
        
        //Descrifro
        robot.descifrarRuta(rutaCifrada);
        
        //Compruebo si se ha guardado bien la ruta
        assertEquals(robot.getlRuta().toString(), resultadoEsperado.toString());
    }
}
