package barberoDormilonCorregir;

public class Cliente extends Thread {

    //Atributos
    private String nombre;
    private Barberia barberia;

    //Constructor
    public Cliente(String nombre, Barberia barberia) {
        this.nombre = nombre;
        this.barberia = barberia;
    }

    //Getter
    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        while (!barberia.entrar(this)) {
            try {
                Thread.sleep(4000);//Duermo 4 segundos
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }
        }
    }
}
