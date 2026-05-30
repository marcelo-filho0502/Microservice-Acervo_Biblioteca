package com.biblioteca.acervo.repository;

import com.biblioteca.acervo.model.LivroExemplar;
import com.biblioteca.acervo.model.StatusExemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; // Não se esqueça de importar a List

@Repository
public interface LivroExemplarRepository extends JpaRepository<LivroExemplar, Long> {
    
    // Método mágico do Spring para encontrar livros por status
    List<LivroExemplar> findByStatus(StatusExemplar status);
}