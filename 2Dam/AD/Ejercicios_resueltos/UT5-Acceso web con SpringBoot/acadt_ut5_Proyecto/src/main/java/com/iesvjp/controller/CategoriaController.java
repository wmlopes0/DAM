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

import com.iesvjp.model.Categoria;
import com.iesvjp.service.ICategoriaService;

@Controller
@RequestMapping("/admin/categoria")
public class CategoriaController {

	// Inyección de dependencias para el servicio de categorías
	@Autowired
	@Qualifier("categoriaService")
	private ICategoriaService categoriaService;

	// Constantes para los nombres de las vistas
	private static final String LISTADO_CATEGORIAS = "admin/list-categoria";
	private static final String FORMULARIO_CATEGORIA = "admin/form-categoria";

	// Método GET para listar todas las categorías
	@GetMapping("/")
	public ModelAndView listadoCategorias() {
		ModelAndView mav = new ModelAndView(LISTADO_CATEGORIAS);
		// Agrega una lista de todas las categorias al modelo
		mav.addObject("listaCategorias", categoriaService.listAllCategorias());
		return mav;
	}
	
	// Método GET para mostrar el formulario de nueva categoría
	@GetMapping("/nueva")
	public String showCategoriaForm(Model model) {
		Categoria categoria = new Categoria();
		model.addAttribute("categoriaModel", categoria);
		return FORMULARIO_CATEGORIA;
	}
	
	// Método GET para eliminar una categoría por su ID
	@GetMapping("/borrar/{id}")
	private ModelAndView removeCategoria(@PathVariable("id") int id) {
		//Llamo al servicio para eliminar la categoria
		categoriaService.removeCategoria(id);
		//Devuelvo el mav del método listadoCategorias()
		return listadoCategorias();
	}
	
	// Método GET para editar una categoría.
	@GetMapping("/editar/{id}")
	private String editarCategoria(@PathVariable("id") Integer id, Model model) {
		//Busco el objeto categoria por su id en la bd y se la paso al modelo
	    Categoria categoria = categoriaService.findCategoriaById(id);
	    model.addAttribute("categoriaModel", categoria);
	    return FORMULARIO_CATEGORIA;
	}
	
	// Método POST para agregar o actualizar una categoría
	@PostMapping("/addCategoria")
	public String addOrUpdateCategoria(@ModelAttribute("categoriaModel") Categoria categoriaModel, RedirectAttributes ra) {
		if (categoriaService.addCategoria(categoriaModel) != null) {
			ra.addFlashAttribute("result", 1);
		} else {
			ra.addFlashAttribute("result", 0);
		}
		//Redirecciono
		return "redirect:/admin/categoria/";
	}

}
