package model;

import java.io.Serializable;

public interface Entrada extends Serializable {
    String getTitulo();
    String getAutor();
    int getAno();
    String gerarBibTeX();
}
