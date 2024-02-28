package com.iesvjp.psp;

import java.io.Serializable;

public class Habitacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4697976740074562370L;
	private int num_habitacion;
	private int temp;

	public Habitacion() {
		this.num_habitacion = 0;
		this.temp = 0;
	}

	public Habitacion(int num_habitacion) {
		this.num_habitacion = num_habitacion;
		this.temp = 0;
	}

	public int getNum_habitacion() {
		return num_habitacion;
	}

	public void setNum_habitacion(int num_habitacion) {
		this.num_habitacion = num_habitacion;
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
		return "Habitacion [num_habitacion=" + num_habitacion + ", temp=" + temp + "]";
	}

}
