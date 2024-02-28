package profesorBacterio;

public class TuberiaHidrogeno extends Thread {
    private Controlador controlador;

    public TuberiaHidrogeno(Controlador controlador) {
        this.controlador = controlador;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            controlador.meterHidrogeno();
        }
    }
}
