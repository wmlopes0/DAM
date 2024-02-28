package tienda_pajaros;

public class Encargado extends Thread{
    private Comedero comedero;

    public Encargado(Comedero comedero) {
        this.comedero = comedero;
    }

    @Override
    public void run() {
        while (true){
            comedero.reponerAlpiste(this);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }
        }
    }
}
