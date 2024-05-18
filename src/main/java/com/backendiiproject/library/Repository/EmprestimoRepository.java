package com.backendiiproject.library.Repository;

import com.backendiiproject.library.Model.Emprestimo;
import com.backendiiproject.library.Model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    List<Emprestimo> findAllByLivro(Livro livro);
}
