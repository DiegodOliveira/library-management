package com.backendiiproject.library.Model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Venda {
	
	public Venda(Livro livro) {
		Date dataDeHoje = new Date();
		this.data = dataDeHoje.toString();
		this.livro = livro;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	private Livro livro;
	private String data;
}
