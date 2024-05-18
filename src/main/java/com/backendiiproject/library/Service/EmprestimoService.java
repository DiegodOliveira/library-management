package com.backendiiproject.library.Service;

import com.backendiiproject.library.Model.Emprestimo;
import com.backendiiproject.library.Model.Venda;
import com.backendiiproject.library.Repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backendiiproject.library.Model.Livro;


import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    @Autowired
    EmprestimoRepository repository;

    @Autowired
    LivroService livro;

    public Venda adicionarVenda(int livroId) {

        Optional<Livro> livroOptional = livro.encontrarLivroPorId(livroId);

        if(livroOptional.isPresent()) {
            livro.deduzirEstoque(livroId, 1);
            return repository.save(new Emprestimo(livroOptional.get()));
        } else {
            throw new NullPointerException();
        }

    }

    public List<Emprestimo> listarEmprestimoPorLivro(int livroId) {
        return repository.findAllByLivro(livro.encontrarLivroPorId(livroId).get());
    }
}
