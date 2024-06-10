package com.alura.LiterAlura.modelo;

public enum Categoria {
    ACAO("Action", "Ação"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comédia"),
    DRAMA("Drama", "Drama"),
    CRIME("Crime", "Crime"),
    SUSPENSE("Suspense", "Suspense"),
    FICCAO("Fiction", "Ficção");

    private String categoriasDaAPI;

    private String categoriasEmPortugues;

    Categoria(String categoriasDaAPI, String categoriasEmPortugues) {
        this. categoriasDaAPI = categoriasDaAPI;
        this.categoriasEmPortugues = categoriasEmPortugues;
    }

    public static Categoria fromIngles(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriasDaAPI.equalsIgnoreCase(text)) {
                return categoria;
            }
        }

        try {
            throw new IllegalAccessException("Nenhuma categoria encontrada para o livro fornecido" + text);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Categoria fromPortugues(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriasEmPortugues.equalsIgnoreCase(text)) {
                return categoria;
            }
        }

        try {
            throw new IllegalAccessException("Nenhuma categoria encontrada para o livro fornecido" + text);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
