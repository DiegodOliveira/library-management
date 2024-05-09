package DTO;

import com.backendiiproject.library.Model.Livro;

import lombok.Data;

@Data
public class LivroDto {
	
	public LivroDto(Livro livro) {
		this.nome = livro.getNome();
		this.autor = livro.getAutor();
	}
	
	private String nome;
	private String autor;
	
}
