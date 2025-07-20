package controller;

import model.Colecao;
import model.Livro;
import org.junit.jupiter.api.Test;
import util.CompactadorZip; 
import util.ExportadorBibTeX; 
import java.io.File;
import java.io.FileWriter; 
import java.io.IOException; 

import static org.junit.jupiter.api.Assertions.*;

public class ColecaoZipTest {

    @Test
    public void deveGerarArquivoZipParaColecao() throws Exception {
        GerenciadorColecoes gerenciador = new GerenciadorColecoes();
        gerenciador.criarColecao("Programação");

        Livro livro = new Livro("Effective Java", "Joshua Bloch", 2018, "Addison-Wesley");
        gerenciador.adicionarEntradaNaColecao("Programação", livro);

        Colecao colecao = gerenciador.obterColecao("Programação");
        String zipPath = "colecao_programacao_test.zip";
        String tempBibFilePath = "temp_programacao_test.bib"; 

        try {
            String bibtexContent = colecao.exportarBibTeX();
            try (FileWriter writer = new FileWriter(tempBibFilePath)) {
                writer.write(bibtexContent);
            }

            CompactadorZip.compactarArquivo(tempBibFilePath, zipPath);

            File zip = new File(zipPath);
            assertTrue(zip.exists(), "O arquivo ZIP deve existir.");
            assertTrue(zip.length() > 0, "O arquivo ZIP não deve estar vazio.");

        } finally {
            // Limpeza
            new File(zipPath).delete();
            new File(tempBibFilePath).delete();
        }
    }
}
