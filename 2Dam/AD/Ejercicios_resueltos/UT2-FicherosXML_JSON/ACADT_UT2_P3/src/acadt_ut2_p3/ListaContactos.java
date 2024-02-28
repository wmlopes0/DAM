package acadt_ut2_p3;

import java.util.ArrayList;
import java.util.List;

public class ListaContactos {
	private List<Contacto> lista = new ArrayList<>();

	public ListaContactos() {

	}

	public void add(Contacto con) {
		lista.add(con);
	}

	public List<Contacto> getListaContactos() {
		return lista;
	}

	public void mostrar() {
		for (Contacto contacto : lista) {
			contacto.mostrarContacto();
		}
	}
}
