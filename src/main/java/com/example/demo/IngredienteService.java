package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredienteService {
	@Autowired
	private IngredienteRepository ingredienteRepository;

	public List<Ingrediente> findAll() {
		return ingredienteRepository.findAll();
	}

	public Optional<Ingrediente> findById(int id) {
		return ingredienteRepository.findById(id);
	}

	public Ingrediente save(Ingrediente ingrediente) {
		return ingredienteRepository.save(ingrediente);
	}

	public List<Ingrediente> findByIds(List<Integer> ids) {
		return ingredienteRepository.findAllById(ids);
	}

	public void delete(Ingrediente ingrediente) {
		ingredienteRepository.delete(ingrediente);
	}

}
