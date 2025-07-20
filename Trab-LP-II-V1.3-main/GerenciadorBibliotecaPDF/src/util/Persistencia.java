package util;

import java.io.IOException;

public interface Persistencia<T> {
    void salvar(T objeto, String caminho) throws IOException;
    T carregar(String caminho) throws IOException, ClassNotFoundException;
}
