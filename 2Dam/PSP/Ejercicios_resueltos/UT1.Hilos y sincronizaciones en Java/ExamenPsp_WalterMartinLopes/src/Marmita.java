public class Marmita {

    //Atributos
    private int maxCapacidad;//Máxima capacidad de raciones.
    private int numRaciones;//Número de raciones que quedan.
    private boolean rellenar;//Variable que usaré para decir al Druida cuando tiene que rellenar la marmita.


    //Constuctor
    public Marmita(int maxCapacidad) {
        //Inicio el programa con la marmita a máxima capacidad.
        this.maxCapacidad = maxCapacidad;
        this.numRaciones = maxCapacidad;
        this.rellenar = false;
    }

    //Método sincronizado para beber
    public synchronized void beber(Galo galo) {
        //Controlo si no hay raciones y no está avisado el Druida, para avisarle y que rellene
        if (numRaciones == 0 && !rellenar) {
            System.out.println("000-La marmita está vacía, " + galo.getNombre() + " despierta al Druida");
            rellenar = true;//Aviso al Druida
            notifyAll();//Notifico para despertarlo
        }
        //Los Galos, mientras no haya raciones tienen que esperar
        while (numRaciones == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }
        }
        numRaciones--;//Una vez haya raciones decremento
        System.out.println("---" + galo.getNombre() + " está tomando su ración de brebaje mágico. Raciones restantes: " + numRaciones);
        notifyAll();//Notifico
    }

    //Método sincronizado para rellenar la marmita
    public synchronized void rellenar(Druida druida) {
        //Mientras no tenga la señalde rellenar que espere
        while (!rellenar) {
            try {
                wait();
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }
        }
        rellenar = false;//Actualizo la bandera
        numRaciones = maxCapacidad;//Relleno al máximo la Marmita
        numRaciones--;//Decremento el número de raciones porque bebe
        System.out.println("+++" + druida.getNombre() + " ya ha rellenado la marmita, toma una ración. Raciones restantes: " + numRaciones);
        notifyAll();//Notifico
    }
}
