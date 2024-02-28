/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package t11ejercicio03;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Walter
 */
public class TestTest {

    @Test
    public void testAparcarAutobus() {
        System.out.println("1. Aparcar autobus");
        //Creo aparcamientos
        Autobus[] aparcamiento = new Autobus[6];
        //Creo  3 Conductores
        Conductor c1 = new Conductor("45134320v", "Walter");
        Conductor c2 = new Conductor("45189620j", "Jorge");
        Conductor c3 = new Conductor("44785320a", "Raquel");
        //Creo 2 mapas de conductores
        Map<String, Conductor> mapaConductores1 = new HashMap<>();
        mapaConductores1.put(c1.getDni(), c1);
        mapaConductores1.put(c2.getDni(), c2);
        mapaConductores1.put(c3.getDni(), c3);
        Map<String, Conductor> mapaConductores2 = new HashMap<>();
        mapaConductores1.put(c1.getDni(), c1);
        mapaConductores1.put(c2.getDni(), c2);
        //Creo 2 autobuses
        Autobus a1 = new Autobus("4532BFG", mapaConductores1);
        Autobus a2 = new Autobus("5892BFG", mapaConductores2);
        //APARCO
        aparcamiento[1] = a1;
        aparcamiento[5] = a2;
        //=======================================================
        

    }

}
