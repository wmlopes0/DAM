package ejercicioo1examen;

import java.io.Serializable;

public class Coche implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String matricula;
	private String marca;
	private String modelo;
	
	private int anio_matric;
	private int fecha_ul_ITV;
	
	public Coche(String matricula, String marca, String modelo,  int anio_matric) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		
		this.anio_matric = anio_matric;
		this.anio_matric=-1;
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



	public int getAnio_matric() {
		return anio_matric;
	}

	public void setAnio_matric(int anio_matric) {
		this.anio_matric = anio_matric;
	}

	public int getFecha_ul_ITV() {
		return fecha_ul_ITV;
	}

	public void setFecha_ul_ITV(int fecha_ul_ITV) {
		this.fecha_ul_ITV = fecha_ul_ITV;
	}

	@Override
	public String toString() {
		return "Coche [matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", anio_matric="
				+ anio_matric + ", fecha_ul_ITV=" + fecha_ul_ITV + "]";
	}
	
	
}