package com.biblioteca.acervo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "exemplares")
public class LivroExemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Guarda apenas o ID do livro que pertence ao Microsserviço de Catálogo
    private Long idLivro; 

    @Enumerated(EnumType.STRING)
    private StatusExemplar status;

    // Construtores
    public LivroExemplar() {}

    public LivroExemplar(Long idLivro, StatusExemplar status) {
        this.idLivro = idLivro;
        this.status = status;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIdLivro() { return idLivro; }
    public void setIdLivro(Long idLivro) { this.idLivro = idLivro; }

    public StatusExemplar getStatus() { return status; }
    public void setStatus(StatusExemplar status) { this.status = status; }
}