package com.biblioteca.acervo.controller;

import com.biblioteca.acervo.model.LivroExemplar;
import com.biblioteca.acervo.model.StatusExemplar;
import com.biblioteca.acervo.service.LivroExemplarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exemplares")
@CrossOrigin(origins = "*")
public class LivroExemplarController {

    @Autowired
    private LivroExemplarService service;

   
    @PostMapping
    public ResponseEntity<LivroExemplar> criar(@RequestBody LivroExemplar exemplar) {
        LivroExemplar resultado = service.cadastrarExemplar(exemplar);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

   
    @GetMapping
    public ResponseEntity<List<LivroExemplar>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

   
    public ResponseEntity<List<LivroExemplar>> listarDisponiveis() {
        return ResponseEntity.ok(service.listarDisponiveis());
    }


    @GetMapping("/por-livro/{idLivro}")
    public ResponseEntity<List<LivroExemplar>> buscarPorIdLivro(@PathVariable Long idLivro) {
        return ResponseEntity.ok(service.buscarPorIdLivro(idLivro));
    }

   
    public ResponseEntity<LivroExemplar> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusExemplar status) {

        return service.atualizarStatus(id, status)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            service.deletarLivro(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}