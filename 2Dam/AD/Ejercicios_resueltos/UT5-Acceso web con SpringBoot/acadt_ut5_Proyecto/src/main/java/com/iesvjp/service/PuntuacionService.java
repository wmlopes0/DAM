package com.iesvjp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.iesvjp.model.Puntuacion;
import com.iesvjp.repository.IPuntuacionRepository;

@Service("PuntuacionService")
public class PuntuacionService implements IPuntuacionService {

	// Inyección del repositorio de puntuaciones
	@Autowired
	@Qualifier("puntuacionRepository")
	private IPuntuacionRepository puntuacionRepository;

	// Guarda o actualiza una puntuación en la base de datos
	@Override
	public Puntuacion addPuntuacion(Puntuacion puntuacionModel) {
		Puntuacion puntuacion = puntuacionRepository.save(puntuacionModel);
		return puntuacion;
	}

	// Devuelve una lista de todas las puntuaciones
	@Override
	public List<Puntuacion> listAllPuntuaciones() {
		List<Puntuacion> puntuaciones = puntuacionRepository.findAll();
		return puntuaciones;
	}

	// Encuentra una puntuación específica por su ID
	@Override
	public Puntuacion findPuntuacionById(int id) {
		return puntuacionRepository.findById(id);
	}

	// Elimina una puntuación específica por su ID
	@Override
	public void removePuntuacion(int id) {
		Puntuacion puntuacion = findPuntuacionById(id);
		if (puntuacion != null) {
			puntuacionRepository.delete(puntuacion);
		}
	}
}
