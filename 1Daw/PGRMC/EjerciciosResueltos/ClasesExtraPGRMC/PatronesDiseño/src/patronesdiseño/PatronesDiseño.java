package patronesdiseño;

import patronesdiseño.Persona.PersonaBuilder;

/**
 *
 * @author Walter
 */
public class PatronesDiseño {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*Persona walter = new PersonaBuilder()
                .buildNombre("Walter")
                .buildApellido("Martin Lopes")
                .buildEdad(24)
                .build();*/
        
        PersonaBuilder buildWalter = new PersonaBuilder();
        buildWalter.buildNombre("Walter");
        buildWalter.buildApellido("Martin Lopes");
        buildWalter.buildEdad(24);
        Persona walter = buildWalter.build();
        
        System.out.println(walter.toString());
        
        walter.setCorreoElectronico("walterlopesdiez@gmail.com");
        
        System.out.println(walter.toString());
    }
    
}
