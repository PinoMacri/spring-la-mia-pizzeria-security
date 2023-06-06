package com.example.demo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Offerta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private LocalDate dataInizio;
	private LocalDate dataFine;
	private String titolo;
	private int percentualeSconto;

	@ManyToOne
	private Pizza pizza;

	public Offerta() {
	}

	public Offerta(LocalDate dataInizio, LocalDate dataFine, String titolo, int percentualeSconto, Pizza pizza) {
		setDataInizio(dataInizio);
		setDataFine(dataFine);
		setTitolo(titolo);
		setPercentualeSconto(percentualeSconto);
		setPizza(pizza);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}

	public LocalDate getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public int getPercentualeSconto() {
		return percentualeSconto;
	}

	public void setPercentualeSconto(int percentualeSconto) {
		this.percentualeSconto = percentualeSconto;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	@Override
	public String toString() {
		return getId() + getTitolo() + getDataInizio() + getDataFine();
	}
	

}
