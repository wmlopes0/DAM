package filosofos;

public class Mesa {
    public Mesa() {
    }

    public void comer(Filosofo filosofo) {
        try {
            cogerPalillos(filosofo);
            System.out.println("\t" + filosofo.getNombre() + " está comiendo.");
            Thread.sleep(500);//Duerme simulando que come
            dejarPalillos(filosofo);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
    }

    private synchronized void cogerPalillos(Filosofo filosofo) {
        while (filosofo.getPalilloIzquierdo().isCogido() || filosofo.getPalilloDerecho().isCogido()) {
            try {
                wait(100); //Espero
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }
        }
        filosofo.getPalilloIzquierdo().setCogido(true);
        filosofo.getPalilloDerecho().setCogido(true);
        System.out.println(filosofo.getNombre() + " coge los palillos.");
        notifyAll();
    }

    private synchronized void dejarPalillos(Filosofo filosofo) {
        filosofo.getPalilloIzquierdo().setCogido(false);
        filosofo.getPalilloDerecho().setCogido(false);
        System.out.println("\t\t" + filosofo.getNombre() + " suelta los palillos.");
        notifyAll();
    }

}
