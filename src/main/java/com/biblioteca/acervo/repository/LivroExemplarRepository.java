package com.biblioteca.acervo.repository;

import com.biblioteca.acervo.model.LivroExemplar;
import com.biblioteca.acervo.model.StatusExemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LivroExemplarRepository extends JpaRepository<LivroExemplar, Long> {
    
    // Método customizado para buscar apenas exemplares com um status específico (Ex: DISPONIVEL)
    List<LivroExemplar> findByStatus(StatusExemplar status);
}