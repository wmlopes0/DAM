public class Galo extends Thread {

    //Atributos
    private String nombre;
    private Marmita marmita;

    ///Constructor
    public Galo(String nombre, Marmita marmita) {
        this.nombre = nombre;
        this.marmita = marmita;
    }

    //Getter
    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        //Cada Galo va a beber 2 veces durante la ejecución del programa
        for (int i = 0; i < 2; i++) {
            marmita.beber(this);
            try {
                Thread.sleep(1000);//Espera un poco antes de beber de nuevo
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }
        }
    }
}
