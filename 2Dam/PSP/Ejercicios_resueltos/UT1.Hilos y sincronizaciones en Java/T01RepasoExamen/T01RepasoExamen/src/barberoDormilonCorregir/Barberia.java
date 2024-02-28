package barberoDormilonCorregir;

public class Barberia {

    private int numSillasSalaEspera;
    private int sillasOcupadas;
    private boolean sillaBarberoOcupada;
    private boolean finCorte;

    public Barberia(int numSillasSalaEspera) {
        this.numSillasSalaEspera = numSillasSalaEspera;
        sillasOcupadas = 0;
        sillaBarberoOcupada = false;
        finCorte = true;
    }


    public synchronized boolean entrar(Cliente cliente) {
        if (sillasOcupadas == numSillasSalaEspera) {
            System.out.println("Sala de espera llena, el " + cliente.getNombre() + " abandona la peluquería.");
            return false;
        } else {
            if (sillaBarberoOcupada) {
                sillasOcupadas++;
                System.out.println("-----" + cliente.getNombre() + " se siente en la sala de espera.Sillas ocupadas " + sillasOcupadas);
            }
            while (sillaBarberoOcupada) {
                try {
                    wait();//Espera a que notifiquen que este vacia
                } catch (InterruptedException e) {
//                       throw new RuntimeException(e);
                }
            }
            //Si hay sillas ocupadas la decremento porque entra acortarse el pelo, sino no porque quedaria en negativo
            if (sillasOcupadas > 0) sillasOcupadas--;
            sillaBarberoOcupada = true;
            System.out.println("----El cliente " + cliente.getNombre() + " se sienta en la silla del Barbero.Sillas ocupadas: " + sillasOcupadas);
            notifyAll();
            finCorte = false;
            while (!finCorte) {
                try {
                    wait();
                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
                }
            }

            sillaBarberoOcupada = false;
            System.out.println("\t\t---El cliente " + cliente.getNombre() + " se va con el pelo cortado");
            finCorte = true;
            notifyAll();
            return true;
        }

    }

    public synchronized void cortarPelo(Barbero barbero) {
        while (!sillaBarberoOcupada) {
            System.out.println(barbero.getNombre() + " esperando cliente...Zzzzzzzz");
            try {
                wait();//Espero
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }
        }
        System.out.println(barbero.getNombre() + " cortando el pelo XXXXX");
        try {
            Thread.sleep(5000);//Tiempo en cortar el pelo
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
        System.out.println("Barbero termina de cortar el pelo");
        sillaBarberoOcupada = false;
        finCorte = true;
        notifyAll();
    }

}
