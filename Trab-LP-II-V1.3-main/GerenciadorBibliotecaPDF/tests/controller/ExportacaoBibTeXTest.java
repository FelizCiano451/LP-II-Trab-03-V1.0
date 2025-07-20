package controller;

import model.Entrada;
import model.Livro;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExportacaoBibTeXTest {

    @Test
    public void deveExportarBibTeXComSucesso() throws IOException {
        GerenciadorBiblioteca biblioteca = new GerenciadorBiblioteca();
        Entrada livro = new Livro("Java Eficaz", "Joshua Bloch", 2018, "Addison-Wesley");
        biblioteca.adicionarEntrada(livro);

        String caminho = "saida_test.bib";
        ExportadorBibTeX.exportar(biblioteca.listarEntradas(), caminho);

        File arquivo = new File(caminho);
        assertTrue(arquivo.exists());

        String conteudo = Files.readString(arquivo.toPath());
        assertTrue(conteudo.contains("@book"));
        assertTrue(conteudo.contains("Java Eficaz"));

        // Limpeza
        arquivo.delete();
    }
}
