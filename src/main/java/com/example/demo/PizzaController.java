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
public class PizzaController {
	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private OffertaService offertaService;
	@Autowired
	private IngredienteService ingredienteService;

	@GetMapping("/")
	public String home(Model model) {
		return "home";
	}

	@GetMapping("/pizze")
	public String index(Model model) {
		Optional<List<Pizza>> optionalPizze = Optional.of(pizzaService.findAll());

		if (optionalPizze.isPresent()) {
			model.addAttribute("pizze", optionalPizze.get());
		} else {
			model.addAttribute("message", "Non ci sono pizze");
		}

		return "index";
	}

	@GetMapping("/pizze/{id}")
	public String getPizza(Model model, @PathVariable("id") int id) {
		Pizza pizza = getPizzaById(id);
		Optional<Offerta> offerte = offertaService.getOfferteByPizzaId(id);
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		model.addAttribute("pizza", pizza);
		model.addAttribute("offerte", offerte);
		model.addAttribute("ingredienti", ingredienti);
		return "show";
	}

	private Pizza getPizzaById(int id) {
		List<Pizza> pizze = pizzaService.findAll();
		Pizza singolaPizza = null;
		for (Pizza pizza : pizze)
			if (pizza.getId() == id)
				singolaPizza = pizza;
		return singolaPizza;
	}

	@PostMapping("/pizze/nome")
	public String getPizzaNome(@RequestParam(required = false) String nome, Model model) {
		List<Pizza> pizze = pizzaService.findByNome(nome);
		model.addAttribute("pizze", pizze);
		model.addAttribute("nome", nome);
		return "index";
	}

	@GetMapping("pizze/create")
	public String create(Model model) {
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		model.addAttribute("pizza", new Pizza());
		model.addAttribute("ingredienti", ingredienti);

		return "create";
	}

	@PostMapping("/pizze/store")
	public String store(Model model, @Valid @ModelAttribute Pizza pizza, BindingResult bindingResult,
			@RequestParam("ingredientiSelezionati") List<Integer> ingredientiSelezionati) {
		if (bindingResult.hasErrors()) {
			for (ObjectError err : bindingResult.getAllErrors()) {
				System.err.println("errore: " + err.getDefaultMessage());
			}
			model.addAttribute("pizza", pizza);
			model.addAttribute("errors", bindingResult);
			return "create";
		}

		// Recupera gli oggetti Ingrediente corrispondenti agli ID selezionati
		List<Ingrediente> ingredienti = ingredienteService.findByIds(ingredientiSelezionati);

		// Imposta gli ingredienti selezionati nella pizza
		pizza.setIngredienti(ingredienti);

		pizzaService.save(pizza);
		return "redirect:/pizze";
	}

	@GetMapping("/pizze/edit/{id}")
	public String edit(Model model, @PathVariable int id) {
		Optional<Pizza> pizzaOpt = pizzaService.findById(id);
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		Pizza pizza = pizzaOpt.get();
		model.addAttribute("pizza", pizza);
		model.addAttribute("ingredienti", ingredienti);
		return "edit";
	}

	@PostMapping("/pizze/update/{id}")
	public String update(
	        Model model,
	        @PathVariable int id,
	        @Valid @ModelAttribute("pizza") Pizza pizza,
	        BindingResult bindingResult,
	        @RequestParam(value = "ingredientiSelezionati", required = false) List<Integer> ingredientiSelezionati) {
	    if (bindingResult.hasErrors()) {
	        for (ObjectError err : bindingResult.getAllErrors()) {
	            System.err.println("errore: " + err.getDefaultMessage());
	        }
	        model.addAttribute("errors", bindingResult);
	        return "edit";
	    }

	    Pizza existingPizza = getPizzaById(id);
	    if (existingPizza == null) {
	        return "redirect:/pizze";
	    }

	    existingPizza.setNome(pizza.getNome());
	    existingPizza.setDescrizione(pizza.getDescrizione());
	    existingPizza.setFoto(pizza.getFoto());
	    existingPizza.setPrezzo(pizza.getPrezzo());

	    if (ingredientiSelezionati != null) {
	        List<Ingrediente> ingredienti = ingredienteService.findByIds(ingredientiSelezionati);
	        existingPizza.setIngredienti(ingredienti);
	    } else {
	        // Se non sono stati selezionati ingredienti, rimuovi tutti gli ingredienti
	        existingPizza.getIngredienti().clear();
	    }

	    pizzaService.save(existingPizza);
	    return "redirect:/pizze";
	}


	@GetMapping("/pizze/delete/{id}")
	public String delete(@PathVariable int id) {
		Optional<Pizza> pizzaOpt = pizzaService.findById(id);
		Pizza pizza = pizzaOpt.get();
		pizzaService.delete(pizza);
		return "redirect:/pizze";
	}

	@GetMapping("/pizze/{pizzaId}/offerte/new")
	public String showNewOffertaForm(@PathVariable int pizzaId, Model model) {
		Optional<Pizza> pizzaOptional = pizzaService.findById(pizzaId);

		if (pizzaOptional.isPresent()) {
			Pizza pizza = pizzaOptional.get();
			Offerta offerta = new Offerta();
			model.addAttribute("pizza", pizza);
			model.addAttribute("offerta", offerta);
			return "create-offerta";
		} else {

			return "redirect:/pizze";
		}
	}

	@PostMapping("/pizze/{pizzaId}/offerte/new")
	public String createOfferta(@PathVariable int pizzaId, @ModelAttribute("offerta") Offerta offerta) {
		Optional<Pizza> pizzaOptional = pizzaService.findById(pizzaId);

		if (pizzaOptional.isPresent()) {
			Pizza pizza = pizzaOptional.get();
			offerta.setPizza(pizza);
			offertaService.save(offerta);
			return "redirect:/pizze/{pizzaId}";
		} else {

			return "redirect:/pizze";
		}
	}

	@GetMapping("/pizze/{pizzaId}/offerte/{offertaId}/edit")
	public String showEditOffertaForm(@PathVariable int pizzaId, @PathVariable int offertaId, Model model) {
		Optional<Offerta> offertaOptional = offertaService.findById(offertaId);
		if (offertaOptional.isPresent()) {
			Offerta offerta = offertaOptional.get();
			model.addAttribute("offerta", offerta);
		} else {
			throw new IllegalArgumentException("Offerta non trovata");
		}
		return "edit-offerta";
	}

	@PostMapping("/pizze/{pizzaId}/offerte/{offertaId}/edit")
	public String updateOfferta(@PathVariable int pizzaId, @PathVariable int offertaId,
			@ModelAttribute("offerta") Offerta offerta) {
		Optional<Pizza> pizzaOptional = pizzaService.findById(pizzaId);
		if (pizzaOptional.isPresent()) {
			Pizza pizza = pizzaOptional.get();

			Optional<Offerta> offertaOptional = offertaService.findById(offertaId);
			if (offertaOptional.isPresent()) {
				Offerta offertaPersistente = offertaOptional.get();

				offertaPersistente.setDataInizio(offerta.getDataInizio());
				offertaPersistente.setDataFine(offerta.getDataFine());
				offertaPersistente.setTitolo(offerta.getTitolo());
				offertaPersistente.setPercentualeSconto(offerta.getPercentualeSconto());
				offertaPersistente.setPizza(pizza);

				offertaService.save(offertaPersistente);
			} else {
				throw new IllegalArgumentException("Offerta non trovata");
			}
		} else {
			throw new IllegalArgumentException("Pizza non trovata");
		}
		return "redirect:/pizze/{pizzaId}";
	}

}
