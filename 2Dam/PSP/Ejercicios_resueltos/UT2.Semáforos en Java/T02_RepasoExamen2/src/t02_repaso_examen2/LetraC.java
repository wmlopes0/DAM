package t02_repaso_examen2;

public class LetraC extends Thread {
    private String letra;
    private Pantalla pantalla;

    public LetraC(Pantalla pantalla) {
        this.letra = "C";
        this.pantalla = pantalla;
    }

    public String getLetra() {
        return letra;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                pantalla.pintarC(this);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            /*throw new RuntimeException(e);*/
        }
    }
}
