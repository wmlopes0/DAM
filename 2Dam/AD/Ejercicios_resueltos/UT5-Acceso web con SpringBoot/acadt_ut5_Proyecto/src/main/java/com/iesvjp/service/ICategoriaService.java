package com.iesvjp.service;

import java.util.List;

import com.iesvjp.model.Categoria;

public interface ICategoriaService {

	public Categoria addCategoria(Categoria categoriaModel);

	public List<Categoria> listAllCategorias();

	public Categoria findCategoriaById(int id);

	public void removeCategoria(int id);
}
