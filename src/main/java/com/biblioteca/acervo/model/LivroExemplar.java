package com.biblioteca.acervo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "exemplares")
public class LivroExemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_livro")
    private String nomeLivro; 

    // Novo campo para guardar a quantidade
    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    private StatusExemplar status;

    // Construtor vazio exigido pelo JPA
    public LivroExemplar() {}

    // Construtor com todos os campos
    public LivroExemplar(String nomeLivro, Integer quantidade, StatusExemplar status) {
        this.nomeLivro = nomeLivro;
        this.quantidade = quantidade;
        this.status = status;
    }

    // --- Getters e Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeLivro() { return nomeLivro; }
    public void setNomeLivro(String nomeLivro) { this.nomeLivro = nomeLivro; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public StatusExemplar getStatus() { return status; }
    public void setStatus(StatusExemplar status) { this.status = status; }
}