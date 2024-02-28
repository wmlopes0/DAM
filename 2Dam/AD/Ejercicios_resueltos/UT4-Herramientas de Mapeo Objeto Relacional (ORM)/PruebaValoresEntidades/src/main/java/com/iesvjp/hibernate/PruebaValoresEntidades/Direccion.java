package com.iesvjp.hibernate.PruebaValoresEntidades;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Direccion {

	private String via;
	@Column(length = 5)
	private String codigoPostal;
	private String poblacion;
	private String provincia;

	// Constructores
	public Direccion() {
		super();

	}

	public Direccion(String via, String codigoPostal, String poblacion, String provincia) {
		super();
		this.via = via;
		this.codigoPostal = codigoPostal;
		this.poblacion = poblacion;
		this.provincia = provincia;
	}

	// Getter y Setter
	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

}
