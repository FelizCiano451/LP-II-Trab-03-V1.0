package util;

public class ValidadorEntrada {

    public static void validarTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException(Mensagens.get("erro.titulo.vazio"));
        }
    }

    public static void validarAutor(String autor) {
        if (autor == null || autor.isBlank()) {
            throw new IllegalArgumentException(Mensagens.get("erro.autor.vazio"));
        }
    }

    public static void validarAno(int ano) {
        if (ano <= 0) {
            throw new IllegalArgumentException(Mensagens.get("erro.ano.invalido"));
        }
    }

    public static void validarEditora(String editora) {
        if (editora == null || editora.isBlank()) {
            throw new IllegalArgumentException(Mensagens.get("erro.editora.vazia"));
        }
    }

    public static void validarCurso(String curso) {
        if (curso == null || curso.isBlank()) {
            throw new IllegalArgumentException(Mensagens.get("erro.curso.vazio"));
        }
    }

    public static void validarNumeroSlides(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException(Mensagens.get("erro.slides.invalidos"));
        }
    }
}
