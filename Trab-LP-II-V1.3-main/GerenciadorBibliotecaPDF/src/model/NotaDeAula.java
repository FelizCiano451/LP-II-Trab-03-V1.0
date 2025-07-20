package model;

import util.Mensagens;
import static util.ValidadorEntrada.*;

public class NotaDeAula implements Entrada {
    private static final long serialVersionUID = 1L;

    private String titulo;
    private String autor;
    private int ano;
    private String curso;

public NotaDeAula(String titulo, String autor, int ano, String curso) {
    validarTitulo(titulo);
    validarAutor(autor);
    validarAno(ano);
    validarDisciplina(curso);

    this.titulo = titulo.trim();
    this.autor = autor.trim();
    this.ano = ano;
    this.curso = curso.trim();
}

     @Override
    public String getTitulo() { return titulo; }

    @Override
    public String getAutor() { return autor; }

    @Override
    public int getAno() { return ano; }

    public String getCurso() { return curso; }

    @Override
    public String gerarBibTeX() {
        return String.format("@misc{%s,\n  author = {%s},\n  title = {%s},\n  year = {%d},\n  note = {Nota de aula do curso: %s}\n}",
            titulo.replaceAll("\\s", ""), autor, titulo, ano, curso);
    }

    @Override
    public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof NotaDeAula)) return false;
    NotaDeAula nota = (NotaDeAula) o;
    return titulo.equalsIgnoreCase(nota.titulo) &&
           autor.equalsIgnoreCase(nota.autor);
}

    @Override
    public int hashCode() {
    return (titulo.toLowerCase() + autor.toLowerCase()).hashCode();
}
}
