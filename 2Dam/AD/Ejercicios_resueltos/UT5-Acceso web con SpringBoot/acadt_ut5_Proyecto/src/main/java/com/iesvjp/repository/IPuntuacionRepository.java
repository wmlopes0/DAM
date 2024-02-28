package com.iesvjp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iesvjp.model.Puntuacion;

@Repository("puntuacionRepository")
public interface IPuntuacionRepository extends JpaRepository<Puntuacion, Long> {
	
	// Método para buscar una puntuación por su ID
	public Puntuacion findById(int id);
}
