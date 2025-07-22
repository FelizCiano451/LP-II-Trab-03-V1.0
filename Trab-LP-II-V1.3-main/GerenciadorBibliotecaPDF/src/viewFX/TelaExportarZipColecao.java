package viewfx;

import controller.GerenciadorBiblioteca;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Colecao;

import java.io.File;

public class TelaExportarZipColecao {

    public static void exibir(GerenciadorBiblioteca gerenciador) {
        Stage stage = new Stage();
        stage.setTitle("Exportar Coleção para ZIP");

        ComboBox<String> selecao = new ComboBox<>();
        selecao.getItems().addAll(gerenciador.getNomesColecoes());

        Button btnExportar = new Button("Exportar ZIP");

        btnExportar.setOnAction(e -> {
            String nomeColecao = selecao.getValue();
            Colecao colecao = gerenciador.getColecao(nomeColecao);
            if (colecao != null) {
                FileChooser chooser = new FileChooser();
                chooser.setTitle("Salvar Arquivo ZIP");
                chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivo ZIP", "*.zip"));
                File arquivo = chooser.showSaveDialog(stage);
                if (arquivo != null) {
                    gerenciador.exportarColecaoComoZip(colecao, arquivo.getAbsolutePath());
                    stage.close();
                }
            }
        });

        VBox layout = new VBox(10, new Label("Selecione a coleção:"), selecao, btnExportar);
        layout.setPadding(new javafx.geometry.Insets(10));

        stage.setScene(new Scene(layout, 400, 150));
        stage.show();
    }
}
