package com.iesvjp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.iesvjp.model.Producto;
import com.iesvjp.repository.IProductoRepository;

@Service("productoService")
public class ProductoService implements IProductoService {

	// Inyección del repositorio de productos
	@Autowired
	@Qualifier("productoRepository")
	private IProductoRepository productoRepository;

	// Guarda o actualiza un producto en la base de datos
	@Override
	public Producto addProducto(Producto productoModel) {
		Producto producto = productoRepository.save(productoModel);
		return producto;
	}

	// Devuelve una lista de todos los productos
	@Override
	public List<Producto> listAllProductos() {
		List<Producto> productos = productoRepository.findAll();
		return productos;
	}

	// Devuelve una lista de todos los productos ordenados por mayor a menor
	// descuento
	@Override
	public List<Producto> listAllProductByDiscount() {
		List<Producto> productos = productoRepository.listAllProductByDiscount();
		return productos;
	}

	// Encuentra un producto específico por su ID
	@Override
	public Producto findProductoById(int id) {
		return productoRepository.findById(id);
	}

	// Busca productos basados en el ID de su categoría
	@Override
	public List<Producto> findProductosByCategoriaId(int categoriaId) {
		return productoRepository.findByCategoriaId(categoriaId);
	}

	// Elimina un producto específico por su ID
	@Override
	public void removeProducto(int id) {
		Producto producto = findProductoById(id);
		if (producto != null) {
			productoRepository.delete(producto);
		}
	}

}
