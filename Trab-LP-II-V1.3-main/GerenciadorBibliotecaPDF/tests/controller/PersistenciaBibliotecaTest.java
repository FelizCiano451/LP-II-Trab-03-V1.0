package controller;

import model.Entrada;
import model.Livro;
import org.junit.jupiter.api.Test;
import util.Persistencia;
import util.PersistenciaEmArquivo;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PersistenciaBibliotecaTest {

    @Test
    public void deveSalvarECarregarBiblioteca() throws Exception {
        GerenciadorBiblioteca original = new GerenciadorBiblioteca();
        Entrada livro = new Livro("Clean Code", "Robert Martin", 2008, "Prentice Hall");
        original.adicionarEntrada(livro);

        Persistencia<GerenciadorBiblioteca> persistencia = new PersistenciaEmArquivo<>();
        String caminho = "biblioteca_test.dat";
        persistencia.salvar(original, caminho);

        GerenciadorBiblioteca recarregada = persistencia.carregar(caminho);
        List<Entrada> entradas = recarregada.listarEntradas();

        assertEquals(1, entradas.size());
        assertEquals("Clean Code", entradas.get(0).getTitulo());

        // Limpeza
        new File(caminho).delete();
    }
}
