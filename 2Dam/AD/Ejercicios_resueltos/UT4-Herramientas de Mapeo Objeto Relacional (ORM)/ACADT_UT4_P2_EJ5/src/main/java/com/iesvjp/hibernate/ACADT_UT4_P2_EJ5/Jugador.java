package com.iesvjp.hibernate.ACADT_UT4_P2_EJ5;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="jugadores")
public class Jugador {

	@Id
	private int dorsal;
	private String nombre;
	private String apellidos;
	private String nacionalidad;
	private String posicion;
	@ManyToOne
	@JoinColumn(name = "equipo")
	private Equipo equipo;

	// Constructores
	public Jugador() {
		super();
	}

	public Jugador(int dorsal, String nombre, String apellidos, String nacionalidad, String posicion, Equipo equipo) {
		super();
		this.dorsal = dorsal;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nacionalidad = nacionalidad;
		this.posicion = posicion;
		this.equipo = equipo;
	}

	// Getter y Setter
	public int getDorsal() {
		return dorsal;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

}
