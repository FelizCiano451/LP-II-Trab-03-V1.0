package util;

import model.Entrada;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportadorBibTeX {
    public static void exportarParaArquivo(List<Entrada> entradas, String caminhoArquivo) throws IOException {
        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            for (Entrada entrada : entradas) {
                writer.write(entrada.gerarBibTeX());
                writer.write("\n\n");
            }
        }
    }
}
