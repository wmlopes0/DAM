package ejercicio2;

import java.util.Scanner;

/**
 *
 * @author java2
 */
public class Averia {

    private String motivo;
    private float importe;
    private boolean cobrada;

    public Averia() {
        motivo = "";
        importe = 0;
        cobrada = false;
    }

    public Averia(String motivo, float importe, boolean cobrada) {
        this.motivo = motivo;
        this.importe = importe;
        this.cobrada = cobrada;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public void setCobrada(boolean cobrada) {
        this.cobrada = cobrada;
    }

    public String getMotivo() {
        return motivo;
    }

    public float getImporte() {
        return importe;
    }

    public boolean isCobrada() {
        return cobrada;
    }

    //METODOS PROPIOS DE AVERIA
    public void rellenarAveria() {
        System.out.println("-----RELLENAR AVERIA-----");
        motivo = pedirMotivo();
        importe = pedirImporte();
        cobrada = pedirCobrada();
    }

    //Metodos rellenar, son privados porque no me interesa que se puedan llamar desde la clase Test.
    private String pedirMotivo() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduzca el motivo: ");
        return entrada.nextLine();
    }

    private float pedirImporte() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduzca el importe: ");
        return entrada.nextFloat();
    }

    private boolean pedirCobrada() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("¿Esta cobrada?: ");
        return entrada.nextLine().equalsIgnoreCase("si");
    }

    //-------------------------------------------------------------------------
    public void mostrarAveria() {
        //Variable para mostrar si o no en el atributo cobrada
        String estaCobrada = "No";
        if (cobrada) {
            estaCobrada = "Si";
        }
        //-----------------------------
        System.out.println("------AVERIA------");
        System.out.println("Motivo: " + motivo);
        System.out.println("Importe: " + importe);
        System.out.println("¿Cobrada?: " + estaCobrada);
        System.out.println("-------------------");
    }
}
