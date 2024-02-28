package profesorBacterio;

public class TuberiaOxigeno extends Thread {
    private Controlador controlador;

    public TuberiaOxigeno(Controlador controlador) {
        this.controlador = controlador;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++){
            controlador.meterOxigeno();
        }
    }
}
