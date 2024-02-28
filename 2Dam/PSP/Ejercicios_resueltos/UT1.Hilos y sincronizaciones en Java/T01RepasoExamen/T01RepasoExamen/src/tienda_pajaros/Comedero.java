package tienda_pajaros;


public class Comedero {
    private int numMaxPajarosComedero;
    private int pajarosComedero;
    private boolean quiereReponer;

    public Comedero(int numMaxPajarosComedero) {
        this.numMaxPajarosComedero = numMaxPajarosComedero;
        this.pajarosComedero = 0;
        this.quiereReponer = false;
    }

    public void comer(Pajaro pajaro) {
        //BLOQUE ENTRAR
        synchronized (this) {
            //Si hay el num max de pajaros comiendo o el encargado quiere reponer, espera
            while (pajarosComedero == numMaxPajarosComedero || quiereReponer) {
                try {
                    wait();//Espero
                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
                }
            }
            pajarosComedero++;
        }
        System.out.println("-----" + pajaro.getNombre() + " está comiendo alpiste <0)");
        try {
            Thread.sleep(2000);//Tarda 2 segundos en comer
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
        //BLOQUE SALIR
        synchronized (this) {
            pajarosComedero--;
            System.out.println("\t\t" + pajaro.getNombre() + " abandona el comedero WW_O_WW");
            notifyAll();
        }
    }

    public synchronized void reponerAlpiste(Encargado encargado) {
        System.out.println("El encargado quiere reponer alpiste");
        quiereReponer = true;
        while (pajarosComedero != 0) {
            try {
                wait();//Espero
            } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
            }
        }
        System.out.println("+++++Reponiendo alpiste");
        quiereReponer = false;
        notifyAll();
    }
}
