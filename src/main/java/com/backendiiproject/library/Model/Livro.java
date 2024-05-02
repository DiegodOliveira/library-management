package com.backendiiproject.library.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Livro{
	
	public Livro(String nome, String autor, String editora, String dataDeLancamento) {
		this.nome = nome;
		this.autor = autor;
		this.editora = editora;
		this.dataDeLancamento = dataDeLancamento;
		this.estoque = 0;
	}
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nome;
	private String autor;
	private String editora;
	private String dataDeLancamento;
	private int estoque;
}
