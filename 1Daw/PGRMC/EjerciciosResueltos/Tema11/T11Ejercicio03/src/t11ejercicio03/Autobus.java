package t11ejercicio03;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Autobus {

    private String matricula;
    private Map<String, Conductor> conductores;

    public Autobus() {
        matricula = "";
        conductores = new HashMap<>();
    }

    public Autobus(String matricula, Map<String, Conductor> conductores) {
        this.matricula = matricula;
        this.conductores = conductores;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setConductores(Map<String, Conductor> conductores) {
        this.conductores = conductores;
    }

    public String getMatricula() {
        return matricula;
    }

    public Map<String, Conductor> getConductores() {
        return conductores;
    }

    //METODOS PROPIOS
    //Rellenar
    public void rellenarAutobus() {
        System.out.println("========AUTOBUS========");
        rellenarMatricula();
        rellenarConductores();
        System.out.println("=======================");
    }

    private void rellenarMatricula() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce la matricula: ");
        matricula = entrada.nextLine();
    }

    private void rellenarConductores() {
        Conductor conductor;
        do {
            conductor = new Conductor();
            conductor.rellenarConductor();
            conductores.put(conductor.getDni(), conductor);
        } while (seguir());
    }

    private boolean seguir() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("¿Desea seguir introduciendo conductores?");
        return entrada.nextLine().equalsIgnoreCase("si");
    }

    //Mostrar
    public void mostrarAutobus() {
        System.out.println("========AUTOBUS========");
        System.out.println("Matricula: " + matricula);
        mostrarConductores();
        System.out.println("=======================");
    }

    private void mostrarConductores() {
        Conductor conductor;
        for (String con : conductores.keySet()) {
            conductor = conductores.get(con);
            conductor.mostrarConductor();
        }
    }

    //Acciones
    public void buscarConductor(String dni) {
        if (conductores.containsKey(dni)) {
            System.out.println("Conduce el autobus con matricula: " + matricula);
        }
    }

    public int numeroConductores() {
        return conductores.size();
    }
}
