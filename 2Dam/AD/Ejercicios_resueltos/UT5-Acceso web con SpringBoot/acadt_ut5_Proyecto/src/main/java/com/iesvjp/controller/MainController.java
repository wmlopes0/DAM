package com.iesvjp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iesvjp.model.Producto;
import com.iesvjp.service.ICategoriaService;
import com.iesvjp.service.IProductoService;


@Controller
@RequestMapping("/")
public class MainController {
	
	// Inyección de dependencias para el servicio de productos
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	
	// Inyección de dependencias para el servicio de categorias
	@Autowired
	@Qualifier("categoriaService")
	private ICategoriaService categoriaService;

	// Constantes para definir los nombres de las vistas
	private static final String INDEX = "index";
	private static final String DETAIL = "detail";
	
	// Manejador GET para ("/"). Muestra la página principal
	@GetMapping("/")
	public ModelAndView paginaPrincipal() {
		ModelAndView mav = new ModelAndView(INDEX);
		// Agrega una lista de todos los productos ordenados por descuento y categorias al modelo
		mav.addObject("listaProductos", productoService.listAllProductByDiscount());
		mav.addObject("listaCategorias",categoriaService.listAllCategorias());
		return mav;
	}
	
	// Manejador GET para "/{id}". Muestra detalles de un producto específico
	@GetMapping("/{id}")
	private String detalleProducto(@PathVariable("id") Integer id, Model model) {
		// Busca el producto por su ID y lo agrega al modelo
		Producto producto = productoService.findProductoById(id);
	    model.addAttribute("productoModel", producto);
	    return DETAIL;
	}
	
	// Manejador GET para "/categoria/{id}". Muestra productos por categoría
	@GetMapping("/categoria/{id}")
	public ModelAndView productosPorCategoria(@PathVariable("id") Integer id) {
		ModelAndView mav = new ModelAndView(INDEX);
		// Agrega una lista de productos filtrados por categoría al modelo
		mav.addObject("listaProductos", productoService.findProductosByCategoriaId(id));
		mav.addObject("listaCategorias",categoriaService.listAllCategorias());
	    return mav;
	}

	
}
