package com.backendiiproject.library.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendiiproject.library.Model.Livro;
import com.backendiiproject.library.Repository.LivroRepository;

@Service
public class LivroService {

	@Autowired
	LivroRepository repository;
	
	public Livro adicionarLivro(Livro livro){
		try {
			return repository.save(livro);
		} catch (Exception e) {
			throw new NullPointerException();
		}
	}
	
	public List<Livro> listarLivros() {
		return repository.findAll();
	}
	
	public Optional<Livro> encontrarLivroPorId(int id){
		return repository.findById(id);
	}
	
	public void deletarLivro(int id) {
		repository.deleteById(id);
	}
}

