/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package generico;

import org.junit.Test;
import static org.junit.Assert.*;
import personajes.*;

/**
 *
 * @author Walter
 */
public class CeldaTest {

    @Test
    public void testBuscarPersonaje() {
        System.out.println("#buscarPersonaje()");

        //Creo un objeto celda para prueba
        Celda celda = new Celda();

        //Creo 3 personajes 
        Personaje p1 = new Cientifico("Legásov", 1, 10, 'L');
        Personaje p2 = new Cientifico("Shcherbina", 2, 1, 'S');
        Personaje p3 = new Minero("Glújov", 4, 0, 'G');

        //Inserto personajes en el atributo "lPersonajes"
        celda.insertarPersonaje(p1);
        celda.insertarPersonaje(p2);
        celda.insertarPersonaje(p3);

        //REALIZO LA BÚSQUEDA
        assertEquals(p3, celda.buscarPersonaje("Glújov"));
        assertEquals(p2, celda.buscarPersonaje("Shcherbina"));
        assertEquals(p1, celda.buscarPersonaje("Legásov"));
        assertEquals(p3, celda.buscarPersonaje("GLÚJOV"));
        assertEquals(p2, celda.buscarPersonaje("SHCHERBINA"));
        assertEquals(p1, celda.buscarPersonaje("LEGÁSOV"));

        assertNull(celda.buscarPersonaje("PEPE"));

    }

    @Test
    public void testBorrarPersonaje() {
        System.out.println("#borrarPersonaje()");

        //Creo un objeto celda para prueba
        Celda celda = new Celda();

        //Creo 3 personajes 
        Personaje p1 = new Cientifico("Legásov", 1, 10, 'L');
        Personaje p2 = new Cientifico("Shcherbina", 2, 1, 'S');
        Personaje p3 = new Minero("Glújov", 4, 0, 'G');

        //Inserto personajes en el atributo "lPersonajes"
        celda.insertarPersonaje(p1);
        celda.insertarPersonaje(p2);
        celda.insertarPersonaje(p3);

        //Borro un personaje y luego lo busco a ver si devuelve null
        assertEquals(p1, celda.buscarPersonaje("Legásov"));
        celda.borrarPersonaje("Legásov");
        assertNull(celda.buscarPersonaje("Legásov"));
    }

    @Test
    public void testBuscarAlgunOperador() {
        System.out.println("#buscarAlgunOperador()");

        //Creo un objeto celda para prueba
        Celda celda = new Celda();

        //Creo 3 personajes, 1 operador y 2 de la KGB
        Personaje p1 = new Cientifico("Legásov", 1, 10, 'L');
        Personaje p2 = new Voluntario("Pepe", 1, 8, 'P');
        Personaje p3 = new Oficial("Quique", 1, 10, 'Q');

        //Inserto personajes en el atributo "lPersonajes"
        celda.insertarPersonaje(p1);
        celda.insertarPersonaje(p2);
        celda.insertarPersonaje(p3);

        //COMPRUEBO QUE DEVUELVE TRUE
        assertTrue(celda.buscarAlgunOperador());

        //BORRO OPERADOR
        celda.borrarPersonaje("Legásov");

        //COMPRUEBO QUE DEVUELVE FALSE
        assertFalse(celda.buscarAlgunOperador());
    }
}
