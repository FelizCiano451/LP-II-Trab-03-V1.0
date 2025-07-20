package view;

import controller.GerenciadorBiblioteca;
import controller.GerenciadorColecoes;
import exceptions.ColecaoNaoEncontradaException;
import exceptions.EntradaJaExisteException;
import exceptions.EntradaNaoEncontradaException;
import model.Entrada;
import model.Livro;
import model.NotaDeAula;
import model.Slide;
import util.CompactadorZip;
import util.ExportadorBibTeX;
import util.PersistenciaEmArquivo;
import util.Persistencia;
import java.io.File; 
import java.io.FileWriter; 
import java.io.IOException;
import java.util.List;

public class GerenciadorBibliotecaPDF {

    public static void main(String[] args) {
        IGerenciadorBiblioteca biblioteca = new GerenciadorBiblioteca();
        IGerenciadorColecoes colecoes = new GerenciadorColecoes();
        Persistencia<IGerenciadorBiblioteca> persistencia = new PersistenciaEmArquivo<>();

        try {
            // Criar entradas
            Livro livro = new Livro("Programação Orientada a Objetos", "Ana Silva", 2020, "TechBooks");
            NotaDeAula nota = new NotaDeAula("Estruturas de Dados", "Carlos Lima", 2019, "BCC101");
            Slide slide = new Slide("Apresentação de Java", "Beatriz Costa", 2021, "Semana da Computação"); // Assuming Slide now takes String event

            // Adicionar à biblioteca
            biblioteca.adicionarEntrada(livro);
            biblioteca.adicionarEntrada(nota);
            biblioteca.adicionarEntrada(slide);

            // Criar coleção e adicionar entradas
            colecoes.criarColecao("Computação");
            colecoes.adicionarEntradaNaColecao("Computação", livro);
            colecoes.adicionarEntradaNaColecao("Computação", slide);

            // Exportar para BibTeX
            // Ensure IGerenciadorColecoes.obterColecao returns a Colecao object
            // and Colecao.getEntradas() returns the List<Entrada>
            List<Entrada> entradasParaExportar = colecoes.obterColecao("Computação").getEntradas();
            String caminhoBib = "colecao_computacao.bib";
            ExportadorBibTeX.exportarParaArquivo(entradasParaExportar, caminhoBib);
            System.out.println("BibTeX exportado para " + caminhoBib);

            // Compactar o arquivo BibTeX
            String caminhoZip = "colecao_computacao.zip";
            CompactadorZip.compactarArquivo(caminhoBib, caminhoZip);
            System.out.println("Arquivo compactado para " + caminhoZip);

            new File(caminhoBib).delete();


            // Persistir dados da biblioteca
            persistencia.salvar(biblioteca, "biblioteca.dat");
            System.out.println("Biblioteca salva.");

            // Carregar dados da biblioteca
            IGerenciadorBiblioteca bibliotecaCarregada = persistencia.carregar("biblioteca.dat");
            System.out.println("Biblioteca carregada.");

            List<Entrada> entradasCarregadas = bibliotecaCarregada.listarEntradas();
            System.out.println("Entradas carregadas da biblioteca:");
            for (Entrada e : entradasCarregadas) {
                System.out.printf("- %s (%d) por %s\n", e.getTitulo(), e.getAno(), e.getAutor());
            }

        } catch (EntradaJaExisteException | EntradaNaoEncontradaException | ColecaoNaoEncontradaException e) {
            System.err.println("Erro de dados: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro de E/S: " + e.getMessage());
        } catch (ClassNotFoundException e) { 
            System.err.println("Erro de classe não encontrada durante carregamento: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }
    }
}
