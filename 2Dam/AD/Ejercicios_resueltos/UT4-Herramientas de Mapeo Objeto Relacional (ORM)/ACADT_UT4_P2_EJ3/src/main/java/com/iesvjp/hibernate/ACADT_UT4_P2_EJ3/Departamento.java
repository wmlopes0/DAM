package com.iesvjp.hibernate.ACADT_UT4_P2_EJ3;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the departamentos database table.
 * 
 */
@Entity
@Table(name = "departamentos")
@NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d")
public class Departamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dept_no")
	private int deptNo;

	private String dnombre;

	private String loc;

	// bi-directional many-to-one association to Empleado
	@OneToMany(mappedBy = "departamento")
	private List<Empleado> empleados;

	public Departamento() {
	}

	public int getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public String getDnombre() {
		return this.dnombre;
	}

	public void setDnombre(String dnombre) {
		this.dnombre = dnombre;
	}

	public String getLoc() {
		return this.loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public List<Empleado> getEmpleados() {
		return this.empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public Empleado addEmpleado(Empleado empleado) {
		getEmpleados().add(empleado);
		empleado.setDepartamento(this);

		return empleado;
	}

	public Empleado removeEmpleado(Empleado empleado) {
		getEmpleados().remove(empleado);
		empleado.setDepartamento(null);

		return empleado;
	}

	public void mostrar() {
		System.out.println("NumDep: " + deptNo);
		System.out.println("Nombre: " + dnombre);
		System.out.println("Localización: " + loc);
		System.out.println("Empleados: ");
		for (Empleado empleado : empleados) {
			System.out.println("\t -" + empleado.getApellido());
		}
	}
}