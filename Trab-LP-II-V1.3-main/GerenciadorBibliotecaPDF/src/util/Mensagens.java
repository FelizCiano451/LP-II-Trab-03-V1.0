package util;

import java.util.Locale;
import java.util.ResourceBundle;

public class Mensagens {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("resources.messages", new Locale("pt", "BR"));

    public static String get(String chave) {
        return bundle.getString(chave);
    }
}
