package viewfx;

import controller.GerenciadorBiblioteca;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Colecao;

public class TelaBibTeXColecao {

    public static void exibir(GerenciadorBiblioteca gerenciador) {
        Stage stage = new Stage();
        stage.setTitle("BibTeX de Coleção");

        ComboBox<String> selecao = new ComboBox<>();
        selecao.getItems().addAll(gerenciador.getNomesColecoes());
        selecao.setPromptText("Selecione uma coleção");

        TextArea areaBibtex = new TextArea();
        areaBibtex.setEditable(false);

        selecao.setOnAction(e -> {
            String nomeColecao = selecao.getValue();
            Colecao colecao = gerenciador.getColecao(nomeColecao);
            if (colecao != null) {
                areaBibtex.setText(gerenciador.gerarBibTeXDaColecao(colecao));
            }
        });

        VBox layout = new VBox(10, selecao, areaBibtex);
        layout.setPadding(new javafx.geometry.Insets(10));

        stage.setScene(new Scene(layout, 600, 400));
        stage.show();
    }
}
