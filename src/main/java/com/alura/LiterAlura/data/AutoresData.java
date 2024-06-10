package com.alura.LiterAlura.data;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutoresData(@JsonAlias("name") String nomeAutor,
                          @JsonAlias("birth_year") Integer anoDeNascimento,
                          @JsonAlias("death_year") Integer anoDaMorte) {
}
