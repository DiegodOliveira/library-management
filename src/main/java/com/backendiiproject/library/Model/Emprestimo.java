package com.backendiiproject.library.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Emprestimo {

    public Emprestimo(Livro livro, Usuario usuario, Date dataDevolucao){
        this.livro = livro;
        this.usuario = usuario;
        this.dataDevolucao = dataDevolucao.toString();
        this.dataDeEmprestimo = new Date().toString();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "livro")
    private Livro livro;

    private String dataDeEmprestimo;

    private String dataDevolucao;

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}
