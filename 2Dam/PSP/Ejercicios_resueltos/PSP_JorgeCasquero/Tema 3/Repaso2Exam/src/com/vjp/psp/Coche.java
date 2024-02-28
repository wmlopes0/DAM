package com.vjp.psp;

import java.io.Serializable;

public class Coche implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String matricula;
	private String marca;
	private String modelo;

	private int anioMatricula;
	private int fechaUltimaITV;

	public Coche(String matricula, String marca, String modelo, int anio_matric) {
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;

		this.anioMatricula = anio_matric;
		this.anioMatricula = -1;
	}

	public Coche() {

	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAnioMatricula() {
		return anioMatricula;
	}

	public void setAnioMatricula(int anioMatricula) {
		this.anioMatricula = anioMatricula;
	}

	public int getFechaUltimaITV() {
		return fechaUltimaITV;
	}

	public void setFechaUltimaITV(int fechaUltimaITV) {
		this.fechaUltimaITV = fechaUltimaITV;
	}

	@Override
	public String toString() {
		return "Coche [matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", anioMatricula="
				+ anioMatricula + ", fechaUltimaITV=" + fechaUltimaITV + "]";
	}

}
