package com.backendiiproject.library.Model;

import DTO.LivroDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livro {

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

	public void reduzEstoque(int qtd) {
		setEstoque(getEstoque() - qtd); // Use the getter for better encapsulation practices
	}

	public void aumentaEstoque(int qtd) {
		setEstoque(getEstoque() + qtd); // Use the getter for better encapsulation practices
	}

	public LivroDto toDto() {
		return new LivroDto(this);
	}

	public String getNome() {
		return nome; // Added return statement
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getDataDeLancamento() {
		return dataDeLancamento;
	}

	public void setDataDeLancamento(String dataDeLancamento) {
		this.dataDeLancamento = dataDeLancamento;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = Math.max(0, estoque); // Ensure that stock doesn't go negative
	}
}
