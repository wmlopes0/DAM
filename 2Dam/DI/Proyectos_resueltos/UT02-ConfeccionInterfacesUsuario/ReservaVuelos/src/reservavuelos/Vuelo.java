package reservavuelos;

/**
 *
 * @author Walter
 */
public interface Vuelo {

    //Debe devolver true si la reserva fue exitosa y false si no hay asientos disponibles o si el pasajero no cumple con ciertos requisitos
    public boolean reservarAsiento(Pasajero pasajero);

    //Debe mostrar información básica sobre el vuelo, como el número de vuelo, la hora de salida...
    public void mostrarInfoVuelo();
}
