package com.example.demo;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Pizza {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Il nome non può essere vuoto")
	private String nome;
	@Size(min = 2, max = 100, message = "La descrizione deve contenere minimo 2 e massimo 100 caratteri")
	private String descrizione;
	@NotBlank(message = "La foto è obbligatoria")
	private String foto;
	@Min(value = 1, message = "Inserisci un prezzo valido")
	private Float prezzo;


    @OneToMany(mappedBy = "pizza", cascade = CascadeType.REMOVE)
    private List<Offerta> offerte;
	@ManyToMany
	private List <Ingrediente> ingredienti;

	public Pizza() {
	}

	public Pizza(String nome, String descrizione, String foto, Float prezzo) {
		setNome(nome);
		setDescrizione(descrizione);
		setFoto(foto);
		setPrezzo(prezzo);
	}
	
	public Pizza (String nome, String descrizione, String foto, Float prezzo, Ingrediente... ingredienti) {
		setNome(nome);
		setDescrizione(descrizione);
		setFoto(foto);
		setPrezzo(prezzo);
		setIngredienti(ingredienti);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
	}

	public List<Offerta> getOfferte() {
		return offerte;
	}

	public void setOfferte(List<Offerta> offerte) {
		this.offerte = offerte;
	}
	
	public List <Ingrediente> getIngredienti(){
		return ingredienti;
	}
	
	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}
	
	public void setIngredienti(Ingrediente[] ingredienti) {
		setIngredienti(Arrays.asList(ingredienti));
	}

	@Override
	public String toString() {
		return "Pizza [id=" + getId() + ", nome=" + getNome() + ", descrizione=" + getDescrizione() + ", foto="
				+ getFoto() + ", prezzo=" + getPrezzo() + "]";
	}
}
