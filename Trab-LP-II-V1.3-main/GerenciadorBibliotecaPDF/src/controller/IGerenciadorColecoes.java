package controller;

import exceptions.ColecaoNaoEncontradaException;
import exceptions.EntradaNaoEncontradaException;
import model.Colecao;
import model.Entrada;

import java.util.List;

public interface IGerenciadorColecoes {
    void criarColecao(String nome);
    void adicionarEntradaNaColecao(String nomeColecao, Entrada entrada) throws ColecaoNaoEncontradaException;
    void removerEntradaDaColecao(String nomeColecao, Entrada entrada) throws ColecaoNaoEncontradaException, EntradaNaoEncontradaException;
    List<Colecao> listarColecoes();
    Colecao obterColecao(String nomeColecao) throws ColecaoNaoEncontradaException;
}
