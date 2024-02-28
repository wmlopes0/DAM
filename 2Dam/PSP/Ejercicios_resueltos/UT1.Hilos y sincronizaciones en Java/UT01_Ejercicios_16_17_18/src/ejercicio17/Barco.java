package ejercicio17;

public class Barco extends Thread {
    //    Atributos
    private Puente puente;

    //    Constructor
    public Barco(Puente puente) {
        this.puente = puente;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                sleep(Utileria.tiempoAleatorio(0, 150));
//            Entrar
                puente.entrar(null, this);
                puente.salir(null, this);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
