package acadt_ut2_p4;

import com.google.gson.annotations.SerializedName;

public class SerializeEmpleado {

	@SerializedName("empleado")
	private Empleado empleado;

	public SerializeEmpleado(Empleado empleado) {
		super();
		this.empleado = empleado;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

}
