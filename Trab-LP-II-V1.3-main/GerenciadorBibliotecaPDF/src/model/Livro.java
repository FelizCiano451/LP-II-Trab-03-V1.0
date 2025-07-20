package model;

import util.Mensagens;
import static util.ValidadorEntrada.*;

public class Livro implements Entrada {
    private static final long serialVersionUID = 1L;

    private String titulo;
    private String autor;
    private int ano;
    private String editora;

public Livro(String titulo, String autor, int ano, String editora) {
    validarTitulo(titulo);
    validarAutor(autor);
    validarAno(ano);
    validarEditora(editora);

    this.titulo = titulo;
    this.autor = autor;
    this.ano = ano;
    this.editora = editora;
}

    @Override
    public String getTitulo() { return titulo; }

    @Override
    public String getAutor() { return autor; }

    @Override
    public int getAno() { return ano; }

    public String getEditora() { return editora; }

    @Override
    public String gerarBibTeX() {
        return String.format("@book{%s,\n  author = {%s},\n  title = {%s},\n  year = {%d},\n  publisher = {%s}\n}",
            titulo.replaceAll("\\s", ""), autor, titulo, ano, editora);
    }

    @Override
    public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Livro)) return false;
    Livro livro = (Livro) o;
    return titulo.equalsIgnoreCase(livro.titulo) &&
           autor.equalsIgnoreCase(livro.autor);
}
    @Override
    public int hashCode() {
    return (titulo.toLowerCase() + autor.toLowerCase()).hashCode();
}
}
