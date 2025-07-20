package util;

import java.io.*;

public class PersistenciaEmArquivo<T> implements Persistencia<T> {

    @Override
    public void salvar(T objeto, String caminho) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(caminho))) {
            out.writeObject(objeto);
        }
    }

    @Override
    public T carregar(String caminho) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(caminho))) {
            return (T) in.readObject();
        }
    }
}
