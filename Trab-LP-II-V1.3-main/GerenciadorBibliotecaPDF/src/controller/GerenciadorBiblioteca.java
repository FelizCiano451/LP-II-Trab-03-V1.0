package controller;

import model.Entrada;
import exceptions.EntradaJaExisteException;
import exceptions.EntradaNaoEncontradaException;
import util.Mensagens;

import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorBiblioteca implements IGerenciadorBiblioteca {
    private Set<Entrada> entradas;

    public GerenciadorBiblioteca() {
        this.entradas = new HashSet<>();
    }

    public void adicionarEntrada(Entrada entrada) throws EntradaJaExisteException {
        if (!entradas.add(entrada)) {
            throw new EntradaJaExisteException(Mensagens.get("erro.entrada.duplicada"));
        }
    }

    @Override
    public void removerEntrada(String titulo) throws EntradaNaoEncontradaException {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException(Mensagens.get("erro.titulo.vazio"));
        }

        Entrada entradaParaRemover = entradas.stream()
            .filter(e -> e.getTitulo().equalsIgnoreCase(titulo))
            .findFirst()
            .orElseThrow(() -> new EntradaNaoEncontradaException(Mensagens.get("erro.entrada.nao.encontrada")));

        entradas.remove(entradaParaRemover);
    }

    public void substituirEntradas(List<Entrada> novasEntradas) {
        entradas.clear();
        entradas.addAll(novasEntradas);
    }

    public List<Entrada> listarEntradas() {
        return new ArrayList<>(entradas);
    }

    public Entrada buscarPorTitulo(String titulo) throws EntradaNaoEncontradaException {
        return entradas.stream()
            .filter(e -> e.getTitulo().equalsIgnoreCase(titulo))
            .findFirst()
            .orElseThrow(() -> new EntradaNaoEncontradaException("Entrada não encontrada: " + titulo));
    }
}
