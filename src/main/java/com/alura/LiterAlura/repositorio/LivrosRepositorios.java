package com.alura.LiterAlura.repositorio;

import com.alura.LiterAlura.modelo.Autores;
import com.alura.LiterAlura.modelo.Livros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivrosRepositorios extends JpaRepository<Livros, Long> {

    @Query("SELECT l FROM Livros l WHERE l.linguagem ILIKE :idioma")
    List<Livros> buscarLivroPeloIdioma(String idioma);
}
