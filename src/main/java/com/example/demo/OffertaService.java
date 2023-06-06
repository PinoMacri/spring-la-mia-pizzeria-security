package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OffertaService {
	@Autowired
	private OffertaRepository offertaRepository;

	public Offerta save(Offerta offerta) {
		return offertaRepository.save(offerta);
	}
	
	public Optional<Offerta> findById(int id) {
		return offertaRepository.findById(id);
	}
	
	public Optional<Offerta> getOfferteByPizzaId(int id) {
	    return offertaRepository.findById(id);
	}
}
