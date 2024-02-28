package ejercicio4;

import java.util.List;

public class ListaPersonajes {

	private List<String> hobbits;
	private List<String> elfos;
	private List<String> enanos;
	private List<String> hombres;
	private List<String> magos;
	private List<String> villanos;

	// Constructor
	public ListaPersonajes(List<String> hobbits, List<String> elfos, List<String> enanos, List<String> hombres,
			List<String> magos, List<String> villanos) {
		super();
		this.hobbits = hobbits;
		this.elfos = elfos;
		this.enanos = enanos;
		this.hombres = hombres;
		this.magos = magos;
		this.villanos = villanos;
	}

	// Getter y setter
	public List<String> getHobbits() {
		return hobbits;
	}

	public void setHobbits(List<String> hobbits) {
		this.hobbits = hobbits;
	}

	public List<String> getElfos() {
		return elfos;
	}

	public void setElfos(List<String> elfos) {
		this.elfos = elfos;
	}

	public List<String> getEnanos() {
		return enanos;
	}

	public void setEnanos(List<String> enanos) {
		this.enanos = enanos;
	}

	public List<String> getHombres() {
		return hombres;
	}

	public void setHombres(List<String> hombres) {
		this.hombres = hombres;
	}

	public List<String> getMagos() {
		return magos;
	}

	public void setMagos(List<String> magos) {
		this.magos = magos;
	}

	public List<String> getVillanos() {
		return villanos;
	}

	public void setVillanos(List<String> villanos) {
		this.villanos = villanos;
	}

	public void mostrar() {
		System.out.println("HOBBITS: ");
		for (String s : hobbits) {
			System.out.print(s + ",");
		}
		System.out.println("\nELFOS: ");
		for (String s : elfos) {
			System.out.print(s + ",");
		}
		System.out.println("\nENANOS: ");
		for (String s : enanos) {
			System.out.print(s + ",");
		}
		System.out.println("\nHOMBRES: ");
		for (String s : hombres) {
			System.out.print(s + ",");
		}
		System.out.println("\nMAGOS: ");
		for (String s : magos) {
			System.out.print(s + ",");
		}
		System.out.println("\nVILLANOS: ");
		for (String s : villanos) {
			System.out.print(s + ",");
		}
	}
}
