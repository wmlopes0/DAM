public class Jugador extends Thread {

    //Atributos
    private String nombre;
    private Cancha cancha;

    //Constructor
    public Jugador(String nombre, Cancha cancha) {
        this.nombre = nombre;
        this.cancha = cancha;
    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        try {
            cancha.jugarPartido(this);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
    }
}
