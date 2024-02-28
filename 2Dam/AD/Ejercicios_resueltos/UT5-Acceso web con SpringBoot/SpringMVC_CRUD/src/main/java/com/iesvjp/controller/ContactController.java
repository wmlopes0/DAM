package com.iesvjp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iesvjp.model.Contact;
import com.iesvjp.service.IContactService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Controller
@RequestMapping("/contacts")
public class ContactController {
	@Autowired
	@Qualifier("contactService")
	private IContactService contacService;

	private static final Log LOG = LogFactory.getLog(ContactController.class);

	@GetMapping(value = { "", "/" })
	private ModelAndView showContacts() {
		ModelAndView mav = new ModelAndView("contacts");
		mav.addObject("contactsModel", contacService.listAllContacts());
		LOG.info("METHOD: showContacts -- PARAMS: " + mav.getModel());
		return mav;
	}

	// localhost:9000/contacts/contacform
	@GetMapping("/contactform")
	private String showContactForm(@RequestParam(name = "id", required = false) Integer id, Model model) {
		Contact contact = new Contact();
		// si el id es distinto de 0, el contacto ya existe y se va a realizar una
		// modificación/eliminación
		if (id == null)
			id = 0;
		if (id != 0) {
			contact = contacService.findContactById(id);
		}
		model.addAttribute("contactModel", contact);
		return "contactform";
	}

	// localhost:9000/contacts/contacform
	@PostMapping("/addcontact")
	private String addContact(@ModelAttribute("contactModel") Contact contacModel, RedirectAttributes ra) {
		LOG.info("METHOD: addContact -- PARAMS: " + contacModel);
		if (contacService.addContact(contacModel) != null) {
			ra.addFlashAttribute("result", 1);
		} else {
			ra.addFlashAttribute("result", 0);
		}
		return "redirect:/contacts";
	}

	@GetMapping("/removecontact")
	private ModelAndView removeContact(@RequestParam(name = "id", required = true) int id) {
		contacService.removeContact(id);
		return showContacts();
	}

	@GetMapping("/cancel")
	private ModelAndView cancel(Model model) {
		return showContacts();
	}
}
