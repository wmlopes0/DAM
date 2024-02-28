package com.iesvjp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/helloWorld")
public class HelloWorldController2 {
	private static final String HELLOWORLD_VIEW = "helloWorld2";

	// FORMA 1
	//localhost:9000/helloWorld/request1?nm=Walter&lastn=Martin%20Lopes&yrs=25
	@GetMapping("/request1")
	public ModelAndView request1(@RequestParam(name = "nm", required = false, defaultValue = "World") String name,
			@RequestParam(name = "lastn", required = false, defaultValue = "") String lastname,
			@RequestParam(name = "yrs", required = false, defaultValue = "") String years) {
		ModelAndView mav = new ModelAndView(HELLOWORLD_VIEW);
		mav.addObject("name_model", name);
		mav.addObject("lastname_model", lastname);
		mav.addObject("years_model", years);
		return mav;
	}

	// FORMA 2
	// localhost:9000/helloWorld/request2/Walter/MartinLopes/25
	@GetMapping("/request2/{nm}/{lastn}/{yrs}")
	public ModelAndView request2(@PathVariable("nm") String name,@PathVariable("lastn") String lastname,@PathVariable("yrs") String years) {
		ModelAndView mav = new ModelAndView(HELLOWORLD_VIEW);
		mav.addObject("name_model", name);
		mav.addObject("lastname_model", lastname);
		mav.addObject("years_model", years);
		return mav;
	}
}
