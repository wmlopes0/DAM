package com.iesvjp.hibernate.ACADT_UT4_P2_EJ3;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the empleados database table.
 * 
 */
@Entity
@Table(name = "empleados")
@NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
public class Empleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_no")
	private int empNo;

	private String apellido;

	private Float comision;

	private int dir;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_alt")
	private Date fechaAlt;

	private String oficio;

	private float salario;

	// bi-directional many-to-one association to Departamento
	@ManyToOne
	@JoinColumn(name = "dept_no")
	private Departamento departamento;

	public Empleado() {
	}

	public int getEmpNo() {
		return this.empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public float getComision() {
		return this.comision;
	}

	public void setComision(float comision) {
		this.comision = comision;
	}

	public int getDir() {
		return this.dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public Date getFechaAlt() {
		return this.fechaAlt;
	}

	public void setFechaAlt(Date fechaAlt) {
		this.fechaAlt = fechaAlt;
	}

	public String getOficio() {
		return this.oficio;
	}

	public void setOficio(String oficio) {
		this.oficio = oficio;
	}

	public float getSalario() {
		return this.salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public Departamento getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return "Empleado [empNo=" + empNo + ", apellido=" + apellido + ", comision=" + comision + ", dir=" + dir
				+ ", fechaAlt=" + fechaAlt + ", oficio=" + oficio + ", salario=" + salario + ", departamento="
				+ departamento + "]";
	}

}