package com.iesvjp.mongodb.EjemplosMongoDB;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class ArticuloPOJO {

	@BsonProperty(value = "_id")
	private ObjectId id;
	private Integer codigo;
	private String denominacion;
	private Integer pvp;
	private String categoria;
	private Integer uv;
	private Integer stock;
	
	public ArticuloPOJO() {
		
	}

	public ArticuloPOJO(ObjectId id, Integer codigo, String denominacion, Integer pvp, String categoria, Integer uv,
			Integer stock) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.denominacion = denominacion;
		this.pvp = pvp;
		this.categoria = categoria;
		this.uv = uv;
		this.stock = stock;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	// Resto de Getters y Setters

	public Integer getPvp() {
		return pvp;
	}

	public void setPvp(Integer pvp) {
		this.pvp = pvp;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Integer getUv() {
		return uv;
	}

	public void setUv(Integer uv) {
		this.uv = uv;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	// Resto de Getts y Setters

	@Override
	public String toString() {
		return "ArticuloBSON [id=" + id + ", codigo=" + codigo + ", denominacion=" + denominacion + ", pvp=" + pvp
				+ ", categoria=" + categoria + ", uv=" + uv + ", stock=" + stock + "]";
	}

}
