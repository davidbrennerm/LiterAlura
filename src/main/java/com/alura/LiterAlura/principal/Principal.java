package com.alura.LiterAlura.principal;

import com.alura.LiterAlura.data.LivrosData;
import com.alura.LiterAlura.data.ResultadoData;
import com.alura.LiterAlura.modelo.Autores;
import com.alura.LiterAlura.modelo.Livros;
import com.alura.LiterAlura.repositorio.AutoresRepositorio;
import com.alura.LiterAlura.repositorio.LivrosRepositorios;
import com.alura.LiterAlura.servico.CloseCaptions;
import com.alura.LiterAlura.servico.ConsultaAPI;
import com.alura.LiterAlura.servico.ConverteDados;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner entradaDeDados = new Scanner(System.in);
    
    private final ConsultaAPI consultaAPI = new ConsultaAPI();

    private final ConverteDados converteDados = new ConverteDados();

    private CloseCaptions linguagem = new CloseCaptions();

    private final String ENDERECO = "http://gutendex.com/books/?search=";

    private LivrosRepositorios livrosRepositorios;

    private AutoresRepositorio autoresRepositorio;

    public Principal(LivrosRepositorios livrosRepositorios, AutoresRepositorio autoresRepositorio) {
        this.livrosRepositorios = livrosRepositorios;
        this.autoresRepositorio = autoresRepositorio;
    }

    public void exibirMenu(){
        var opcao = -1;
        while(opcao != 0) {
            var menu = """
                    ******MENU******
                    Escolha o número de uma opção
                    
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Lista livros em um determinado idioma
                    
                    0 - sair
                    ****************
                    """;

            System.out.println(menu);
            opcao = entradaDeDados.nextInt();
            entradaDeDados.nextLine();

            switch (opcao) {
                case 1: buscarLivro();
                    break;
                case 2: buscarLivrosNoBanco();
                    break;
                case 3: buscarAutoresNoBanco();
                    break;
                case 4: buscarAutoresVivosPorPeriodo();
                    break;
                case 5: buscarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Finalizando...");
                    break;
                default:
                    System.out.println("Escolha uma opção váida");
            }
        }
    }

    private void buscarLivro() {
        ResultadoData dados = getLivros();
        List<LivrosData> livrosData = dados.livrosData();

        if (!livrosData.isEmpty()) {
            LivrosData livroNoBanco = livrosData.get(0);
            Livros livros = new Livros(livroNoBanco);

            Autores autorExistente = autoresRepositorio.buscarAutorPeloNome(livros.getAutores().getNomeAutor());

            if (autorExistente == null) {
                autoresRepositorio.save(livros.getAutores());
            } else {
                livros.setAutores(autorExistente);
            }

            livrosRepositorios.save(livros);
            System.out.println(livros);
        } else {
            System.out.println("Livro não encontrado");
        }
    }


    private ResultadoData getLivros() {
        System.out.println("Digite o nome do livro que deseja buscar:");
        var nomeLivro = entradaDeDados.nextLine();
        System.out.println("Buscando...");
        var json = consultaAPI.consultaBaseAPI(ENDERECO + nomeLivro.replace(" ", "+"));
        ResultadoData resultadoData = converteDados.obterDados(json, ResultadoData.class);
        return resultadoData;
    }

    private void buscarLivrosNoBanco() {
        List<Livros> livros = livrosRepositorios.findAll();
        livros.forEach(System.out::println);
    }

    private void buscarAutoresNoBanco() {
        List<Autores> autores = autoresRepositorio.buscarAutorNoBanco();
        if (!autores.isEmpty()) {
            autores.forEach(System.out::println);
        } else {
            System.out.println("Autor não encontrado no banco, por favor selecione a opção 1 e cadastre um livro.");
        }
    }

    private void buscarAutoresVivosPorPeriodo() {
        try {
//            List<Autores> autores = autoresRepositorio.findAll();
//            autores.forEach(System.out::println);

            System.out.println("Digite o ano para busca:");
            Integer anoDigitado = entradaDeDados.nextInt();
            entradaDeDados.nextLine();

            List<Autores> autoresAno = autoresRepositorio.listaDeAutoresPorAno(anoDigitado);
            autoresAno.forEach(System.out::println);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida, digite um ano válido");
            entradaDeDados.nextLine();
        }
    }

    private void buscarLivrosPorIdioma() {
        System.out.println("""
                Escolha um idioma para filtrar:
                en - Inglês
                pt - Português
                """);
        var idioma = entradaDeDados.nextLine();

        List<Livros> livrosIdioma = livrosRepositorios.buscarLivroPeloIdioma(idioma);
        livrosIdioma.forEach(System.out::println);
    }

}
