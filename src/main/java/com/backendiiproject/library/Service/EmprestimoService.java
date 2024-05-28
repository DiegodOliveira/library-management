package com.backendiiproject.library.Service;

import com.backendiiproject.library.Model.Emprestimo;
import com.backendiiproject.library.Model.Livro;
import com.backendiiproject.library.Model.Usuario;
import com.backendiiproject.library.Repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    @Autowired
    EmprestimoRepository repository;

    @Autowired
    LivroService livroService;

    @Autowired
    UsuarioService usuarioService;

    public Emprestimo adicionarEmprestimo(int livroId, Long usuarioId, String dataDevolucaoStr) {
        Optional<Livro> livroOptional = livroService.encontrarLivroPorId(livroId);
        Optional<Usuario> usuarioOptional = usuarioService.findById(usuarioId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dataDevolucaoStr, formatter);
        Date dataDevolucao = java.sql.Date.valueOf(localDate);

        if (livroOptional.isPresent() && usuarioOptional.isPresent()) {
            livroService.deduzirEstoque(livroId, 1);
            Emprestimo emprestimo = new Emprestimo(livroOptional.get(), usuarioOptional.get(), dataDevolucao);
            return repository.save(emprestimo);
        } else {
            throw new IllegalArgumentException("Livro or usuário not found.");
        }
    }

    public List<Emprestimo> listarEmprestimoPorLivro(int livroId) {
        return repository.findAllByLivro(livroService.encontrarLivroPorId(livroId).orElseThrow(() -> new IllegalArgumentException("Livro not found")));
    }
}
