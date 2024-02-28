package com.iesvjp.service;

import java.util.List;

import com.iesvjp.model.Producto;

public interface IProductoService {
	
	public Producto addProducto(Producto productoModel);

	public List<Producto> listAllProductos();

	public Producto findProductoById(int id);
	
	//Al añadir en el repository el método con la consulta personalizada también tengo que añadirlo aquí
	public List<Producto> findProductosByCategoriaId(int categoriaId);
	
	public List<Producto> listAllProductByDiscount();

	public void removeProducto(int id);
}
