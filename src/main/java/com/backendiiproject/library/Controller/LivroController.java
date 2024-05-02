package com.backendiiproject.library.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.backendiiproject.library.Model.Livro;
import com.backendiiproject.library.Service.LivroService;

@Controller
@RequestMapping(path = "/livros")
public class LivroController {
	
	@Autowired
	public LivroService service;

	@GetMapping()
	public ResponseEntity<List<Livro>> listarLivros(){
		
		return new ResponseEntity<List<Livro>>(service.listarLivros(), HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Livro> encontrarLivroPorId(@PathVariable int id){
		
		Optional<Livro> livroOptional = service.encontrarLivroPorId(id);
		
		if(livroOptional.isEmpty()) {
			return new ResponseEntity<Livro>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Livro>(livroOptional.get(), HttpStatus.ACCEPTED);
		}
	}
	
	@PostMapping
	public ResponseEntity<Livro> adicionarLivro(@RequestBody Livro livrob) {
		
		Livro livro = new Livro(livrob.getNome(), livrob.getAutor(), livrob.getEditora(), livrob.getDataDeLancamento());
		try {
			return new ResponseEntity<Livro>(service.adicionarLivro(livro), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Livro>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
}