package reservavuelos;

import java.time.LocalDate;

/**
 *
 * @author Walter
 */
public class Main {

    //Constantes
    final static int EDAD_MINIMA = 16;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("****************************************");
        System.out.println("*********** A T E N C I Ó N ************");
        System.out.println("** DATOS INTRODUCIDOS AUTOMÁTICAMENTE **");
        System.out.println("****************************************");
        Thread.sleep(3000);
        //Creo pasajeros
        Pasajero pasajero1 = new Pasajero("Pasajero1", 45132526, 25);
        Pasajero pasajero2 = new Pasajero("Pasajero2", 45132527, 26);
        Pasajero pasajero3 = new Pasajero("Pasajero3", 45132528, 14);
        Pasajero pasajero4 = new Pasajero("Pasajero4", 45132529, 28);

        //CREO VUELO COMERCIAL
        VueloComercial vuelo1 = new VueloComercial();
        vuelo1.setNumeroVuelo(12345);
        vuelo1.setAforoMaximo(50);
        vuelo1.setOrigen("Madrid");
        vuelo1.setDestino("Francia");
        vuelo1.setFecha(LocalDate.of(2023, 10, 03));
        vuelo1.setHoraSalida("23:00");
        //Añado pasajeros
        vuelo1.reservarAsiento(pasajero1);
        vuelo1.reservarAsiento(pasajero2);
        vuelo1.reservarAsiento(pasajero3);
        vuelo1.reservarAsiento(pasajero4);
        //Muestro información del vuelo
        vuelo1.mostrarInfoVuelo();

        //CREO VUELO DE CARGA
        VueloCarga vuelo2 = new VueloCarga();
        vuelo2.setNumeroVuelo(23456);
        vuelo2.setCargaMaxima(5000);
        vuelo2.setOrigen("Barcelona");
        vuelo2.setDestino("EEUU");
        vuelo2.setFechaSalida(LocalDate.of(2023, 10, 23));
        vuelo2.setHoraSalida("08:30");
        //Añado pasajeros
        vuelo2.reservarCarga(2500);
        vuelo2.reservarCarga(500);
        vuelo2.reservarCarga(523);
        //Muestro información del vuelo
        vuelo2.mostrarInfoVuelo();

        System.out.println("****************************************");
        System.out.println("****************************************");
        if (Utilidades.seguir("¿Desea introducir manualmente los datos de un vuelo comercial?")) {
            VueloComercial vuelo3 = new VueloComercial();
            vuelo3.rellenarInfoVuelo();
            vuelo3.mostrarInfoVuelo();
        }
        System.out.println("****************************************");
        System.out.println("****************************************");
        if (Utilidades.seguir("¿Desea introducir manualmente los datos de un vuelo de carga?")) {
            VueloCarga vuelo4 = new VueloCarga();
            vuelo4.rellenarInfoVuelo();
            vuelo4.mostrarInfoVuelo();
        }
    }
}
