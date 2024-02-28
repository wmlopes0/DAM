package com.iesvjp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.iesvjp.model.Categoria;
import com.iesvjp.repository.ICategoriaRepository;

@Service("categoriaService")
public class CategoriaService implements ICategoriaService {

	// Inyección del repositorio de categorías
	@Autowired
	@Qualifier("categoriaRepository")
	private ICategoriaRepository categoriaRepository;

	// Guarda o actualiza una categoría en la base de datos
	@Override
	public Categoria addCategoria(Categoria categoriaModel) {
		Categoria categoria = categoriaRepository.save(categoriaModel);
		return categoria;
	}

	// Devuelve una lista de todas las categorías
	@Override
	public List<Categoria> listAllCategorias() {
		List<Categoria> categorias = categoriaRepository.findAll();
		return categorias;
	}

	// Encuentra una categoría específica por su ID
	@Override
	public Categoria findCategoriaById(int id) {
		return categoriaRepository.findById(id);
	}

	// Elimina una categoría específica por su ID
	@Override
	public void removeCategoria(int id) {
		Categoria categoria = findCategoriaById(id);
		if (categoria != null) {
			categoriaRepository.delete(categoria);
		}
	}

}
