package ejercicio18;

public class Fumador extends Thread {

    //Atributos
    private final int ingrediente;
    private final Banio banio;

    //Constructor
    public Fumador(int ingrediente, Banio banio) {
        this.ingrediente = ingrediente;
        this.banio = banio;
    }

    //Getter ingrediente
    public int getIngrediente() {
        return ingrediente;
    }

    @Override
    public void run() {
        while (true) {
            try {
                banio.entrar(this);
                banio.salir(this);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //Accion de fumar
    public void fumar() {
        System.out.println("        ==~~ ==~~ Fumador " + ingrediente + " fumando ==~~ ==~~");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
