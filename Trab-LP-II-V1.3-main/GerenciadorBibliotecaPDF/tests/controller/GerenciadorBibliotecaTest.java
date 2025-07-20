package controller;

import exceptions.EntradaJaExisteException;
import exceptions.EntradaNaoEncontradaException;
import model.Livro;
import model.Entrada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GerenciadorBibliotecaTest {

    private GerenciadorBiblioteca gerenciador;

    @BeforeEach
    public void setUp() {
        gerenciador = new GerenciadorBiblioteca();
    }

    @Test
    public void testAdicionarEntrada() throws EntradaJaExisteException {
        Entrada livro = new Livro("Livro Teste", "Autor Teste", 2024, "Editora Teste");
        gerenciador.adicionarEntrada(livro);
        assertEquals(1, gerenciador.listarEntradas().size());
    }

    @Test
    public void testAdicionarEntradaDuplicadaLancaExcecao() throws EntradaJaExisteException {
        Entrada livro = new Livro("Livro Teste", "Autor Teste", 2024, "Editora Teste");
        gerenciador.adicionarEntrada(livro);
        assertThrows(EntradaJaExisteException.class, () -> gerenciador.adicionarEntrada(livro));
    }

    @Test
    public void testRemoverEntrada() throws Exception {
        Entrada livro = new Livro("Livro Teste", "Autor Teste", 2024, "Editora Teste");
        gerenciador.adicionarEntrada(livro);
        gerenciador.removerEntrada("Livro Teste");
        assertTrue(gerenciador.listarEntradas().isEmpty());
    }

    @Test
    public void testBuscarEntradaExistente() throws Exception {
        Entrada livro = new Livro("Livro Teste", "Autor Teste", 2024, "Editora Teste");
        gerenciador.adicionarEntrada(livro);
        Entrada encontrada = gerenciador.buscarPorTitulo("Livro Teste");
        assertEquals("Autor Teste", encontrada.getAutor());
    }

    @Test
    public void testBuscarEntradaInexistente() {
        assertThrows(EntradaNaoEncontradaException.class, () -> gerenciador.buscarPorTitulo("Inexistente"));
    }
}
