package org.example;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.sql.SQLOutput;

public class ArticuloPojo {

    @BsonProperty(value = "_id")
    private ObjectId id;
    private Integer codigo;
    private String denominacion;
    private Integer pvp;
    private String categoria;
    private Integer uv;
    private Integer stock;

    //Constructores
    public ArticuloPojo() {
    }

    public ArticuloPojo(ObjectId id, Integer codigo, String denominacion, Integer pvp, String categoria, Integer uv, Integer stock) {
        this.id = id;
        this.codigo = codigo;
        this.denominacion = denominacion;
        this.pvp = pvp;
        this.categoria = categoria;
        this.uv = uv;
        this.stock = stock;
    }

    //Getter y Setter
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

    //ToString
    @Override
    public String toString() {
        return "Articulo [id=" + id + ", codigo=" + codigo + ", denominacion=" + denominacion + ", pvp=" + pvp
                + ", categoria=" + categoria + ", uv=" + uv + ", stock=" + stock + "]";
    }
}
