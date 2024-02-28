package com.iesvjp.psp;

import java.io.Serializable;

public class Paciente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8929540046804319860L;

	private int temperatura;
	private int frecuencia;
	private int presionSistolica;
	private int presionDiastolica;

	public Paciente() {
		this.temperatura = 0;
		this.frecuencia = 0;
		this.presionSistolica = 0;
		this.presionDiastolica = 0;

	}

	public Paciente(int temperatura, int frecuencia, int presionSistolica, int presionDiastolica) {
		this.temperatura = temperatura;
		this.frecuencia = frecuencia;
		this.presionSistolica = presionSistolica;
		this.presionDiastolica = presionDiastolica;
	}

	public int getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(int temperatura) {
		this.temperatura = temperatura;
	}

	public int getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(int frecuencia) {
		this.frecuencia = frecuencia;
	}

	public int getPresionSistolica() {
		return presionSistolica;
	}

	public void setPresionSistolica(int presionSistolica) {
		this.presionSistolica = presionSistolica;
	}

	public int getPresionDiastolica() {
		return presionDiastolica;
	}

	public void setPresionDiastolica(int presionDiastolica) {
		this.presionDiastolica = presionDiastolica;
	}

	public void actualizarConstantesVitales() {
		// ramdom(mayor-menor+1)+menor
		// le damos valores fuera del rango por que si no no salta el mensaje
		this.temperatura = (int) ((Math.random() * 10) + 35);
		this.frecuencia = (int) ((Math.random() * 51) + 55);
		this.presionSistolica = (int) ((Math.random() * 41) + 110);
		this.presionDiastolica = (int) ((Math.random() * 41) + 60);

	}

	@Override
	public String toString() {
		return "|| ConstantesVitales ||" + "\n" + " [temperatura : " + this.temperatura + "  ºC]" + "\n" + " [pulso : "
				+ this.frecuencia + " ppm]\n [sistólica : " + this.presionSistolica + " mm Hg]\n [diastólica : "
				+ this.presionDiastolica + " mm Hg]\n";
	}

}
