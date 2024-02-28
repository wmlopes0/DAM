package reservavuelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Walter
 */
public class VueloCarga implements Vuelo {

    private int numeroVuelo;
    private String origen;
    private String destino;
    private LocalDate fechaSalida;
    private String horaSalida;
    private int cargaMaxima;
    private List<Integer> carga;

    //Constructores
    public VueloCarga() {
        this.numeroVuelo = 0;
        this.origen = "";
        this.destino = "";
        this.fechaSalida = LocalDate.now();
        this.horaSalida = "";
        this.cargaMaxima = 0;
        this.carga = new ArrayList<>();
    }

    public VueloCarga(int numeroVuelo, String origen, String destino, LocalDate fechaSalida, String horaSalida, int cargaMaxima) {
        this.numeroVuelo = numeroVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.horaSalida = horaSalida;
        this.cargaMaxima = cargaMaxima;
        this.carga = new ArrayList<>();
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

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public int getCargaMaxima() {
        return cargaMaxima;
    }

    public void setCargaMaxima(int cargaMaxima) {
        this.cargaMaxima = cargaMaxima;
    }

    public List<Integer> getCarga() {
        return carga;
    }

    public void setCarga(List<Integer> carga) {
        this.carga = carga;
    }

    //Métodos propios
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
        this.cargaMaxima = Utilidades.pedirEntero("Carga máxima: ");
        while (Utilidades.seguir("¿Desea introducir una reserva de carga?")) {
            reservarCarga(Utilidades.pedirEntero("Introduzca la carga deseada:"));
        }
    }

    public boolean reservarCarga(int carga) {
        if (comprobarCarga(carga)) {
            this.carga.add(carga);
            System.out.println("¡CARGA RESERVADA CORRECTAMENTE!");
            return true;
        } else {
            System.out.println("¡LO SENTIMOS! LA CARGA EXCEDE NUESTRA CAPACIDAD MÁXIMA.");
            return false;
        }
    }

    private boolean comprobarCarga(int carga) {
        return (obtenerCargaTotal() + carga) <= cargaMaxima;
    }

    private int obtenerCargaTotal() {
        int suma = 0;
        for (Integer kg : carga) {
            suma += kg;
        }
        return suma;
    }

    @Override
    public boolean reservarAsiento(Pasajero pasajero) {
        //NO HACE NADA
        return false;
    }

    @Override
    public void mostrarInfoVuelo() {
        System.out.println("======================================");
        System.out.println("INFORMACIÓN DEL VUELO " + numeroVuelo);
        System.out.println("======================================");
        System.out.println("Se trata de un vuelo de carga con destino a " + destino + ",\n"
                + "cuenta actualmente con " + obtenerCargaTotal() + " KG de carga reservada\n"
                + "y tiene previsto salir de " + origen + " el día " + fechaSalida + "\na las " + horaSalida + ".\n");
    }

}
