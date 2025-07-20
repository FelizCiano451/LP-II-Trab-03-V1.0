package controller;

import exceptions.ColecaoNaoEncontradaException;
import model.Livro;
import model.Entrada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GerenciadorColecoesTest {

    private GerenciadorColecoes gerenciador;

    @BeforeEach
    public void setUp() {
        gerenciador = new GerenciadorColecoes();
    }

    @Test
    public void testCriarColecao() throws ColecaoNaoEncontradaException {
        gerenciador.criarColecao("Favoritos");
        assertNotNull(gerenciador.obterColecao("Favoritos"));
    }

    @Test
    public void testAdicionarEntradaNaColecao() throws Exception {
        gerenciador.criarColecao("Favoritos");
        Entrada livro = new Livro("Livro", "Autor", 2020, "Editora");
        gerenciador.adicionarEntradaNaColecao("Favoritos", livro);
        assertEquals(1, gerenciador.obterColecao("Favoritos").getEntradas().size());
    }

    @Test
    public void testColecaoInexistenteLancaExcecao() {
        assertThrows(ColecaoNaoEncontradaException.class, () ->
                gerenciador.adicionarEntradaNaColecao("Inexistente", new Livro("X", "Y", 2000, "Z")));
    }
}
