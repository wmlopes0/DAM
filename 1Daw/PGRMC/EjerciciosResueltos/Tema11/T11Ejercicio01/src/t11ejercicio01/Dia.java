package t11ejercicio01;

/**
 *
 * @author Walter
 */
public class Dia {

    private String nombreDia;
    private int temperatura;

    public Dia() {
        nombreDia = "";
        temperatura = 0;
    }

    public Dia(String nombreDia, int temperatura) {
        this.nombreDia = nombreDia;
        this.temperatura = temperatura;
    }

    public void setNombreDia(String nombreDia) {
        this.nombreDia = nombreDia;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public String getNombreDia() {
        return nombreDia;
    }

    public int getTemperatura() {
        return temperatura;
    }

    //MOTRAR
    public void mostrarDia() {
        System.out.println("Dia de la semana: "+nombreDia);
        System.out.println("Temperatura: "+temperatura+"º");
        System.out.println("------------------------------");
    }
}
