package la_aldea_gala;

public class Main {
    public static void main(String[] args) {
        Marmita marmita = new Marmita(4);
        Galo galo1 = new Galo("Asterix", marmita);
        Galo galo2 = new Galo("Falbalá", marmita);
        Galo galo3 = new Galo("Asurancetúrix", marmita);
        Druida druida = new Druida("Druida", marmita);
        galo1.start();
        galo2.start();
        galo3.start();
        druida.start();

    }
}
