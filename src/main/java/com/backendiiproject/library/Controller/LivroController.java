package com.backendiiproject.library.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.backendiiproject.library.Model.Livro;
import com.backendiiproject.library.Service.LivroService;

import DTO.LivroDto;

@Controller
@RequestMapping(path = "/livros")
public class LivroController {
	
	@Autowired
	public LivroService service;

	@GetMapping()
	public ResponseEntity<List<LivroDto>> listarLivros(){
		return new ResponseEntity<List<LivroDto>>(service.listarLivros(), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<LivroDto> encontrarLivroPorId(@PathVariable int id){
		
		Optional<Livro> livroOptional = service.encontrarLivroPorId(id);
		
		if(livroOptional.isEmpty()) {
			return new ResponseEntity<LivroDto>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<LivroDto>(livroOptional.get().toDto(), HttpStatus.ACCEPTED);
		}
	}
	
	@PostMapping
	public ResponseEntity<LivroDto> adicionarLivro(@RequestBody Livro livrob) {
		
		Livro livro = new Livro(livrob.getNome(), livrob.getAutor(), livrob.getEditora(), livrob.getDataDeLancamento());
		try {
			return new ResponseEntity<LivroDto>(service.adicionarLivro(livro).toDto(), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<LivroDto>(HttpStatus.BAD_REQUEST);
		}
		
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id){
		service.deletarLivro(id);
	}
	
}