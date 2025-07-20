package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Colecao implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private List<Entrada> entradas;

    public Colecao(String nome) {
        this.nome = nome;
        this.entradas = new ArrayList<>();
    }

   public String getNome() {
        return nome;
    }

    public List<Entrada> getEntradas() {
        return entradas;
    }

    public void adicionarEntrada(Entrada entrada) {
        entradas.add(entrada);
    }

    public void removerEntrada(Entrada entrada) {
        entradas.remove(entrada);
    }

    public String exportarBibTeX() {
        return entradas.stream()
                .map(Entrada::gerarBibTeX)
                .collect(Collectors.joining("\n\n"));
    }
}
    
