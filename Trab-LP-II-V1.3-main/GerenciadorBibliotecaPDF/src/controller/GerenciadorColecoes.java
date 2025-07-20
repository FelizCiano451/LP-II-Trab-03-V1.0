package controller;

import model.Colecao;
import model.Entrada;
import exceptions.ColecaoNaoEncontradaException;
import exceptions.EntradaNaoEncontradaException;
import util.Mensagens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GerenciadorColecoes implements IGerenciadorColecoes {
    private Map<String, Colecao> colecoes;

    public GerenciadorColecoes() {
        this.colecoes = new HashMap<>();
    }

    public void criarColecao(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(Mensagens.get("erro.nome.colecao.vazio"));
        }
        colecoes.putIfAbsent(nome.trim(), new Colecao(nome.trim()));
    }

    public void adicionarEntradaNaColecao(String nomeColecao, Entrada entrada) throws ColecaoNaoEncontradaException {
        Colecao colecao = colecoes.get(nomeColecao);
        if (colecao == null) {
            throw new ColecaoNaoEncontradaException(Mensagens.get("erro.colecao.nao.encontrada") + ": " + nomeColecao);
        }
        colecao.adicionarEntrada(entrada);
    }

    public void removerEntradaDaColecao(String nomeColecao, Entrada entrada)
            throws ColecaoNaoEncontradaException, EntradaNaoEncontradaException {

        Colecao colecao = colecoes.get(nomeColecao);
        if (colecao == null) {
            throw new ColecaoNaoEncontradaException(Mensagens.get("erro.colecao.nao.encontrada") + ": " + nomeColecao);
        }

        if (!colecao.getEntradas().contains(entrada)) {
            throw new EntradaNaoEncontradaException(Mensagens.get("erro.entrada.nao.encontrada"));
        }

        colecao.removerEntrada(entrada);
    }

    public List<Colecao> listarColecoes() {
        return new ArrayList<>(colecoes.values());
    }

    public Colecao obterColecao(String nomeColecao) throws ColecaoNaoEncontradaException {
        Colecao colecao = colecoes.get(nomeColecao);
        if (colecao == null) {
            throw new ColecaoNaoEncontradaException(Mensagens.get("erro.colecao.nao.encontrada") + ": " + nomeColecao);
        }
        return colecao;
    }
}
