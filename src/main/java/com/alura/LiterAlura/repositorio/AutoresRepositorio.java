package com.alura.LiterAlura.repositorio;

import com.alura.LiterAlura.modelo.Autores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoresRepositorio extends JpaRepository<Autores, Long> {

    @Query("SELECT a FROM Livros l JOIN l.autores a WHERE a.nomeAutor = :nomeAutor")
    Autores buscarAutorPeloNome(String nomeAutor);


    @Query("SELECT a FROM Livros l JOIN l.autores a")
    List<Autores> buscarAutorNoBanco();

    @Query("SELECT a FROM Livros l JOIN l.autores a WHERE a.anoDeNascimento <= :anoDigitado and a.anoDaMorte >= :anoDigitado")
    List<Autores> listaDeAutoresPorAno(Integer anoDigitado);
}
