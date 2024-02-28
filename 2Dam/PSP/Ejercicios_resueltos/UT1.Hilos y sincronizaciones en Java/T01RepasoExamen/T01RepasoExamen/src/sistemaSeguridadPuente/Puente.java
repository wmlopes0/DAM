package sistemaSeguridadPuente;

public class Puente {

    //Atributos
    private int numMaxVehiculos;
    private int numMaxKg;
    private int numVehiculos;
    private int kg;

    //Constructor
    public Puente(int numMaxVehiculos, int numMaxKg) {
        this.numMaxVehiculos = numMaxVehiculos;
        this.numMaxKg = numMaxKg;
        this.numVehiculos = 0;
        this.kg = 0;
    }

    //El método no puede ser totalmente sincronizado porque quiero que entre mas de un vehiculo
    public void entrarPuente(Vehiculo vehiculo) {
        System.out.println(vehiculo.getTipo() + vehiculo.getIdVehiculo() + " intentando entrar en el puente, peso: " + vehiculo.getPeso() + ", peso actual: " + kg + ", número de vehículos: " + numVehiculos);
        //Compruebo que puedo entrar
        synchronized (this) {
            while (numVehiculos == numMaxVehiculos || (kg + vehiculo.getPeso()) >= numMaxKg) { //Si el numero de vehiculos es igual al numero maximo de vehiculos o el peso es igual al maxPeso
                try {
                    wait();//Espero
                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
                }
            }
            numVehiculos++;
            kg += vehiculo.getPeso();
            System.out.println("===" + vehiculo.getTipo() + vehiculo.getIdVehiculo() + " cruzando el puente");
        }
        //CRUZANDO PUENTE
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
    }

    public synchronized void salirPuente(Vehiculo vehiculo) {
        numVehiculos--;
        kg -= vehiculo.getPeso();
        System.out.println("\t\t" + vehiculo.getTipo() + vehiculo.getIdVehiculo() + " saliendo del puente, peso actual del puente: " + kg);
        notifyAll();
    }
}
