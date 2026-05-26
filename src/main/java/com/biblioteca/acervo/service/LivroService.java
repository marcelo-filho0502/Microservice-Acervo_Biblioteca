package com.biblioteca.acervo.service;

import com.biblioteca.acervo.model.Livro;
import com.biblioteca.acervo.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    // Regra: Listagem simples
    public List<Livro> listarTodos() {
        return repository.findAll();
    }

    // Regra: Salvar livro (Poderia ter validações aqui)
    public Livro adicionarLivro(Livro livro) {
        if (livro.getQuantidade() < 0) {
            throw new IllegalArgumentException("A quantidade inicial não pode ser negativa.");
        }
        return repository.save(livro);
    }

    // Regra: Remover livro apenas se ele existir
    public boolean removerLivro(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    // Regra específica de negócio solicitada: Atualizar o status (quantidade)
    public Optional<Livro> atualizarQuantidade(Long id, int novaQuantidade) {
        if (novaQuantidade < 0) {
            throw new IllegalArgumentException("A nova quantidade não pode ser negativa.");
        }
        
        return repository.findById(id).map(livro -> {
            livro.setQuantidade(novaQuantidade);
            return repository.save(livro);
        });
    }
}
