package com.iesvjp.hibernate.ACADT_UT4_P2_EJ5;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "equipos")
public class Equipo {

	@Id
	private String nombre;
	private String categoria;
	private String pais;
	private String estadio;
	@OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Jugador> jugadores;

	// Constructores
	public Equipo() {
		super();

	}

	public Equipo(String nombre, String categoria, String pais, String estadio, List<Jugador> jugadores) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
		this.pais = pais;
		this.estadio = estadio;
		this.jugadores = jugadores;
	}

	// Geter y Setter
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEstadio() {
		return estadio;
	}

	public void setEstadio(String estadio) {
		this.estadio = estadio;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

}
