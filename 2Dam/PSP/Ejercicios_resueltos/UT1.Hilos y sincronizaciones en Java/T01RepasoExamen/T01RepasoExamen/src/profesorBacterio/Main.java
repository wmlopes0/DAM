package profesorBacterio;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Controlador controlador = new Controlador();
        TuberiaHidrogeno tuberiaHidrogeno = new TuberiaHidrogeno(controlador);
        TuberiaOxigeno tuberiaOxigeno = new TuberiaOxigeno(controlador);

        tuberiaHidrogeno.start();
        tuberiaOxigeno.start();

        tuberiaHidrogeno.join();
        tuberiaOxigeno.join();

        System.out.println("FIN DEL PROGRAMA");
    }
}
