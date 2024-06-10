package com.alura.LiterAlura.modelo;

import com.alura.LiterAlura.data.LivrosData;
import jakarta.persistence.*;

@Entity
public class Livros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeDaObra;

    @ManyToOne
    @JoinColumn(name = "autores_id")
    private Autores autores;
    private String linguagem;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private Integer numeroDeDownloads;

    public Livros() {}

    public Livros(LivrosData livrosData) {
        this.nomeDaObra = livrosData.nomeDaObra();
        Autores autor = new Autores(livrosData.autores().get(0));
        this.autores = autor;
        this.linguagem = livrosData.linguagem().get(0);
        this.numeroDeDownloads = livrosData.numeroDeDownloads();
    }

    public Livros(Long id, String nomeDaObra, Autores autores, String linguagem, Integer numeroDeDownloads) {
        this.nomeDaObra = nomeDaObra;
        this.autores = autores;
        this.linguagem = linguagem;
        this.numeroDeDownloads = numeroDeDownloads;
    }

    public String getNomeDaObra() {
        return nomeDaObra;
    }

    public void setNomeDaObra(String nomeDaObra) {
        this.nomeDaObra = nomeDaObra;
    }

    public Autores getAutores() {
        return autores;
    }

    public void setAutores(Autores autores) {
        this.autores = autores;
    }

    public String getLinguagem() {
        return linguagem;
    }

    public void setLinguagem(String linguagem) {
        this.linguagem = linguagem;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Integer getNumeroDeDownloads() {
        return numeroDeDownloads;
    }

    public void setNumeroDeDownloads(Integer numeroDeDownloads) {
        this.numeroDeDownloads = numeroDeDownloads;
    }

    @Override
    public String toString() {

        return "id=" + id +
                ", autor=" + (autores != null ? autores.getNomeAutor() : "Desconhecido") +
                ", nomeDaObra=" + nomeDaObra + '\'' +
                ", linguagem=" + linguagem + '\'' +
                ", categoria=" + categoria +
                ", numeroDeDownloads=" + numeroDeDownloads;
    }
}
