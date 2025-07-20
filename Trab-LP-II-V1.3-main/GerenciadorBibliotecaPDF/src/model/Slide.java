package model;

import util.Mensagens;
import static util.ValidadorEntrada.*;

public class Slide implements Entrada {
    private static final long serialVersionUID = 1L;

    private String titulo;
    private String autor;
    private int ano;
    private String evento;

    public Slide(String titulo, String autor, int ano, String evento) {
        validarTitulo(titulo);
        validarAutor(autor);
        validarAno(ano);

        this.titulo = titulo.trim();
        this.autor = autor.trim();
        this.ano = ano;
        this.evento = evento.trim();
    }

    @Override
    public String getTitulo() { return titulo; }

    @Override
    public String getAutor() { return autor; }

    @Override
    public int getAno() { return ano; }

    public String getEvento() { return evento; }

    @Override
    public String gerarBibTeX() {
        return String.format("@misc{%s,\n  author = {%s},\n  title = {%s},\n  year = {%d},\n  howpublished = {Apresentado em: %s}\n}",
            titulo.replaceAll("\\s", ""), autor, titulo, ano, evento);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Slide)) return false;
        Slide slide = (Slide) o;
        return titulo.equalsIgnoreCase(slide.titulo) &&
               autor.equalsIgnoreCase(slide.autor);
    }

    @Override
    public int hashCode() {
        return (titulo.toLowerCase() + autor.toLowerCase()).hashCode();
    }
}
    public int hashCode() {
    return (titulo.toLowerCase() + autor.toLowerCase()).hashCode();
}
}
