package reservavuelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Walter
 */
public class VueloComercial implements Vuelo {

    private int numeroVuelo;
    private String origen;
    private String destino;
    private LocalDate fechaSalida;
    private String horaSalida;
    private int aforoMaximo;
    private List<Pasajero> pasajeros;

    //Constructores
    public VueloComercial() {
        this.numeroVuelo = 0;
        this.origen = "";
        this.destino = "";
        this.fechaSalida = LocalDate.now();
        this.horaSalida = "";
        this.aforoMaximo = 0;
        this.pasajeros = new ArrayList<>();
    }

    public VueloComercial(int numeroVuelo, String origen, String destino, LocalDate fecha, String horaSalida, int aforoMaximo) {
        this.numeroVuelo = numeroVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fecha;
        this.horaSalida = horaSalida;
        this.aforoMaximo = aforoMaximo;
        this.pasajeros = new ArrayList<>();
    }

    //Getter y Setter
    public int getNumeroVuelo() {
        return numeroVuelo;
    }

    public void setNumeroVuelo(int numeroVuelo) {
        this.numeroVuelo = numeroVuelo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDate getFecha() {
        return fechaSalida;
    }

    public void setFecha(LocalDate fecha) {
        this.fechaSalida = fecha;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public int getAforoMaximo() {
        return aforoMaximo;
    }

    public void setAforoMaximo(int aforoMaximo) {
        this.aforoMaximo = aforoMaximo;
    }

    public List<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(List<Pasajero> pasajeros) {
        this.pasajeros = pasajeros;
    }

    //Metodos propios
    public void rellenarInfoVuelo() {
        System.out.println("******************************");
        System.out.println("** INTRODUCE LA INFORMACIÓN **");
        System.out.println("********* DEL VUELO **********");
        System.out.println("******************************");
        this.numeroVuelo = Utilidades.pedirEntero("Número de vuelo: ");
        this.origen = Utilidades.pedirCadena("Origen: ");
        this.destino = Utilidades.pedirCadena("Destino: ");
        this.fechaSalida = Utilidades.pedirFecha();
        this.horaSalida = Utilidades.pedirCadena("Introduce la hora de salida en formato '00:00': ");
        this.aforoMaximo = Utilidades.pedirEntero("Aforo máximo: ");
        while (Utilidades.seguir("¿Desea introducir la reserva de un pasajero?")) {            
            Pasajero pasajero = new Pasajero();
            pasajero.rellenarPasajero();
            reservarAsiento(pasajero);
        }
    }

    private boolean comprobarAforo() {
        return pasajeros.size() < this.aforoMaximo;
    }

    @Override
    public boolean reservarAsiento(Pasajero pasajero) {
        if (comprobarAforo() && Utilidades.comprobarRequisitosPasajero(pasajero)) {
            pasajeros.add(pasajero);
            System.out.println("¡LA RESERVA SE HA EFECTUADO CORRECTAMENTE!");
            return true;
        } else {
            if (!comprobarAforo()) {
                System.out.println("¡LO SENTIMOS! NO HAY ASIENTOS DISPONIBLES.");
            }
            if (!Utilidades.comprobarRequisitosPasajero(pasajero)) {
                System.out.println("¡LO SENTIMOS! EL PASAJERO NO CUMPLE LOS REQUISITOS MÍNIMOS.");
            }
            return false;
        }
    }

    @Override
    public void mostrarInfoVuelo() {
        System.out.println("======================================");
        System.out.println("INFORMACIÓN DEL VUELO " + numeroVuelo);
        System.out.println("======================================");
        System.out.println("Se trata de un vuelo comercial con destino a " + destino + ",\n"
                + "cuenta actualmente con " + pasajeros.size() + " reservas y tiene previsto "
                + "salir\nde " + origen + " el día " + fechaSalida + " a las " + horaSalida + ".\n");
    }

}
