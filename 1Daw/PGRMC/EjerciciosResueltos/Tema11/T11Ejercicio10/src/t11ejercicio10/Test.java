package t11ejercicio10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author Walter
 */
public class Test {

    public static void main(String[] args) {

        //TREESET
        Set<Persona> conjunto = new TreeSet<>();
        conjunto.add(new Persona("Quique", 38));
        conjunto.add(new Persona("Elena", 26));
        conjunto.add(new Persona("Alba", 33));
        conjunto.add(new Persona("Julian", 45));
        conjunto.add(new Persona("Martin", 7));

        System.out.println("Mostrando TreeSet");
        mostrarConjunto(conjunto);

        //HASHSET
        Set<Persona> conjuntoHash = new HashSet<>();
        conjuntoHash.add(new Persona("Quique", 38));
        conjuntoHash.add(new Persona("Elena", 26));
        conjuntoHash.add(new Persona("Alba", 33));
        conjuntoHash.add(new Persona("Julian", 45));
        conjuntoHash.add(new Persona("Martin", 7));
        System.out.println("Mostrando HashSet");
        mostrarConjunto(conjuntoHash);

        //ARRAYLIST
        List<Persona> lConjunto = new ArrayList<>();
        lConjunto.add(new Persona("Quique", 38));
        lConjunto.add(new Persona("Elena", 26));
        lConjunto.add(new Persona("Alba", 33));
        lConjunto.add(new Persona("Julian", 45));
        lConjunto.add(new Persona("Martin", 7));

        Collections.sort(lConjunto);
        System.out.println("Mostrando ArrayList");
        mostrarLista(lConjunto);

        //ARRAYLIST
        Map<Persona, Integer> mConjunto = new TreeMap<>();
        mConjunto.put(new Persona("Quique", 38), 1);
        mConjunto.put(new Persona("Elena", 26), 2);
        mConjunto.put(new Persona("Alba", 33), 3);
        mConjunto.put(new Persona("Julian", 45), 4);
        mConjunto.put(new Persona("Martin", 7), 5);

        //Collections.sort(lConjunto);
        System.out.println("Mostrando Mapa");
        mostrarMapa(mConjunto);
    }

    private static void mostrarConjunto(Set<Persona> conjunto) {
        for (Persona persona : conjunto) {
            persona.mostrar();
        }
    }

    private static void mostrarLista(List<Persona> lConjunto) {
        for (Persona persona : lConjunto) {
            persona.mostrar();
        }
    }

    private static void mostrarMapa(Map<Persona, Integer> mConjunto) {
        for (Persona persona : mConjunto.keySet()) {
            persona.mostrar();
        }
    }
}
