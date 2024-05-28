package com.backendiiproject.library.Controller;

import com.backendiiproject.library.Model.Emprestimo;
import com.backendiiproject.library.Service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping
    public ResponseEntity<Emprestimo> createEmprestimo(
            @RequestParam("livroId") int livroId,
            @RequestParam("usuarioId") Long usuarioId,
            @RequestParam("dataDevolucao") String dataDevolucao) {

        try {
            Emprestimo emprestimoCreated = emprestimoService.adicionarEmprestimo(livroId, usuarioId, dataDevolucao);
            return ResponseEntity.ok(emprestimoCreated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/list/{livroId}") // Endpoint to list emprestimos by livroId
    public ResponseEntity<List<Emprestimo>> getEmprestimosByLivroId(@PathVariable int livroId) {
        try {
            List<Emprestimo> emprestimos = emprestimoService.listarEmprestimoPorLivro(livroId);
            return ResponseEntity.ok(emprestimos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
