package ejercicio16_solucion;

public class Profesor extends Thread {
    Banyo banyo;
    String nombre;

    public Profesor(Banyo control, String nombre) {
        this.banyo = control;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            banyo.entrabanio(nombre);
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
