package com.backendiiproject.library.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.backendiiproject.library.Controller.LivroController;
import com.backendiiproject.library.Model.Livro;

@Service
@RequestMapping(path = "/livros")
public class LivroService {
	
	@Autowired
	public LivroController controller;

	@GetMapping()
	public ResponseEntity<List<Livro>> listarLivros(){
		
		return new ResponseEntity<List<Livro>>(controller.listarLivros(), HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Livro> encontrarLivroPorId(@PathVariable int id){
		
		Optional<Livro> livroOptional = controller.encontrarLivroPorId(id);
		
		if(livroOptional.isEmpty()) {
			return new ResponseEntity<Livro>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Livro>(livroOptional.get(), HttpStatus.ACCEPTED);
		}
	}
	
	@PostMapping
	public ResponseEntity<Livro> adicionarLivro(@RequestBody String nome, 
												@RequestBody String autor, 
												@RequestBody String editora, 
												@RequestBody String dataDeLancamento) {
		
		Livro livro = new Livro(nome, autor, editora, dataDeLancamento);
		try {
			return new ResponseEntity<Livro>(controller.adicionarLivro(livro), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Livro>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
