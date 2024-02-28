public class Main {
    public static void main(String[] args) {
        Marmita marmita = new Marmita(4);
        Galo galo1 = new Galo("Asterix", marmita);
        Galo galo2 = new Galo("Falbalá", marmita);
        Galo galo3 = new Galo("Idéafix", marmita);
        Galo galo4 = new Galo("Asutancetúrix", marmita);
        Galo galo5 = new Galo("Abraracúrcix", marmita);
        Galo galo6 = new Galo("Obelix", marmita);
        Druida druida = new Druida("Panoramix", marmita);

        druida.start();
        galo1.start();
        galo2.start();
        galo3.start();
        galo4.start();
        galo5.start();
        galo6.start();

        try {
            druida.join();
            galo1.join();
            galo2.join();
            galo3.join();
            galo4.join();
            galo5.join();
            galo6.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("PROGRAMA FINALIZADO.");

    }
}
