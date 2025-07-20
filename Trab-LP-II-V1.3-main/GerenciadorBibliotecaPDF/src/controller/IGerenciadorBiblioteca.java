package controller;

import exceptions.EntradaJaExisteException;
import exceptions.EntradaNaoEncontradaException;
import model.Entrada;

import java.util.List;

public interface IGerenciadorBiblioteca {
    void adicionarEntrada(Entrada entrada) throws EntradaJaExisteException;
    void removerEntrada(String titulo) throws EntradaNaoEncontradaException;
    Entrada buscarPorTitulo(String titulo) throws EntradaNaoEncontradaException;
    List<Entrada> listarEntradas();
}
