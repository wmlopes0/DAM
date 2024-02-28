package com.iesvjp.psp;

import java.io.Serializable;

public class Habitacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int numero = 0;
	private int numHabitacion;
	private int temp;

	public Habitacion() {
		numero++;
		this.numHabitacion = numero;
		this.temp = 0;

	}

	public int getNumHabitacion() {
		return numHabitacion;
	}

	public void setNumHabitacion(int numHabitacion) {
		this.numHabitacion = numHabitacion;
	}

	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	// metodo que genera un numero aleatorio para marcar la temperatura
	public void generarTemperatura() {
		// generamos los valores fuera de los rangos maximos y minimos : 30 y ºC usamos
		// el metodo get
		this.temp = (int) (Math.random() * (35 - 10) + 10);

	}

	@Override
	public String toString() {
		return "Habitacion [num_habitacion=" + numHabitacion + ", temp=" + temp + "]";
	}

}
