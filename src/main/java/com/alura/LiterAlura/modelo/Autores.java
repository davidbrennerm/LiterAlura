package com.alura.LiterAlura.modelo;

import com.alura.LiterAlura.data.AutoresData;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Autores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeAutor;
    private Integer anoDeNascimento;
    private Integer anoDaMorte;

    @OneToMany(mappedBy = "autores", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Livros> livros = new ArrayList<>();

    public Autores() {}

    public Autores(AutoresData autoresData){
        this.nomeAutor = autoresData.nomeAutor();
        this.anoDeNascimento = autoresData.anoDeNascimento();
        try {
            this.anoDaMorte = autoresData.anoDaMorte();
        } catch (Exception e) {
            this.anoDaMorte = null;
        }
    }

    public Autores (String nomeAutor, Integer anoDeNascimento, Integer anoDaMorte) {
        this.nomeAutor = nomeAutor;
        this.anoDeNascimento = anoDeNascimento;
        this.anoDaMorte = anoDaMorte;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public Integer getAnoDeNascimento() {
        return anoDeNascimento;
    }

    public void setAnoDeNascimento(Integer anoDeNascimento) {
        this.anoDeNascimento = anoDeNascimento;
    }

    public Integer getAnoDaMorte() {
        return anoDaMorte;
    }

    public void setAnoDaMorte(Integer anoDaMorte) {
        this.anoDaMorte = anoDaMorte;
    }

    public List<String> getLivros() {
        return livros.stream()
                .map(Livros::getNomeDaObra).collect(Collectors.toList());
    }

    public void setLivros(@org.jetbrains.annotations.NotNull List<Livros> livros) {
        livros.forEach(l -> l.setAutores(this));
        this.livros = livros;
    }

    @Override
    public String toString() {

        String livrosStr = livros.stream()
                .map(Livros::getNomeDaObra)
                .collect(Collectors.joining(", "));
        return  "nomeAutor=" + nomeAutor + '\'' +
                ", anoDeNascimento=" + anoDeNascimento +
                ", anoDaMorte=" + anoDaMorte +
                ", livros=" + livrosStr + '\'';
    }
}
