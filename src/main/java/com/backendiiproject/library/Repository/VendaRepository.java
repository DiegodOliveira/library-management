package com.backendiiproject.library.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendiiproject.library.Model.Livro;
import com.backendiiproject.library.Model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Integer>{

	List<Venda> findAllByLivro(Livro livro);

}
