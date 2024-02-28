package programacionfuncional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.Collectors;

/**
 *
 * @author Walter
 */
public class ProgramacionFuncional {

    public static List<Integer> crearLista() {
        List<Integer> lista = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lista.add(i);
        }
        return lista;
    }

    public static List<Persona> crearPersonas() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona("Jorge", "Martín Lopes"));
        personas.add(new Persona("Jorge", "Lopes Martín"));
        personas.add(new Persona("Walter", "Martín Lopes"));
        return personas;
    }

    public static void main(String[] args) {
        /*crearLista().stream()
                .filter(enteros -> enteros % 2 == 0)
                .map(entero -> entero.toString())
                .forEach(System.out::println);*/

 /*List<String> nuevaLista =crearLista().stream()
                .filter(enteros -> enteros % 2 == 0)
                .map(entero -> entero.toString())
                .collect(Collectors.toList());*/
 /*Optional<Persona> personaFiltrada = crearPersonas().stream()
                .filter(persona -> "Jorge".equals(persona.getNombre()))
                .findFirst();
        
        System.out.println(personaFiltrada.orElse(new Persona("persona","por defecto")).toString());*/
        Optional<String> resultado = Optional.of(1)
                .map(entero -> "Jorge")
                .map(jorge -> "Walter")
                .filter(walter -> "Jorge".equals(walter));

        System.out.println(resultado.isPresent() ? resultado.get() : "no existe");

    }

}
