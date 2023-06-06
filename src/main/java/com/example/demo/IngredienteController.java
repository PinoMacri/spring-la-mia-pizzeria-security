package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class IngredienteController {
	@Autowired
	private IngredienteService ingredienteService;

	@GetMapping("/ingredienti")
	public String index(Model model) {
		List<Ingrediente> ingredienti = ingredienteService.findAll();

		if (!ingredienti.isEmpty()) {
			model.addAttribute("ingredienti", ingredienti);
		} else {
			model.addAttribute("message", "Non ci sono ingredienti");
		}

		return "index-ingredienti";
	}

	@GetMapping("ingrediente/create")
	public String create(Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
		return "create-ingrediente";
	}

	@PostMapping("/ingrediente/store")
	public String store(Model model, @Valid @ModelAttribute Ingrediente ingrediente, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			for (ObjectError err : bindingResult.getAllErrors()) {
				System.err.println("errore: " + err.getDefaultMessage());
			}
			model.addAttribute("ingrediente", ingrediente);
			model.addAttribute("errors", bindingResult);
			return "create-ingrediente";
		}
		ingredienteService.save(ingrediente);
		return "redirect:/ingredienti";
	}
	
	@GetMapping("/ingrediente/delete/{id}")
	public String delete(@PathVariable int id) {
		Optional<Ingrediente> ingredienteOpt = ingredienteService.findById(id);
		Ingrediente ingrediente = ingredienteOpt.get();
		ingredienteService.delete(ingrediente);
		return "redirect:/ingredienti";
	}

}
