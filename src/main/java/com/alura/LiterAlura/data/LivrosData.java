package com.alura.LiterAlura.data;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivrosData(@JsonAlias("title") String nomeDaObra,
                         @JsonAlias("authors") List<AutoresData> autores,
                         @JsonAlias("languages") List<String> linguagem,
                         // @JsonAlias("subjects") List<String> categoria,
                         @JsonAlias("download_count") Integer numeroDeDownloads) {
}
