package com.iesvjp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iesvjp.model.Producto;
import com.iesvjp.service.ICategoriaService;
import com.iesvjp.service.IProductoService;

@Controller
@RequestMapping("/admin/producto")
public class ProductoController {
	
	// Inyección de dependencias para el servicio de productos
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	
	// Inyección de dependencias para el servicio de categorias
	@Autowired
	@Qualifier("categoriaService")
	private ICategoriaService categoriaService;

	// Constantes para los nombres de las vistas
	private static final String LISTADO_PRODUCTOS = "admin/list-producto";
	private static final String FORMULARIO_PRODUCTO = "admin/form-producto";

	// Método GET para listar todos los productos
	@GetMapping("/")
	public ModelAndView listadoProductos() {
		ModelAndView mav = new ModelAndView(LISTADO_PRODUCTOS);
		mav.addObject("listaProductos", productoService.listAllProductos());
		return mav;
	}

	// Método GET para mostrar el formulario de nuevo producto
	@GetMapping("/nuevo")
	public String showProductoForm(Model model) {
		Producto producto = new Producto();
		model.addAttribute("productoModel", producto);
		model.addAttribute("listaCategorias",categoriaService.listAllCategorias());
		return FORMULARIO_PRODUCTO;
	}

	// Método GET para eliminar un producto por su ID
	@GetMapping("/borrar/{id}")
	private ModelAndView removeProducto(@PathVariable("id") int id) {
		productoService.removeProducto(id);
		return listadoProductos();
	}
	
	// Método GET para editar un producto.
	@GetMapping("/editar/{id}")
	private String editarProducto(@PathVariable("id") Integer id, Model model) {
		//Busco el producto por su id en la bd y se la paso al modelo
		Producto producto= productoService.findProductoById(id);
	    model.addAttribute("productoModel", producto);
	    //Paso también el listado categorias
		model.addAttribute("listaCategorias",categoriaService.listAllCategorias());
	    return FORMULARIO_PRODUCTO;
	}

	// Método POST para agregar o actualizar un producto
	@PostMapping("/addProducto")
	public String addOrUpdateProducto(@ModelAttribute("productoModel") Producto productoModel, RedirectAttributes ra) {
		if (productoService.addProducto(productoModel) != null) {
			ra.addFlashAttribute("result", 1);
		} else {
			ra.addFlashAttribute("result", 0);
		}
		return "redirect:/admin/producto/";
	}
}
