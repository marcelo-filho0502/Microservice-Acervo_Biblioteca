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
        Optional<LivroExemplar> existente = repository.findByIdLivro(exemplar.getIdLivro());
 
        if (existente.isPresent()) {
            LivroExemplar registroAtual = existente.get();
            registroAtual.setQuantidade(registroAtual.getQuantidade() + 1);
            return repository.save(registroAtual);
        } else {
            exemplar.setQuantidade(1);
            if (exemplar.getStatus() == null) {
                exemplar.setStatus(StatusExemplar.DISPONIVEL);
            }
            return repository.save(exemplar);
        }
    }
 
    
    public List<LivroExemplar> listarTodos() {
        return repository.findAll();
    }
 

    public List<LivroExemplar> listarDisponiveis() {
        return repository.findByStatus(StatusExemplar.DISPONIVEL);
    }
 

    public List<LivroExemplar> buscarPorIdLivro(Long idLivro) {
        return repository.findAllByIdLivro(idLivro);
    }
 
   
    public Optional<LivroExemplar> atualizarStatus(Long id, StatusExemplar novoStatus) {
        return repository.findById(id).map(exemplarExistente -> {
            exemplarExistente.setStatus(novoStatus);
            return repository.save(exemplarExistente);
        });
    }
 
   
    public void deletarLivro(Long id) {
        LivroExemplar exemplar = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exemplar com ID " + id + " não encontrado."));
 
        if (exemplar.getQuantidade() > 1) {
            exemplar.setQuantidade(exemplar.getQuantidade() - 1);
            repository.save(exemplar);
        } else {
            repository.deleteById(id);
        }
    }
}
 