package com.biblioteca.acervo.service;

import com.biblioteca.acervo.model.LivroExemplar;
import com.biblioteca.acervo.model.StatusExemplar;
import com.biblioteca.acervo.repository.LivroExemplarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LivroExemplarService {

    @Autowired
    private LivroExemplarRepository repository;

    public LivroExemplar cadastrarExemplar(LivroExemplar exemplar) {
        // Se não enviar status, ele nasce DISPONIVEL por padrão
        if (exemplar.getStatus() == null) {
            exemplar.setStatus(StatusExemplar.DISPONIVEL);
        }
        return repository.save(exemplar);
    }

    public List<LivroExemplar> listarDisponiveis() {
        return repository.findByStatus(StatusExemplar.DISPONIVEL);
    }

    public Optional<LivroExemplar> atualizarStatus(Long id, StatusExemplar novoStatus) {
        return repository.findById(id).map(exemplar -> {
            exemplar.setStatus(novoStatus);
            return repository.save(exemplar);
        });
    }
}