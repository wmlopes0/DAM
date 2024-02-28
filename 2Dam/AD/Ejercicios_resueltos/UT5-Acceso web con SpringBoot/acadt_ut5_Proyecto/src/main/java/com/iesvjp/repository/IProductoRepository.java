package com.iesvjp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iesvjp.model.Producto;

@Repository("productoRepository")
public interface IProductoRepository extends JpaRepository<Producto, Long> {
	
	// Método para buscar un producto por su ID
	public Producto findById(int id);
	
	// Consulta personalizada para buscar productos por ID de categoría
	@Query("select p from Producto p where p.categoria.id =?1")
	public List<Producto> findByCategoriaId(int categoriaId);
	
	//Consulta personalizada para devolver la lista de productos ordenada por descuento
	@Query("select p from Producto p order by p.descuento DESC")
	public List<Producto> listAllProductByDiscount();
}
