package t02_repaso_examen2;

public class LetraB extends Thread{

    private String letra;
    private Pantalla pantalla;

    public LetraB(Pantalla pantalla) {
        this.letra = "B";
        this.pantalla = pantalla;
    }

    public String getLetra() {
        return letra;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                pantalla.pintarB(this);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            /*throw new RuntimeException(e);*/
        }
    }
}
