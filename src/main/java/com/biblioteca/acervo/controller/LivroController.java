package com.biblioteca.acervo.controller;

import com.biblioteca.acervo.model.Livro;
import com.biblioteca.acervo.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService service; // Inversão de Dependência (SOLID)

    @GetMapping
    public ResponseEntity<List<Livro>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Livro livro) {
        try {
            Livro novoLivro = service.adicionarLivro(livro);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoLivro);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean removido = service.removerLivro(id);
        if (removido) {
            return ResponseEntity.noContent().build(); // Status 204
        }
        return ResponseEntity.notFound().build(); // Status 404
    }

    @PatchMapping("/{id}/quantidade")
    public ResponseEntity<?> atualizarStatus(@PathVariable Long id, @RequestParam int quantidade) {
        try {
            return service.atualizarQuantidade(id, quantidade)
                    .map(livroAtualizado -> ResponseEntity.ok(livroAtualizado))
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}