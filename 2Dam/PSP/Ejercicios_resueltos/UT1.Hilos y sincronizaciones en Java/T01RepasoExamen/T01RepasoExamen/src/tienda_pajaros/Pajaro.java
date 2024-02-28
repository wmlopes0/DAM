package tienda_pajaros;

public class Pajaro extends Thread {
    private Comedero comedero;
    private String nombre;


    public Pajaro(String nombre, Comedero comedero) {
        this.nombre = nombre;
        this.comedero = comedero;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        while (true) {
            comedero.comer(this);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }
        }
    }
}
