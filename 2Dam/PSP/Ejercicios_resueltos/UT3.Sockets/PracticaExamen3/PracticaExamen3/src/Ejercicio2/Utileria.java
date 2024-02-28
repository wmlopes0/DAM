package Ejercicio2;

public class Utileria {
    public static String comprobarTemperatura(Habitacion habitacion) {
        String respuesta = "";
        if (habitacion.getTemperatura() > 30) {
            respuesta += "[Habitación " + habitacion.getNumHabitacion() + "] - Temperatura muy alta (" + habitacion.getTemperatura() + "º). Activamos aire acondicionado.";
        } else {
            if (habitacion.getTemperatura() < 15) {
                respuesta += "[Habitación " + habitacion.getNumHabitacion() + "] - Temperatura muy baja (" + habitacion.getTemperatura() + "º). Activamos la calefacción.";
            } else {
                if (habitacion.getTemperatura() == 24) {
                    respuesta += "[Habitación " + habitacion.getNumHabitacion() + "] - Temperatura ideal (" + habitacion.getTemperatura() + "º).";
                }
            }
        }
        return respuesta;
    }
}
