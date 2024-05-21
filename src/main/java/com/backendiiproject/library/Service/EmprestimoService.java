package com.backendiiproject.library.Service;

import com.backendiiproject.library.Model.Emprestimo;
import com.backendiiproject.library.Model.Usuario;
import com.backendiiproject.library.Model.Venda;
import com.backendiiproject.library.Repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backendiiproject.library.Model.Livro;


import java.text.SimpleDateFormat;
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
    LivroService livro;

    @Autowired
    UsuarioService usuario;


    public Venda adicionarEmprestimo(int livroId, Long usuarioId, String dataDevolucao) {

        Optional<Livro> livroOptional = livro.encontrarLivroPorId(livroId);

        Optional<Usuario> usuarioOptional = usuario.findById(usuarioId);

        DateTimeFormatter format = new DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dataDevolucao, format);

        if(livroOptional.isPresent()) {
            livro.deduzirEstoque(livroId, 1);
            return repository.save(new Emprestimo(livroOptional.get(), usuarioOptional.get(), format.(dataDevolucao)));
        } else {
            throw new NullPointerException();
        }

    }

    public List<Emprestimo> listarEmprestimoPorLivro(int livroId) {
        return repository.findAllByLivro(livro.encontrarLivroPorId(livroId).get());
    }
}
