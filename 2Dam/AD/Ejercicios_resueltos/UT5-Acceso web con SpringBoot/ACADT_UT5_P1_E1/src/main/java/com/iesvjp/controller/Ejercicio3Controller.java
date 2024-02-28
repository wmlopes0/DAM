package com.iesvjp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iesvjp.Usuario;

@Controller
@RequestMapping("/ejercicio3")
public class Ejercicio3Controller {
	
	private static final String EJERCICIO3_VIEW = "Ejercicio3";
	private static final String EJERCICIO3OPCION2_VIEW = "Ejercicio3Opcion2";
	
	@GetMapping("/request1")
	public ModelAndView request1(@ModelAttribute Usuario usuario) {
	    ModelAndView mav = new ModelAndView(EJERCICIO3_VIEW);
	    mav.addObject("usuario", usuario);
	    return mav;
	}
	
	@GetMapping("/request2")
	public ModelAndView request2(@ModelAttribute Usuario usuario) {
	    ModelAndView mav = new ModelAndView(EJERCICIO3OPCION2_VIEW);
	    mav.addObject("usuario", usuario);
	    return mav;
	}
}
