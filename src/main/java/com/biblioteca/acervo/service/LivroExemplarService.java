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

    // ========================================================
    // 1. Resolve o erro da linha 24: cadastrarExemplar
    // ========================================================
    public LivroExemplar cadastrarExemplar(LivroExemplar exemplar) {
        return repository.save(exemplar);
    }

    // ========================================================
    // 2. Resolve o erro da linha 30: listarDisponiveis
    // ========================================================
    public List<LivroExemplar> listarDisponiveis() {
        // Atenção: Certifique-se de que "DISPONIVEL" está escrito exatamente
        // da mesma forma que definiu no seu Enum StatusExemplar.
        return repository.findByStatus(StatusExemplar.DISPONIVEL);
    }

    // ========================================================
    // 3. Resolve o erro da linha 39: atualizarStatus
    // ========================================================
    // Retornamos um Optional porque no Controller você usou .map().orElse()
    public Optional<LivroExemplar> atualizarStatus(Long id, StatusExemplar novoStatus) {
        return repository.findById(id).map(exemplarExistente -> {
            exemplarExistente.setStatus(novoStatus);
            return repository.save(exemplarExistente); // Guarda e retorna o objeto atualizado
        });
    }
    public void deletarLivro(Long id) {
    // Verifica se o ID realmente existe no banco de dados
    if (repository.existsById(id)) {
        repository.deleteById(id); // Executa a exclusão
    } else {
        // Lança uma exceção caso o ID não seja encontrado
        throw new RuntimeException("Livro com ID " + id + " não encontrado.");
    }
}

    // (Pode manter aqui os métodos de deletarLivro e atualizarLivro 
    // que conversámos na resposta anterior, se quiser utilizá-los depois!)
}