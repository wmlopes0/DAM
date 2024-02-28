package com.iesvjp.service;

import java.util.List;

import com.iesvjp.model.Puntuacion;

public interface IPuntuacionService {

	public Puntuacion addPuntuacion(Puntuacion puntuacionModel);

	public List<Puntuacion> listAllPuntuaciones();

	public Puntuacion findPuntuacionById(int id);

	public void removePuntuacion(int id);
}
