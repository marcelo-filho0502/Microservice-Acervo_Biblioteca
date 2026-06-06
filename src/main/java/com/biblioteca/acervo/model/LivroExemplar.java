package com.biblioteca.acervo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "exemplares")
public class LivroExemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idLivro;

    @Enumerated(EnumType.STRING)
    private StatusExemplar status;

    private Integer quantidade;

    public LivroExemplar() {}

    public LivroExemplar(Long idLivro, StatusExemplar status, Integer quantidade) {
        this.idLivro = idLivro;
        this.status = status;
        this.quantidade = quantidade;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIdLivro() { return idLivro; }
    public void setIdLivro(Long idLivro) { this.idLivro = idLivro; }

    public StatusExemplar getStatus() { return status; }
    public void setStatus(StatusExemplar status) { this.status = status; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
}