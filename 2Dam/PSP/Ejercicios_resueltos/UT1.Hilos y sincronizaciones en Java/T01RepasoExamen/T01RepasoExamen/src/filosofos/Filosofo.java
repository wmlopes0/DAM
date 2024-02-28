package filosofos;

public class Filosofo extends Thread {
    private String nombre;
    private Palillo palilloIzquierdo;
    private Palillo palilloDerecho;
    private Mesa mesa;

    public Filosofo(String nombre, Mesa mesa, Palillo palilloIzquierdo, Palillo palilloDerecho) {
        this.nombre = nombre;
        this.mesa = mesa;
        this.palilloIzquierdo = palilloIzquierdo;
        this.palilloDerecho = palilloDerecho;
    }

    public Palillo getPalilloIzquierdo() {
        return palilloIzquierdo;
    }

    public Palillo getPalilloDerecho() {
        return palilloDerecho;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(300);//Pensar
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
        //Comer
        mesa.comer(this);
    }



}
