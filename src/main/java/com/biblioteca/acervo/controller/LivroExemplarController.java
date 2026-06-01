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

    // 1. POST /exemplares (Cadastra um novo exemplar físico)
    @PostMapping
    public ResponseEntity<LivroExemplar> criar(@RequestBody LivroExemplar exemplar) {
        LivroExemplar novoExemplar = service.cadastrarExemplar(exemplar);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoExemplar);
    }

    // 2. GET /exemplares/disponiveis (Lista apenas os que estão disponíveis para empréstimo)
    @GetMapping("/disponiveis")
    public ResponseEntity<List<LivroExemplar>> listarDisponiveis() {
        return ResponseEntity.ok(service.listarDisponiveis());
    }

    // 3. PUT /exemplares/{id}/status (Atualiza o status para EMPRESTADO, DANIFICADO, etc)
    @PutMapping("/{id}/status")
    public ResponseEntity<LivroExemplar> atualizarStatus(
            @PathVariable Long id, 
            @RequestParam StatusExemplar status) {
        
        return service.atualizarStatus(id, status)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. DELETE /exemplares/{id} (Remove um exemplar do banco de dados pelo ID)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
                // Chama o método do Service para deletar
            service.deletarLivro(id);
                
                // Retorna o status 204 No Content (padrão HTTP para exclusão bem-sucedida sem corpo de resposta)
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
                // Se o Service lançar um erro (porque o ID não existe), retorna 404 Not Found
            return ResponseEntity.notFound().build();
            }
        }
}
