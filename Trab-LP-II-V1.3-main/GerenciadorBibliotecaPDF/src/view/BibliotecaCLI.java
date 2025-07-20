package view;

import controller.*;
import model.*;
import util.*;

import java.util.Scanner;

public class BibliotecaCLI {

    private static final Scanner scanner = new Scanner(System.in);
    private static final IGerenciadorBiblioteca biblioteca = new GerenciadorBiblioteca();
    private static final IGerenciadorColecoes colecoes = new GerenciadorColecoes();
    private static final Persistencia<IGerenciadorBiblioteca> persistencia = new PersistenciaEmArquivo<>();

    public static void main(String[] args) {
        String comando;
        System.out.println("Bem-vindo ao Gerenciador de Biblioteca PDF!");
        do {
            mostrarMenu();
            comando = scanner.nextLine().trim().toLowerCase();

            try {
                switch (comando) {
                    case "1" -> adicionarLivro();
                    case "2" -> listarEntradas();
                    case "3" -> removerEntrada();
                    case "4" -> exportarBibTeX();
                    case "5" -> salvar();
                    case "6" -> carregar();
                    case "7" -> adicionarNotaDeAula();
                    case "8" -> adicionarSlide();
                    case "9" -> criarColecao();
                    case "10" -> adicionarEntradaAColecao();
                    case "11" -> gerarZipColecao();
                    case "0" -> System.out.println("Encerrando...");
                    default -> System.out.println("Comando inválido.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while (!comando.equals("0"));
    }

    private static void mostrarMenu() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1 - Adicionar Livro");
        System.out.println("2 - Listar Entradas");
        System.out.println("3 - Remover Entrada");
        System.out.println("4 - Exportar para BibTeX");
        System.out.println("5 - Salvar Biblioteca");
        System.out.println("6 - Carregar Biblioteca");
        System.out.println("7 - Adicionar Nota de Aula");
        System.out.println("8 - Adicionar Slide");
        System.out.println("9 - Criar Coleção");
        System.out.println("10 - Adicionar Entrada a Coleção");
        System.out.println("11 - Gerar ZIP de Coleção");

        System.out.println("0 - Sair");
        System.out.print(">>> ");
    }

    private static void adicionarLivro() throws Exception {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Ano: ");
        int ano = Integer.parseInt(scanner.nextLine());
        System.out.print("Editora: ");
        String editora = scanner.nextLine();

        Livro livro = new Livro(titulo, autor, ano, editora);
        biblioteca.adicionarEntrada(livro);
        System.out.println("Livro adicionado com sucesso.");
    }

     private static void adicionarNotaDeAula() throws Exception {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Ano: ");
        int ano = Integer.parseInt(scanner.nextLine());
        System.out.print("Curso: ");
        String curso = scanner.nextLine();

         NotaDeAula nota = new NotaDeAula(titulo, autor, ano, curso);
         biblioteca.adicionarEntrada(nota);
         System.out.println("Nota de Aula adicionada com sucesso.");
}

    private static void adicionarSlide() throws Exception {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Ano: ");
        int ano = Integer.parseInt(scanner.nextLine());
        System.out.print("Evento: ");
        String evento = scanner.nextLine();

        Slide slide = new Slide(titulo, autor, ano, evento); 
        biblioteca.adicionarEntrada(slide);
        System.out.println("Slide adicionado com sucesso.");
}

    private static void criarColecao() throws Exception {
        System.out.print("Nome da coleção: ");
        String nome = scanner.nextLine();
        colecoes.criarColecao(nome);
        System.out.println("Coleção criada.");
}

    private static void adicionarEntradaAColecao() throws Exception {
        System.out.print("Nome da coleção: ");
        String nomeColecao = scanner.nextLine();
        System.out.print("Título da entrada a adicionar: ");
        String titulo = scanner.nextLine();

    Entrada entrada = biblioteca.listarEntradas().stream()
        .filter(e -> e.getTitulo().equalsIgnoreCase(titulo))
        .findFirst()
        .orElseThrow(() -> new EntradaNaoEncontradaException(Mensagens.get("erro.entrada.nao.encontrada")));

    colecoes.adicionarEntradaNaColecao(nomeColecao, entrada);
    System.out.println("Entrada adicionada à coleção.");
}

    private static void gerarZipColecao() throws Exception {
    System.out.print("Nome da coleção: ");
    String nome = scanner.nextLine();
    System.out.print("Caminho do arquivo ZIP: ");
    String caminhoZip = scanner.nextLine();

    Colecao colecao = colecoes.obterColecao(nome);

    String bibtexContent = colecao.exportarBibTeX();
    String tempBibFilePath = "temp_" + nome.replaceAll("\\s+", "_") + ".bib";
    try (FileWriter writer = new FileWriter(tempBibFilePath)) {
        writer.write(bibtexContent);
    }

    CompactadorZip.compactarArquivo(tempBibFilePath, caminhoZip);

    new File(tempBibFilePath).delete();

    System.out.println("ZIP da coleção gerado com sucesso em: " + caminhoZip);
}
    
    private static void listarEntradas() {
        var entradas = biblioteca.listarEntradas();
        if (entradas.isEmpty()) {
            System.out.println("Nenhuma entrada na biblioteca.");
        } else {
            entradas.forEach(e -> System.out.printf("- %s (%s, %d)%n", e.getTitulo(), e.getAutor(), e.getAno()));
        }
    }

    private static void removerEntrada() throws Exception {
        System.out.print("Título da entrada a remover: ");
        String titulo = scanner.nextLine();
        biblioteca.removerEntrada(titulo);
        System.out.println("Entrada removida.");
    }

    private static void exportarBibTeX() throws Exception {
        System.out.print("Caminho do arquivo BibTeX de saída: ");
        String caminho = scanner.nextLine();
        ExportadorBibTeX.exportarParaArquivo(biblioteca.listarEntradas(), caminho);
        System.out.println("Exportação concluída.");
    }

    private static void salvar() throws Exception {
        persistencia.salvar(biblioteca, "biblioteca.dat");
        System.out.println("Biblioteca salva.");
    }

    private static void carregar() throws Exception {
        IGerenciadorBiblioteca b = persistencia.carregar("biblioteca.dat");
        biblioteca.substituirEntradas(b.listarEntradas());
        System.out.println("Biblioteca carregada.");
    }
}
