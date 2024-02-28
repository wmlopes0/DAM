public class Druida extends Thread {
    //Atributos
    private String nombre;
    private Marmita marmita;

    //Constructor
    public Druida(String nombre, Marmita marmita) {
        this.nombre = nombre;
        this.marmita = marmita;
    }

    //Getter
    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        //El Druida va a rellenar 3 veces la marmita durante la ejecución del programa
        for (int i = 0; i < 3; i++) {
            marmita.rellenar(this);
        }
    }
}
