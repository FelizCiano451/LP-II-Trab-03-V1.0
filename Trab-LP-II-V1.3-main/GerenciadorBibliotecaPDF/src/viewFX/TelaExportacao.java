package viewfx;

import controller.GerenciadorColecoes;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.CompactadorZip;
import util.ExportadorBibTeX;

import java.io.File;
import java.util.List;

public class TelaExportacao {

    private static GerenciadorColecoes gerenciador = new GerenciadorColecoes();

    public static void exibir() {
        Stage stage = new Stage();
        stage.setTitle("Exportar Coleção");

        ComboBox<String> colecaoBox = new ComboBox<>();
        for (var c : gerenciador.listarColecoes()) {
            colecaoBox.getItems().add(c.getNome());
        }
        colecaoBox.setPromptText("Selecione a coleção");

        Button btnBibtex = new Button("Exportar .bib");
        Button btnZip = new Button("Empacotar .zip");
        Button btnFechar = new Button("Fechar");

        Label status = new Label();

        btnBibtex.setOnAction(e -> {
            String nome = colecaoBox.getValue();
            if (nome != null) {
                try {
                    File arquivo = new File(nome + ".bib");
                    ExportadorBibTeX.exportar(gerenciador.getColecao(nome), arquivo);
                    status.setText("Exportado como " + arquivo.getName());
                } catch (Exception ex) {
                    status.setText("Erro: " + ex.getMessage());
                }
            }
        });

        btnZip.setOnAction(e -> {
            String nome = colecaoBox.getValue();
            if (nome != null) {
                try {
                    File arquivo = new File(nome + ".zip");
                    CompactadorZip.compactarColecao(gerenciador.getColecao(nome), arquivo);
                    status.setText("Empacotado como " + arquivo.getName());
                } catch (Exception ex) {
                    status.setText("Erro: " + ex.getMessage());
                }
            }
        });

        btnFechar.setOnAction(e -> stage.close());

        VBox layout = new VBox(10, colecaoBox, new HBox(10, btnBibtex, btnZip), status, btnFechar);
        layout.setPadding(new Insets(15));

        stage.setScene(new Scene(layout, 400, 180));
        stage.show();
    }
}
