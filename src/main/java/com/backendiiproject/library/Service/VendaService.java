package com.backendiiproject.library.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendiiproject.library.Model.Livro;
import com.backendiiproject.library.Model.Venda;
import com.backendiiproject.library.Repository.VendaRepository;

@Service
public class VendaService {
	
	@Autowired
	VendaRepository repository;
	
	@Autowired
	LivroService livroService;
	
	public Venda adicionarVenda(int livroId) {
		
		Optional<Livro> livroOptional = livroService.encontrarLivroPorId(livroId);
		
		if(livroOptional.isPresent()) {
			livroService.deduzirEstoque(livroId, 1);
			return repository.save(new Venda(livroOptional.get()));
		} else {
			throw new NullPointerException();
		}
		
	}
	
	public List<Venda> listarVendas() {
		return repository.findAll();
	}
	
	public List<Venda> listarVendasPorLivro(int livroId) {
		return repository.findAllByLivro(livroService.encontrarLivroPorId(livroId).get());
	}
	
	public Optional<Venda> encontrarVendaPorId(int id) {
		return repository.findById(id);
	}
	
}
