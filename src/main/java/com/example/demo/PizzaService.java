package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PizzaService {
	@Autowired
	private PizzaRepository pizzaRepository;


	public List<Pizza> findAll() {
		return pizzaRepository.findAll();
	}

	public Pizza save(Pizza pizza) {
		return pizzaRepository.save(pizza);
	}

	public void createAndSavePizza() {
		Pizza australiana = new Pizza("Australiana", "Pomodoro, Mozzarella Guanciale e Pecorino", "www.ciao.it", 30.00f);
		pizzaRepository.save(australiana);

		Pizza thailandese = new Pizza("Thailandese", "Pomodoro, Mozzarella Guanciale e Pecorino", "www.ciao.it", 30.00f);
		pizzaRepository.save(thailandese);
	}

	public List<Pizza> findByNome(String nome) {
		return pizzaRepository.findByNomeContaining(nome);
	}

	public void delete(Pizza pizza) {
		pizzaRepository.delete(pizza);
	}

	public Optional<Pizza> findById(int id) {
		return pizzaRepository.findById(id);
	}
	

}
