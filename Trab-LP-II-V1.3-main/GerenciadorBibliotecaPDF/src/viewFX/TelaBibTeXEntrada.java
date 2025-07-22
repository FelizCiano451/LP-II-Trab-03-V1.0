package viewfx;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Entrada;

public class TelaBibTeXEntrada {

    public static void exibir(Entrada entrada) {
        Stage stage = new Stage();
        stage.setTitle("BibTeX da Entrada");

        Label label = new Label("Entrada BibTeX de: " + entrada.getTitulo());

        TextArea textArea = new TextArea(entrada.gerarBibTeX());
        textArea.setWrapText(true);
        textArea.setEditable(false);

        Button btnCopiar = new Button("Copiar");
        btnCopiar.setOnAction(e -> {
            textArea.selectAll();
            textArea.copy();
        });

        Button btnFechar = new Button("Fechar");
        btnFechar.setOnAction(e -> stage.close());

        VBox layout = new VBox(10, label, textArea, btnCopiar, btnFechar);
        layout.setPadding(new Insets(15));

        stage.setScene(new Scene(layout, 500, 300));
        stage.show();
    }
}
