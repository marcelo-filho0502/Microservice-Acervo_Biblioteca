package com.biblioteca.acervo.repository;

import com.biblioteca.acervo.model.LivroExemplar;
import com.biblioteca.acervo.model.StatusExemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroExemplarRepository extends JpaRepository<LivroExemplar, Long> {

    
    Optional<LivroExemplar> findByIdLivro(Long idLivro);

    List<LivroExemplar> findAllByIdLivro(Long idLivro);
    List<LivroExemplar> findByStatus(StatusExemplar status);
}