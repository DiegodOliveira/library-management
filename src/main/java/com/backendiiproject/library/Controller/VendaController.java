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

import com.backendiiproject.library.Model.Venda;
import com.backendiiproject.library.Service.VendaService;

@Controller
@RequestMapping(path = "/vendas")
public class VendaController {
	
	@Autowired
	VendaService service;
	
	@GetMapping()
	public ResponseEntity<List<Venda>> listarVendas(){
		return new ResponseEntity<List<Venda>>(service.listarVendas(), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Venda> encontrarVendaPorId(@PathVariable int id){
		Optional<Venda> vendaOptional = service.encontrarVendaPorId(id);
		
		if(vendaOptional.isEmpty()) {
			return new ResponseEntity<Venda>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Venda>(vendaOptional.get(), HttpStatus.ACCEPTED);
		}
	}
	
	@GetMapping(path = "/livro/{id}")
	public ResponseEntity<List<Venda>> listarVendasPorLivro(@PathVariable int id){
		try {
			return new ResponseEntity<List<Venda>>(service.listarVendasPorLivro(id), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<List<Venda>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(path = "/{idLivro}")
	public ResponseEntity<Venda> adicionarVenda(@PathVariable int idLivro){
		try {
			return new ResponseEntity<Venda>(service.adicionarVenda(idLivro), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Venda>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
