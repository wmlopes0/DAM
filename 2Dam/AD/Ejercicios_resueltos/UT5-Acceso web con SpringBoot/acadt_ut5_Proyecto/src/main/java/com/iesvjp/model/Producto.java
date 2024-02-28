package com.iesvjp.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "nombre", nullable = false, length = 512)
	private String nombre;

	@Column(name = "descripcion", length = 512)
	private String descripcion;

	@Column(name = "pvp", nullable = false)
	private float pvp;

	@Column(name = "descuento")
	private float descuento;

	@Column(name = "imagen", length = 512)
	private String imagen;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria_id", referencedColumnName = "id")
	private Categoria categoria;

	@OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
	private List<Puntuacion> puntuaciones;

	// Constructores
	public Producto() {

	}

	public Producto(Long id, String nombre, String descripcion, float pvp, float descuento, String imagen,
			Categoria categoria, List<Puntuacion> puntuaciones) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.pvp = pvp;
		this.descuento = descuento;
		this.imagen = imagen;
		this.categoria = categoria;
		this.puntuaciones = puntuaciones;
	}

	// Getter y Setter

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPvp() {
		return pvp;
	}

	public void setPvp(float pvp) {
		this.pvp = pvp;
	}

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Puntuacion> getPuntuaciones() {
		return puntuaciones;
	}

	public void setPuntuaciones(List<Puntuacion> puntuaciones) {
		this.puntuaciones = puntuaciones;
	}

	// Método para obtener la puntuacion media si hubiera mas de una puntuacion para
	// un producto
	public int obtenerPuntuacion() {
		int puntuacionFinal = 0;

		for (Puntuacion puntuacion : puntuaciones) {
			puntuacionFinal += puntuacion.getPuntuacion();
		}

		return puntuacionFinal / puntuaciones.size();
	}
}
